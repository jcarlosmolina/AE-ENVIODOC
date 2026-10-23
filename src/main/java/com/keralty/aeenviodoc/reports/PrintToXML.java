
package com.keralty.aeenviodoc.reports;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;



/**
 * Utility para obtener los datos en formato XML a partir de una instancia y un archivo de configuracion de report 
 */
public class PrintToXML {

	private static final String DISALLOW_DOCTYPE_STRING = "http://apache.org/xml/features/disallow-doctype-decl";

	private PrintToXML() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Devuelve los datos solicitados en el reportConfig a partir de la instancia recibida
	 * @param instancia Instancia origen de los datos
	 * @param reportConfigFile Configuración del report, los datos que se necesitan
	 * @param configIsFile Indica si el reportConfigFile es el path del fichero o el contenido 
	 * @return Los datos en formato XML String
	 * @throws SystemException
	 */
	public static String GetDataString(AbstractAppEntity instancia, String reportConfigFile, boolean configIsFile) throws SystemException{
		Document doc = null;
		if (configIsFile) {
			doc = getDataXML(instancia, reportConfigFile);	
		} else {
			
			DocumentBuilderFactory factory = documentBuilderFactoryNewInstance();
		    factory.setNamespaceAware(true);
		    DocumentBuilder builder;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				throw new SystemException(e.getMessage());
			}
			try {
				doc = builder.parse(new ByteArrayInputStream(reportConfigFile.getBytes()));
			} catch (IOException | SAXException e) {
				throw new SystemException(e.getMessage());
			}
			
			doc = getDataXMLForDocConfigFileAndInstance(doc, instancia);
		}
		
