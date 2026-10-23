package com.keralty.aeenviodoc.service;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.keralty.aeenviodoc.viewmodel.ServiceResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TENVIARDOCAUTOAUXRequest;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/api/SolicitudApi")
@RegisterRestClient(configKey="ae-enviodoc")
@ClientHeaderParam(name = "Authorization", value = "Bearer {com.keralty.aeenviodoc.utils.TokenUtilities.getToken4aeenviodoc}")
public interface SolicitudItSelf {

	
	// Definimos el mismo interfaz pero asíncrono
	@POST
    @Path(value = "/TENVIARDOCAUTOAUX_Async")
    public CompletionStage<ServiceResponse> solicitudTENVIARDOCAUTOAUX_Async(TENVIARDOCAUTOAUXRequest request);
}
