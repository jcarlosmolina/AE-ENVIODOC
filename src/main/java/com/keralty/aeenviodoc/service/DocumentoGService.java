package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.global.AsesorConstants;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.DocumentoGConstants;
import com.keralty.aeenviodoc.global.FuncionarioConstants;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.global.STDFunctions;
import com.keralty.aeenviodoc.global.UserFunctions;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.Asesor;
import com.keralty.aeenviodoc.persistence.DocumentoG;
import com.keralty.aeenviodoc.persistence.Funcionario;
import com.keralty.aeenviodoc.persistence.oid.DocumentoGOid;
import com.keralty.aeenviodoc.persistence.oid.PersonaOid;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.repository.DocumentoGJpaRepository;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import com.keralty.aeenviodoc.sqlutils.SQLWhereComparison;
import com.keralty.aeenviodoc.sqlutils.SQLWhereConjunction;
import com.keralty.aeenviodoc.sqlutils.SQLWhereDisjunction;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;
import com.keralty.aeenviodoc.viewmodel.FilterVariableRequest;
import com.keralty.aeenviodoc.viewmodel.InstanceResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.PopulationResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.PreloadResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.QueryRequest;
import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class DocumentoGService extends AbstractService {

    DocumentoGJpaRepository repository;

    DocumentoGService documentoGSrv;

    PersonaService personaSrv;

    SolicitudService solicitudSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public DocumentoGService(
            DocumentoGService documentoGSrv,
            PersonaService personaSrv,
            SolicitudService solicitudSrv,
            UserFunctions userFunctionsSrv,
            DocumentoGJpaRepository repository) {
        this.documentoGSrv = documentoGSrv;
        this.personaSrv = personaSrv;
        this.solicitudSrv = solicitudSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, DocumentoGConstants.CLASS_NAME);
    }

    private List<DocumentoG> solveDocumentoGTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, DocumentoGConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveDocumentoGTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, DocumentoGConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class DocumentoG
     *
     * @return Population of class DocumentoG
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DocumentoG> get() {
        List<DocumentoG> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class DocumentoG that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class DocumentoG matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DocumentoG> get(QueryRequest queryRequest) {
        return solveDocumentoGTypedQuery(queryRequest);
    }
    
    /*
     * Returns the list of instances that matches certain search criteria
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return List of instances that matches certain search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public QueryResponse getDynamic(QueryRequest queryRequest) {
        if (queryRequest.getDisplaySetName() == null || queryRequest.getDisplaySetName().isEmpty()) {
            return new PopulationResponseViewModel(solveDocumentoGTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveDocumentoGTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns the population of a class with a certain structure
     *
     * @param relatedInstanceJsonOid Json Oid of the related instance
     *
     * @param relatedInstancePath Path to the related instance
     *
     * @param displaySetItems DisplaySet items for each returned instance
     *
     * @return List of instances
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PopulationResponseViewModel getByRelatedDynamic(String relatedInstanceJsonOid, String relatedInstancePath, String displaySetItems) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstanceJsonOid))));
        return new PopulationResponseViewModel(solveDocumentoGTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of DocumentoG by ID
     *
     * @param documentoGIdentificador Identification field documentoGIdentificador
     *
     * @return Instance of DocumentoG
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public DocumentoG getById(Long documentoGIdentificador) {
        return getByOID(new DocumentoGOid(documentoGIdentificador));
    }
    
    /*
     * Returns an instance of DocumentoG by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of DocumentoG
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public DocumentoG getByOID(DocumentoGOid oid) {
        Optional<DocumentoG> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of DocumentoG by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of DocumentoG
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public DocumentoG getByOIDWithHV(DocumentoGOid oid) {
        Optional<DocumentoG> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<DocumentoG> getInstanceByOid(DocumentoGOid oid) {
        return oid != null && oid.getDocumentoGIdentificador() != null
               ? repository.findById(oid.getDocumentoGIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of DocumentoG by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of DocumentoG
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public DocumentoG getByJsonOid(String jsonOid) {
        DocumentoGOid oid = new DocumentoGOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of DocumentoG related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of DocumentoG
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DocumentoG> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveDocumentoGTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of DocumentoG related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of DocumentoG
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DocumentoG> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of DocumentoG
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of DocumentoG
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getDocumentoGDynamic(DocumentoGOid oid, String displaySetItems) {
        return new InstanceResponseViewModel(getByOIDWithHV(oid), displaySetItems);
    }

    /*
     * Returns the supplementary info for a given instance
     *
     * @param oid Instance OID
     *
     * @param dsName DisplaySet name
     *
     * @return Supplementary info for a given instance
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public String getSuppInfo(DocumentoGOid oid, String dsName) {
        DocumentoG instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(DocumentoG instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods

    /*
     * Returns a viewmodel for class DocumentoG with displayset DSDocumentoGIIU for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class DocumentoG with displayset DSDocumentoGIIU
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeenviodoc.viewmodel.documentog.ds.DSDocumentoGIIU iIUDocumentoGMaestro(DocumentoGOid oid) {
        return new com.keralty.aeenviodoc.viewmodel.documentog.ds.DSDocumentoGIIU(getByOIDWithHV(oid));
    }


    /*
     * Solves the query needed for PIUDocumentoG
     *
     * @param queryRequest Query request
     * @return List of viewmodels for DocumentoG using displayset DSDocumentoGPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeenviodoc.viewmodel.documentog.ds.DSDocumentoGPIU> piuPIUDocumentoG(QueryRequest queryRequest) {
        return solveDocumentoGTypedQuery(queryRequest).stream()
                .map(com.keralty.aeenviodoc.viewmodel.documentog.ds.DSDocumentoGPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUDocumentoG
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUDocumentoG
     */
    public int getCount4PIUDocumentoG(QueryRequest queryRequest) {
        return solveDocumentoGTypedQuery(queryRequest).size();
    }

    // Service: crear

    /*
     * Entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse crear(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = documentoGSrv.crearTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse crearTxn(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest request) throws ModelException {
        request.setDocumentoGcrearpagrPersonaInstance(personaSrv.getByOIDWithHV(request.getDocumentoGcrearpagrPersona()));
        request.setDocumentoGcrearpagrSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getDocumentoGcrearpagrSolicitud()));
        return documentoGSrv.crearImplementation(request);
    }

    /*
     * Implements service crear
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse crearImplementation(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Persona documentoGcrearpagrPersona = request.getDocumentoGcrearpagrPersonaInstance();
        Solicitud documentoGcrearpagrSolicitud = request.getDocumentoGcrearpagrSolicitudInstance();
        String documentoGcrearpatrDescripcion = request.getDocumentoGcrearpatrDescripcion();
        Long documentoGcrearpatrTipo = request.getDocumentoGcrearpatrTipo();
        Long documentoGcrearpatrEstado = request.getDocumentoGcrearpatrEstado();
        Timestamp documentoGcrearpatrFechaSubida = request.getDocumentoGcrearpatrFechaSubida();
        String documentoGcrearpatrIdDocEnGestor = request.getDocumentoGcrearpatrIdDocEnGestor();
        String documentoGcrearpatrUsuarioCreador = request.getDocumentoGcrearpatrUsuarioCreador();

        request.checkArguments();
        DocumentoG instance = new DocumentoG();
        instance.add2Persona(documentoGcrearpagrPersona);

        instance.add2Solicitud(documentoGcrearpagrSolicitud);

        instance.setDocumentoGIdentificador(Long.valueOf(0));
        instance.setDocumentoGNumDescargas(Long.valueOf(0));
        instance.setDocumentoGDescripcion(documentoGcrearpatrDescripcion);
        instance.setDocumentoGTipo(documentoGcrearpatrTipo);
        instance.setDocumentoGEstado(documentoGcrearpatrEstado);
        instance.setDocumentoGFechaSubida(documentoGcrearpatrFechaSubida);
        instance.setDocumentoGIdDocEnGestor(documentoGcrearpatrIdDocEnGestor);
        instance.setDocumentoGUsuarioCreador(documentoGcrearpatrUsuarioCreador);

        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: modificar

    /*
     * Entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse modificar(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = documentoGSrv.modificarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse modificarTxn(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.ModificarRequest request) throws ModelException {
        request.setDocumentoGmodificarpthisDocumentoGInstance(documentoGSrv.getByOIDWithHV(request.getDocumentoGmodificarpthisDocumentoG()));
        return documentoGSrv.modificarImplementation(request);
    }

    /*
     * Implements service modificar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse modificarImplementation(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DocumentoG documentoGmodificarpthisDocumentoG = request.getDocumentoGmodificarpthisDocumentoGInstance();
        String documentoGmodificarpDescripcion = request.getDocumentoGmodificarpDescripcion();
        Long documentoGmodificarpTipo = request.getDocumentoGmodificarpTipo();
        Long documentoGmodificarpEstado = request.getDocumentoGmodificarpEstado();
        Timestamp documentoGmodificarpFechaSubida = request.getDocumentoGmodificarpFechaSubida();

        request.checkArguments();
        DocumentoG instance = documentoGmodificarpthisDocumentoG;
        instance.setDocumentoGDescripcion(documentoGmodificarpDescripcion);
        instance.setDocumentoGTipo(documentoGmodificarpTipo);
        instance.setDocumentoGEstado(documentoGmodificarpEstado);
        instance.setDocumentoGFechaSubida(documentoGmodificarpFechaSubida);


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: ponerNombreDocumento

    /*
     * Entry point for service ponerNombreDocumento
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse ponerNombreDocumento(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.PonerNombreDocumentoRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = documentoGSrv.ponerNombreDocumentoTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service ponerNombreDocumento
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse ponerNombreDocumentoTxn(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.PonerNombreDocumentoRequest request) throws ModelException {
        request.setDocumentoGponerNombreDocumentopthisDocumentoGInstance(documentoGSrv.getByOIDWithHV(request.getDocumentoGponerNombreDocumentopthisDocumentoG()));
        return documentoGSrv.ponerNombreDocumentoImplementation(request);
    }

    /*
     * Implements service ponerNombreDocumento
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse ponerNombreDocumentoImplementation(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.PonerNombreDocumentoRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DocumentoG documentoGponerNombreDocumentopthisDocumentoG = request.getDocumentoGponerNombreDocumentopthisDocumentoGInstance();
        String documentoGponerNombreDocumentopExtension = request.getDocumentoGponerNombreDocumentopExtension();
        String documentoGponerNombreDocumentopNombre = request.getDocumentoGponerNombreDocumentopNombre();

        request.checkArguments();
        DocumentoG instance = documentoGponerNombreDocumentopthisDocumentoG;
        if (documentoGponerNombreDocumentopExtension != null) {
            instance.setDocumentoGExtension(documentoGponerNombreDocumentopExtension);
        } else {
            instance.setDocumentoGExtension("pdf");
        }
        if (documentoGponerNombreDocumentopNombre != null) {
            instance.setDocumentoGNombre(documentoGponerNombreDocumentopNombre);
        } else if (documentoGponerNombreDocumentopNombre == null && (documentoGponerNombreDocumentopthisDocumentoG.getDocumentoGTipo()).equals(Long.valueOf(1))) {
            instance.setDocumentoGNombre("DOCIDENTIFICACION" + documentoGponerNombreDocumentopthisDocumentoG.getPersona().getTipoIdentificacion().getTipoIdentificacionNombreCorto() + documentoGponerNombreDocumentopthisDocumentoG.getPersona().getPersonaNumIdentificacion());
        } else {
            instance.setDocumentoGNombre("DOCSOLICITUD" + documentoGponerNombreDocumentopthisDocumentoG.getSolicitud().getAesolicitudes().getAesolicitudesNumerosolicitud() + "_" + STDFunctions.intToStrFun(documentoGponerNombreDocumentopthisDocumentoG.getDocumentoGIdentificador()));
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: sustituir

    /*
     * Entry point for service sustituir
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse sustituir(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.SustituirRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = documentoGSrv.sustituirTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service sustituir
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse sustituirTxn(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.SustituirRequest request) throws ModelException {
        request.setDocumentoGsustituirpthisDocumentoGInstance(documentoGSrv.getByOIDWithHV(request.getDocumentoGsustituirpthisDocumentoG()));
        return documentoGSrv.sustituirImplementation(request);
    }

    /*
     * Implements service sustituir
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse sustituirImplementation(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.SustituirRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DocumentoG documentoGsustituirpthisDocumentoG = request.getDocumentoGsustituirpthisDocumentoGInstance();

        request.checkArguments();
        DocumentoG instance = documentoGsustituirpthisDocumentoG;
        instance.setDocumentoGEstado(Long.valueOf(5));


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: setFechaSubida

    /*
     * Entry point for service setFechaSubida
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse setFechaSubida(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.SetFechaSubidaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = documentoGSrv.setFechaSubidaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service setFechaSubida
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse setFechaSubidaTxn(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.SetFechaSubidaRequest request) throws ModelException {
        request.setDocumentoGsetFechaSubidapthisDocumentoGInstance(documentoGSrv.getByOIDWithHV(request.getDocumentoGsetFechaSubidapthisDocumentoG()));
        return documentoGSrv.setFechaSubidaImplementation(request);
    }

    /*
     * Implements service setFechaSubida
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse setFechaSubidaImplementation(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.SetFechaSubidaRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DocumentoG documentoGsetFechaSubidapthisDocumentoG = request.getDocumentoGsetFechaSubidapthisDocumentoGInstance();
        Timestamp documentoGsetFechaSubidapeFechaHora = request.getDocumentoGsetFechaSubidapeFechaHora();

        request.checkArguments();
        DocumentoG instance = documentoGsetFechaSubidapthisDocumentoG;
        instance.setDocumentoGFechaSubida(documentoGsetFechaSubidapeFechaHora);


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: subirDocumentoGestorATO

    /*
     * Entry point for service subirDocumentoGestorATO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse subirDocumentoGestorATO(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.SubirDocumentoGestorATORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = documentoGSrv.subirDocumentoGestorATOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service subirDocumentoGestorATO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse subirDocumentoGestorATOTxn(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.SubirDocumentoGestorATORequest request) throws ModelException {
        request.setDocumentoGsubirDocumentoGestorATOpthisDocumentoGInstance(documentoGSrv.getByOIDWithHV(request.getDocumentoGsubirDocumentoGestorATOpthisDocumentoG()));
        return documentoGSrv.subirDocumentoGestorATOImplementation(request);
    }

    /*
     * Implements service subirDocumentoGestorATO
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse subirDocumentoGestorATOImplementation(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.SubirDocumentoGestorATORequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DocumentoG documentoGsubirDocumentoGestorATOpthisDocumentoG = request.getDocumentoGsubirDocumentoGestorATOpthisDocumentoGInstance();
        String documentoGsubirDocumentoGestorATOpExtension = request.getDocumentoGsubirDocumentoGestorATOpExtension();
        String documentoGsubirDocumentoGestorATOpIdDocGestor = request.getDocumentoGsubirDocumentoGestorATOpIdDocGestor();
        String documentoGsubirDocumentoGestorATOpNombre = request.getDocumentoGsubirDocumentoGestorATOpNombre();

        request.checkArguments();
        DocumentoG instance = documentoGsubirDocumentoGestorATOpthisDocumentoG;
        instance.setDocumentoGExtension(documentoGsubirDocumentoGestorATOpExtension);
        instance.setDocumentoGIdDocEnGestor(documentoGsubirDocumentoGestorATOpIdDocGestor);
        instance.setDocumentoGNombre(documentoGsubirDocumentoGestorATOpNombre);


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: TCREAR

    /*
     * Entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREAR(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = documentoGSrv.tCREARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREARTxn(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.TCREARRequest request) throws ModelException, SystemException {
        request.setDocumentoGTCREARptpagrSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getDocumentoGTCREARptpagrSolicitud()));
        request.setDocumentoGTCREARptpagrPersonaInstance(personaSrv.getByOIDWithHV(request.getDocumentoGTCREARptpagrPersona()));
        return documentoGSrv.tCREARImplementation(request);
    }

    /*
     * Implements service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREARImplementation(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.TCREARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Persona documentoGTCREARptpagrPersona = request.getDocumentoGTCREARptpagrPersonaInstance();
        Solicitud documentoGTCREARptpagrSolicitud = request.getDocumentoGTCREARptpagrSolicitudInstance();
        String documentoGTCREARptpatrDescripcion = request.getDocumentoGTCREARptpatrDescripcion();
        Long documentoGTCREARptpatrTipo = request.getDocumentoGTCREARptpatrTipo();
        Long documentoGTCREARptpatrEstado = request.getDocumentoGTCREARptpatrEstado();
        Timestamp documentoGTCREARptpatrFechaSubida = request.getDocumentoGTCREARptpatrFechaSubida();
        String documentoGTCREARptpatrIdDocEnGestor = request.getDocumentoGTCREARptpatrIdDocEnGestor();
        String documentoGTCREARptpNombre = request.getDocumentoGTCREARptpNombre();
        String documentoGTCREARptpExtension = request.getDocumentoGTCREARptpExtension();

        request.checkArguments();
        DocumentoG instance;

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest step10Request = new com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest();
            step10Request.setDocumentoGcrearpagrPersonaInstance(documentoGTCREARptpagrPersona);
            step10Request.setDocumentoGcrearpagrSolicitudInstance(documentoGTCREARptpagrSolicitud);
            step10Request.setDocumentoGcrearpatrDescripcion(documentoGTCREARptpatrDescripcion);
            step10Request.setDocumentoGcrearpatrTipo(documentoGTCREARptpatrTipo);
            step10Request.setDocumentoGcrearpatrEstado(documentoGTCREARptpatrEstado);
            step10Request.setDocumentoGcrearpatrFechaSubida(documentoGTCREARptpatrFechaSubida);
            step10Request.setDocumentoGcrearpatrIdDocEnGestor(documentoGTCREARptpatrIdDocEnGestor);
            step10Request.setDocumentoGcrearpatrUsuarioCreador(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
            instance = (DocumentoG) documentoGSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest step11Request = new com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest();
            step11Request.setDocumentoGcrearpagrPersonaInstance(documentoGTCREARptpagrPersona);
            step11Request.setDocumentoGcrearpagrSolicitudInstance(documentoGTCREARptpagrSolicitud);
            step11Request.setDocumentoGcrearpatrDescripcion(documentoGTCREARptpatrDescripcion);
            step11Request.setDocumentoGcrearpatrTipo(documentoGTCREARptpatrTipo);
            step11Request.setDocumentoGcrearpatrEstado(documentoGTCREARptpatrEstado);
            step11Request.setDocumentoGcrearpatrFechaSubida(documentoGTCREARptpatrFechaSubida);
            step11Request.setDocumentoGcrearpatrIdDocEnGestor(documentoGTCREARptpatrIdDocEnGestor);
            step11Request.setDocumentoGcrearpatrUsuarioCreador("usuicafile");
            instance = (DocumentoG) documentoGSrv.crearImplementation(step11Request).getInstance();
        }

        com.keralty.aeenviodoc.viewmodel.documentog.siu.PonerNombreDocumentoRequest step2Request = new com.keralty.aeenviodoc.viewmodel.documentog.siu.PonerNombreDocumentoRequest();
        step2Request.setDocumentoGponerNombreDocumentopthisDocumentoGInstance(instance);
        step2Request.setDocumentoGponerNombreDocumentopNombre(documentoGTCREARptpNombre);
        step2Request.setDocumentoGponerNombreDocumentopExtension(documentoGTCREARptpExtension);
        documentoGSrv.ponerNombreDocumentoImplementation(step2Request);

        com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCALCESTADODOCURequest step3Request = new com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCALCESTADODOCURequest();
        step3Request.setSolicitudTCALCESTADODOCUpthisSolicitudInstance(instance.getSolicitud());
        solicitudSrv.tCALCESTADODOCUImplementation(step3Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TNEWATO

    /*
     * Entry point for service TNEWATO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tNEWATO(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.TNEWATORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = documentoGSrv.tNEWATOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TNEWATO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tNEWATOTxn(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.TNEWATORequest request) throws ModelException, SystemException {
        request.setDocumentoGTNEWATOptpagrPersonaInstance(personaSrv.getByOIDWithHV(request.getDocumentoGTNEWATOptpagrPersona()));
        request.setDocumentoGTNEWATOptpagrSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getDocumentoGTNEWATOptpagrSolicitud()));
        return documentoGSrv.tNEWATOImplementation(request);
    }

    /*
     * Implements service TNEWATO
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tNEWATOImplementation(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.TNEWATORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Persona documentoGTNEWATOptpagrPersona = request.getDocumentoGTNEWATOptpagrPersonaInstance();
        Solicitud documentoGTNEWATOptpagrSolicitud = request.getDocumentoGTNEWATOptpagrSolicitudInstance();
        String documentoGTNEWATOptpatrDescripcion = request.getDocumentoGTNEWATOptpatrDescripcion();
        Long documentoGTNEWATOptpatrTipo = request.getDocumentoGTNEWATOptpatrTipo();
        Long documentoGTNEWATOptpatrEstado = request.getDocumentoGTNEWATOptpatrEstado();
        String documentoGTNEWATOpIdDocGestor = request.getDocumentoGTNEWATOpIdDocGestor();
        String documentoGTNEWATOptpExtension = request.getDocumentoGTNEWATOptpExtension();
        String documentoGTNEWATOptNombre = request.getDocumentoGTNEWATOptNombre();

        request.checkArguments();
        DocumentoG instance;

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest step10Request = new com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest();
            step10Request.setDocumentoGcrearpagrPersonaInstance(documentoGTNEWATOptpagrPersona);
            step10Request.setDocumentoGcrearpagrSolicitudInstance(documentoGTNEWATOptpagrSolicitud);
            step10Request.setDocumentoGcrearpatrDescripcion(documentoGTNEWATOptpatrDescripcion);
            step10Request.setDocumentoGcrearpatrTipo(documentoGTNEWATOptpatrTipo);
            step10Request.setDocumentoGcrearpatrEstado(documentoGTNEWATOptpatrEstado);
            step10Request.setDocumentoGcrearpatrFechaSubida(STDFunctions.systemDateTimeFun());
            step10Request.setDocumentoGcrearpatrIdDocEnGestor(documentoGTNEWATOpIdDocGestor);
            step10Request.setDocumentoGcrearpatrUsuarioCreador(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
            instance = (DocumentoG) documentoGSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest step11Request = new com.keralty.aeenviodoc.viewmodel.documentog.siu.CrearRequest();
            step11Request.setDocumentoGcrearpagrPersonaInstance(documentoGTNEWATOptpagrPersona);
            step11Request.setDocumentoGcrearpagrSolicitudInstance(documentoGTNEWATOptpagrSolicitud);
            step11Request.setDocumentoGcrearpatrDescripcion(documentoGTNEWATOptpatrDescripcion);
            step11Request.setDocumentoGcrearpatrTipo(documentoGTNEWATOptpatrTipo);
            step11Request.setDocumentoGcrearpatrEstado(documentoGTNEWATOptpatrEstado);
            step11Request.setDocumentoGcrearpatrFechaSubida(STDFunctions.systemDateTimeFun());
            step11Request.setDocumentoGcrearpatrIdDocEnGestor(documentoGTNEWATOpIdDocGestor);
            step11Request.setDocumentoGcrearpatrUsuarioCreador("usuicafile");
            instance = (DocumentoG) documentoGSrv.crearImplementation(step11Request).getInstance();
        }

        com.keralty.aeenviodoc.viewmodel.documentog.siu.SubirDocumentoGestorATORequest step2Request = new com.keralty.aeenviodoc.viewmodel.documentog.siu.SubirDocumentoGestorATORequest();
        step2Request.setDocumentoGsubirDocumentoGestorATOpthisDocumentoGInstance(instance);
        step2Request.setDocumentoGsubirDocumentoGestorATOpExtension(documentoGTNEWATOptpExtension);
        step2Request.setDocumentoGsubirDocumentoGestorATOpIdDocGestor(documentoGTNEWATOpIdDocGestor);
        step2Request.setDocumentoGsubirDocumentoGestorATOpNombre(documentoGTNEWATOptNombre);
        documentoGSrv.subirDocumentoGestorATOImplementation(step2Request);

        com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCALCESTADODOCURequest step3Request = new com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCALCESTADODOCURequest();
        step3Request.setSolicitudTCALCESTADODOCUpthisSolicitudInstance(instance.getSolicitud());
        solicitudSrv.tCALCESTADODOCUImplementation(step3Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TMODIFICARATO

    /*
     * Entry point for service TMODIFICARATO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tMODIFICARATO(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.TMODIFICARATORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = documentoGSrv.tMODIFICARATOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TMODIFICARATO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tMODIFICARATOTxn(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.TMODIFICARATORequest request) throws ModelException, SystemException {
        request.setDocumentoGTMODIFICARATOpthisDocumentoGInstance(documentoGSrv.getByOIDWithHV(request.getDocumentoGTMODIFICARATOpthisDocumentoG()));
        return documentoGSrv.tMODIFICARATOImplementation(request);
    }

    /*
     * Implements service TMODIFICARATO
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tMODIFICARATOImplementation(
            com.keralty.aeenviodoc.viewmodel.documentog.siu.TMODIFICARATORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        String documentoGTMODIFICARATOptpDescripcion = request.getDocumentoGTMODIFICARATOptpDescripcion();
        Long documentoGTMODIFICARATOptpTipo = request.getDocumentoGTMODIFICARATOptpTipo();
        Long documentoGTMODIFICARATOptpEstado = request.getDocumentoGTMODIFICARATOptpEstado();
        Timestamp documentoGTMODIFICARATOptpFechaSubida = request.getDocumentoGTMODIFICARATOptpFechaSubida();
        String documentoGTMODIFICARATOptpExtension = request.getDocumentoGTMODIFICARATOptpExtension();
        String documentoGTMODIFICARATOptIdDocGestor = request.getDocumentoGTMODIFICARATOptIdDocGestor();
        String documentoGTMODIFICARATOptNombre = request.getDocumentoGTMODIFICARATOptNombre();
        DocumentoG documentoGTMODIFICARATOpthisDocumentoG = request.getDocumentoGTMODIFICARATOpthisDocumentoGInstance();

        request.checkArguments();
        DocumentoG instance = documentoGTMODIFICARATOpthisDocumentoG;

        com.keralty.aeenviodoc.viewmodel.documentog.siu.ModificarRequest step1Request = new com.keralty.aeenviodoc.viewmodel.documentog.siu.ModificarRequest();
        step1Request.setDocumentoGmodificarpthisDocumentoGInstance(documentoGTMODIFICARATOpthisDocumentoG);
        step1Request.setDocumentoGmodificarpDescripcion(documentoGTMODIFICARATOptpDescripcion);
        step1Request.setDocumentoGmodificarpTipo(documentoGTMODIFICARATOptpTipo);
        step1Request.setDocumentoGmodificarpEstado(documentoGTMODIFICARATOptpEstado);
        step1Request.setDocumentoGmodificarpFechaSubida(documentoGTMODIFICARATOptpFechaSubida);
        documentoGSrv.modificarImplementation(step1Request);

        com.keralty.aeenviodoc.viewmodel.documentog.siu.SubirDocumentoGestorATORequest step2Request = new com.keralty.aeenviodoc.viewmodel.documentog.siu.SubirDocumentoGestorATORequest();
        step2Request.setDocumentoGsubirDocumentoGestorATOpthisDocumentoGInstance(documentoGTMODIFICARATOpthisDocumentoG);
        step2Request.setDocumentoGsubirDocumentoGestorATOpExtension(documentoGTMODIFICARATOptpExtension);
        step2Request.setDocumentoGsubirDocumentoGestorATOpIdDocGestor(documentoGTMODIFICARATOptIdDocGestor);
        step2Request.setDocumentoGsubirDocumentoGestorATOpNombre(documentoGTMODIFICARATOptNombre);
        documentoGSrv.subirDocumentoGestorATOImplementation(step2Request);

        com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCALCESTADODOCURequest step3Request = new com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCALCESTADODOCURequest();
        step3Request.setSolicitudTCALCESTADODOCUpthisSolicitudInstance(documentoGTMODIFICARATOpthisDocumentoG.getSolicitud());
        solicitudSrv.tCALCESTADODOCUImplementation(step3Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }


    @Override
    protected void applyHVFilter(SQLSelect sql) throws SystemException {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        SQLWhereDisjunction disjunction = new SQLWhereDisjunction();
        if (getAgentRoles().contains(AsesorConstants.CLASS_NAME)) applyHVFilter4Asesor(sql, disjunction);
        conjunction.addDisjunction(disjunction);
        sql.addConjunction(conjunction);
    }

    private void applyHVFilter4Asesor(SQLSelect sql, SQLWhereDisjunction disjunction) {
        // Solicitud.Agente = agent OR Solicitud.Agente.CodJefe = Agent.Usuario
        Asesor agentInstance = getAgentAsesor();
    {
        // 'or' Logical operator
        SQLWhereDisjunction comparison1 = new SQLWhereDisjunction();
    
        // Operand 1
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(DocumentoGConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AGENTE, FuncionarioConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(Constants.AGENTKEYWORD, AsesorConstants.CLASS_NAME, AsesorConstants.CLASS_NAME, agentInstance.getOid(), Constants.AGENTKEYWORD);
            comparison2.setSecondOperand(operand224);
            comparison1.addOperand(comparison2);
    
        // Operand 2
            // '=' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(DocumentoGConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AGENTE + "." + FuncionarioConstants.ATTR_NAME_FUNCIONARIOCODJEFE, Constants.Type.STRING.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral(agentInstance.getFuncionarioUsuario(), Constants.Type.STRING.getTypeName()));
            comparison1.addOperand(comparison4);
        
        disjunction.addOperand(comparison1);
    }

    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCDocumentoG(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(DocumentoGConstants.CLASS_NAME, DocumentoGConstants.ATTR_FIELD_DOCUMENTOGIDENTIFICADOR, false), true);
    }


    private void applyOCOCDocumentoG(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_DocumentoG".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(DocumentoGConstants.CLASS_NAME, DocumentoGConstants.ATTR_NAME_DOCUMENTOGFECHASUBIDA, false), false);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildDocumentoGRelatedInstance(relatedInstanceJsonOid);
        }
        if (DocumentoGConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDocumentoGRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (DocumentoGConstants.ROLE_NAME_PERSONA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDocumentoGRelatedInstance4Persona(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildDocumentoGRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new DocumentoG(mapper.readValue(relatedInstanceJsonOid, DocumentoGOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDocumentoGRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDocumentoGRelatedInstance4Persona(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Persona(mapper.readValue(relatedInstanceJsonOid, PersonaOid.class)): Persona.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            DocumentoGOid oid = new DocumentoGOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(DocumentoGConstants.ATTR_FIELD_DOCUMENTOGIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getDocumentoGIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(DocumentoGService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
