package com.keralty.aeenviodoc.reports;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/api/ReportBuilderApi")
@RegisterRestClient(configKey="ae-reportsgenerator")
@ClientHeaderParam(name = "Authorization", value = "Bearer {com.keralty.aeenviodoc.utils.TokenUtilities.getToken4aereportsgenerator}")
public interface ReportsGeneratorProxy {


    @POST
    @Path("/buildReport")
    ReportResponse buildReport(@RequestBody ReportRequest request);
    
    @POST
    @Path("/getReportConfig")
    ConfigResponse getReportConfig(@RequestBody ConfigRequest request);

   
}
