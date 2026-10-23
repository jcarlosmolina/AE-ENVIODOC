package com.keralty.aeenviodoc.reports;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ReportGenerator {

	@RestClient
	ReportsGeneratorProxy reportsGeneratorProxy;
	
	@Inject
	public ReportGenerator() {
		
	}
	
	public byte[] generateReport(AbstractAppEntity instancia, String rutaParcial) throws SystemException {
		String prefijoPath = "CLASSPATH:";
        String rutaCompleta = prefijoPath + rutaParcial;
//        if (!"CLASSPATH:".equalsIgnoreCase(prefijoPath) && !prefijoPath.endsWith("/") && !rutaParcial.startsWith("/")) {
//        	rutaCompleta = prefijoPath + "/" + rutaParcial;
//        }
        return generateReportAux(instancia, rutaCompleta);
	}
	
	private byte[] generateReportAux(AbstractAppEntity instancia, String pathConfiguration) throws SystemException {

		// 1. Obtener la configuracion, qué datos se necesitan
		ConfigRequest configRequest = new ConfigRequest();
		configRequest.setReportConfigPath(pathConfiguration + "/report.jrcfg.xml");
		ConfigResponse configResponse =  reportsGeneratorProxy.getReportConfig(configRequest);
		
		if (!configResponse.isOk()) {
			throw new SystemException(configResponse.getResultCode() + ". " + configResponse.getResultDescription());
		}
		
		// 2. Obtener los datos
		String dataXML = PrintToXML.GetDataString(instancia, configResponse.getConfigXMLData(), false);
		
		// 3. Generar el reporte
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setReportData(dataXML);
		reportRequest.setReportTemplatePath(pathConfiguration + "/report.jasper");
		ReportResponse reportResponse =  reportsGeneratorProxy.buildReport(reportRequest);

		if (!reportResponse.isOk()) {
			throw new SystemException(reportResponse.getResultCode() + ". " + reportResponse.getResultDescription());
		}

		return reportResponse.getReportData();
	}
}