		String data = "";
		if (doc != null && doc.hasChildNodes()) {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = transformerFactoryNewInstance();
			Transformer transformer;
			try {
				transformer = tf.newTransformer();
			} catch (TransformerConfigurationException e) {
				throw new SystemException(e.getMessage());
			}
			try {
				transformer.transform(domSource, result);
			} catch (TransformerException e) {
				throw new SystemException(e.getMessage());
			}
			data = writer.toString();				
		}
		return data;
	}

	private static DocumentBuilderFactory documentBuilderFactoryNewInstance() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setFeature(DISALLOW_DOCTYPE_STRING, true);
		} catch (ParserConfigurationException e) {
			throw new SystemException(e.getMessage());
		}
		return factory;
	}

    public static TransformerFactory transformerFactoryNewInstance() {
		TransformerFactory transformerFactory = TransformerFactory.newDefaultInstance();
		transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
		return transformerFactory;
    }

	public static Document getDataXML(AbstractAppEntity instancia, String reportConfigFile) throws SystemException{
		Document doc = loadXMLReportConfigurationFile(reportConfigFile);
		return getDataXMLForDocConfigFileAndInstance(doc, instancia);
	}	
	
	private static Document getDataXMLForDocConfigFileAndInstance(Document docConfigFile, AbstractAppEntity instancia) throws SystemException{

		if (docConfigFile == null) return null;

        // Get the staring class node
        NodeList nodeListStartingClass = docConfigFile.getElementsByTagName("StartingClass");
        if (nodeListStartingClass.getLength() == 0){
			String errorMessage = "PTX004: Starting class node not found";
			throw new SystemException(errorMessage);
        }

        // Read the starting class information
        Node startingClassInfo = nodeListStartingClass.item(0);
        // Name to be used in the data XML
        String name2UseStartingClass = startingClassInfo.getAttributes().getNamedItem("name2Use").getNodeValue();
        
        // Get info about attributes
        ArrayList<String> attNames = new ArrayList<>();
        ArrayList<String> attNames2Use = new ArrayList<>();
        getInfoAboutAttributes(startingClassInfo, attNames, attNames2Use);
        // Create the XML writer
		DocumentBuilderFactory docBuilderFactory = documentBuilderFactoryNewInstance();
        DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			String errorMessage = "PTX008: " + e.getMessage();
			throw new SystemException(errorMessage);
		}

		Document dataDocument = docBuilder.newDocument();
		// Root element. Always <Data>
		Element rootElement = dataDocument.createElement("Data");
		dataDocument.appendChild(rootElement);
		
		// Starting class element
		Element startingClassElement = dataDocument.createElement(name2UseStartingClass);
		rootElement.appendChild(startingClassElement);
		
		// Add the attributes value to the node
		if (instancia != null && !instancia.isNull()){
			addAttributesToNode(instancia,attNames,attNames2Use, dataDocument, startingClassElement);
		}

		// Obtain the related nodes and process all of them 
        ArrayList<Node> relatedList = getChildNodes(startingClassInfo, "Related");
		for (Node relNode : relatedList) {
			processRelationships(instancia, relNode, dataDocument, startingClassElement);
		}

		return dataDocument;
	}
	
	/**
	 * Load the Report configuration file
	 * @param reportConfigFile Configuration file path name 
	 * @return Xml Document
	 * @throws Exception In case of any error
	 */
	private static Document loadXMLReportConfigurationFile(String reportConfigFile) throws SystemException {
		File file = new File(reportConfigFile);

		DocumentBuilderFactory docBuilderFactory = documentBuilderFactoryNewInstance();

		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new SystemException(e.getMessage());
		}

        DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			String errorMessage = "PTX001: " + e.getMessage();
			try {
				inputStream.close();
			} catch (IOException e1) {
				throw new SystemException(e.getMessage());
			}
			throw new SystemException(errorMessage);
		}
        Document doc = null;
		try {
			doc = docBuilder.parse(inputStream);
		} catch (SAXException e1) {
			String errorMessage = "PTX002: Error parsing file. " + e1.getMessage();
			throw new SystemException(errorMessage);
		} catch (IOException e1) {
			String errorMessage = "PTX003: Error parsing file. " + e1.getMessage();
			throw new SystemException(errorMessage);
		}
        // Normalize text representation
        doc.getDocumentElement().normalize();
        
        return doc;
	}

	/**
	 * Procesa las relaciones mutivaluadas de la instancia recibida
	 * @param instancia Origen
	 * @param relNode Nodo XML con la informaci�n del rol multivaluado
	 * @param dataDocument Documento XML al que a�adir la informaci�n
	 * @param parentElement Nodo padre al que a�adir los nuevos nodos
	 * @throws Exception En caso de error
	 */
	private static void processRelationships(AbstractAppEntity instancia, Node relNode, Document dataDocument, Element parentElement){

		// Read the info about the related 
        String rolName = relNode.getAttributes().getNamedItem("roleName").getNodeValue();
        String name2UseRol = relNode.getAttributes().getNamedItem("name2UseRol").getNodeValue();
        String name2UseClass = relNode.getAttributes().getNamedItem("name2UseClass").getNodeValue();
        
        
        Element rolElement = dataDocument.createElement(name2UseRol);
        parentElement.appendChild(rolElement);

        // Create the display set and store the attribute info
        ArrayList<String> attNames = new ArrayList<>();
        ArrayList<String> attNames2Use = new ArrayList<>();
        getInfoAboutAttributes(relNode, attNames, attNames2Use);
		// Obtain the related nodes 
        ArrayList<Node> relatedList = getChildNodes(relNode, "Related");

		// Obtenemos los datos de la relacionada
		Method metodoGetRol = instancia.getMethod(rolName);
		if (metodoGetRol == null && rolName != null && rolName.contains("_")) {
			String sinGuiones = rolName.replaceAll("_", "");
			metodoGetRol = instancia.getMethod(sinGuiones);
		}
		if (metodoGetRol != null){
			Object relacionados = null;
			try {
				var args = new Object[0];
				relacionados = metodoGetRol.invoke(instancia, args);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				relacionados = null;
			}
			if (relacionados instanceof  Collection){
				Collection<AbstractAppEntity> colRelacionados = (Collection<AbstractAppEntity>) relacionados; 
				for (AbstractAppEntity relacionada : colRelacionados){
				
					if (relacionada != null && !relacionada.isNull()) {
				        // Add the related node to the XML
				        Element relatedElement = dataDocument.createElement(name2UseClass);
				        rolElement.appendChild(relatedElement);
						
						// Add the attribute values to the XML
						addAttributesToNode(relacionada, attNames, attNames2Use, dataDocument, relatedElement);
	
						// Process the related nodes
						for (Node relatedNode : relatedList) {
							processRelationships(relacionada, relatedNode, dataDocument, relatedElement);
						}
					}
				}
			} else if (relacionados instanceof  AbstractAppEntity relacionada){
				if (relacionada != null && !relacionada.isNull()) {
			        // Add the related node to the XML
			        Element relatedElement = dataDocument.createElement(name2UseClass);
			        rolElement.appendChild(relatedElement);
					
					// Add the attribute values to the XML
					addAttributesToNode(relacionada, attNames, attNames2Use, dataDocument, relatedElement);
	
					// Process the related nodes
					for (Node relatedNode : relatedList) {
						processRelationships(relacionada, relatedNode, dataDocument, relatedElement);
					}
				}
			}
			
		}
	}

	/**
	 * A�ade los valores de los atributos como nodos XML  
	 * @param instancia Origen de la informaci�n
	 * @param attNames Nombres de los atributos
	 * @param attNames2Use Nombres a utilizar en el XML
	 * @param dataDocument Documento XML
	 * @param element Elememnto al que a�adir los nuevos nodos
	 */
	private static void addAttributesToNode(AbstractAppEntity instancia, ArrayList<String> attNames, ArrayList<String> attNames2Use, Document dataDocument, Element element) {
		for(int i = 0; i < attNames.size();i++){
			Object value = getValorAtributo(instancia, attNames.get(i));
			Element attElement = dataDocument.createElement(attNames2Use.get(i));
			if (value != null){
				if (value instanceof byte[] valueArray){
					attElement.appendChild(dataDocument.createTextNode(new String(Base64.encodeBase64(valueArray))));
				}
				else{
					attElement.appendChild(dataDocument.createTextNode(value.toString()));
				}
			}
			element.appendChild(attElement);
		}
	}


	/**
	 * Return the child nodes with the specified tag name
	 * @param parentNode Parent node 
	 * @param tagName Tag name to be found
	 * @return List of nodes with that tag name
	 */
	private static ArrayList<Node> getChildNodes(Node parentNode, String tagName) {
		ArrayList<Node> lReturn = new ArrayList<>();
		NodeList nodeList  = parentNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength();i++) {
			Node node = nodeList.item(i);
			if (node.getNodeName() != null && node.getNodeName().equalsIgnoreCase(tagName)){
				lReturn.add(node);
			}
		}
		return lReturn;
	}
	
	/**
	 * Devuelve el valor del atributo en formato XML para Jasper
	 * @param instancia Instancia
	 * @param atributo Atributo
	 * @return Valor en formato
	 * @throws IllegalAccessException En caso de error
	 * @throws IllegalArgumentException En caso de error
	 * @throws InvocationTargetException En caso de error
	 */
	public static Object getValorAtributo(AbstractAppEntity instancia, String atributo) {
		Object valor = null;
		Method metodo = null;
		// Comprobamos si se trata de un path de roles
		try{
			int pos = atributo.indexOf(".");
			if (pos > 0){
				String primerRol = atributo.substring(0,pos);
				AbstractAppEntity instanciaRol = getInstanciaRol(instancia,primerRol);
				String pathRestante = atributo.substring(pos+1);
				if (instanciaRol != null && !instanciaRol.isNull()){
					valor = getValorAtributo(instanciaRol, pathRestante);
				}
			} else{
				metodo = instancia.getMethod(atributo);
				if (metodo == null && atributo != null && atributo.contains("_")) {
					String sinGuiones = atributo.replaceAll("_", "");
					metodo = instancia.getMethod(sinGuiones);
				}
				if (metodo != null){
					var args = new Object[0];
					valor = metodo.invoke(instancia, args);
				}
			}
		}
		catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			valor = null;
		}
		return valor;
	}
	
	/**
	 * Devuelve la instancia correspondiente al rol univaluado 
	 * @param instancia Origen
	 * @param primerRol Rol a recuperar
	 * @return La instancia del rol
	 * @throws IllegalAccessException En caso de error
	 * @throws IllegalArgumentException  En caso de error
	 * @throws InvocationTargetException  En caso de error
	 */
	private static AbstractAppEntity getInstanciaRol(AbstractAppEntity instancia, String primerRol) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method metodo = instancia.getMethod(primerRol);
		if (metodo == null && primerRol != null && primerRol.contains("_")) {
			primerRol = primerRol.replaceAll("_", "");
			metodo = instancia.getMethod(primerRol);
		}
		if (metodo != null){
			var args = new Object[0];
			Object objeto = metodo.invoke(instancia, args);
			if (objeto instanceof  AbstractAppEntity instanciaRol){
				return instanciaRol;
			}
		}
		return null;
	}
	/**
	 * Analyze the Xml node and gets information about the existing attributes
	 * @param nodeInfo Node
	 * @param attNames Attribute names found
	 * @param attNames2Use Names to use found
	 * @return Display set. Attribute names separated by commas
	 */
	private static String getInfoAboutAttributes(Node nodeInfo,  ArrayList<String> attNames, ArrayList<String> attNames2Use) {
        
		StringBuilder displaySet = new StringBuilder("");
        // Get Attribute nodes
        ArrayList<Node> attributeList = getChildNodes(nodeInfo, "Attribute");
        
        // Create the display set and store the attribute info
		for (Node attNode : attributeList) {
			if (!displaySet.toString().equals("")){
				displaySet.append(",");
			}
			String attName = attNode.getAttributes().getNamedItem("name").getNodeValue(); 
			String attName2Use = attNode.getAttributes().getNamedItem("name2Use").getNodeValue(); 
			displaySet.append(attName);
			attNames.add(attName);
			attNames2Use.add(attName2Use);
		}
		return displaySet.toString();
	}
}
