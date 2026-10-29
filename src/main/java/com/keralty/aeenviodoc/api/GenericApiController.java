package com.keralty.aeenviodoc.api;

import com.keralty.aeenviodoc.service.GenericService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/api/GenericApiController")
@RequestScoped
public class GenericApiController {
	
    GenericService genericService;

    @Inject
	public GenericApiController(GenericService genericService) {
        this.genericService = genericService;
    }

    @Path("/isSolicitudNovedadJpaRepositoryAvailable")
    public boolean isSolicitudNovedadJpaRepositoryAvailable() {
        return genericService.getSolicitudNovedadJpaRepository() != null;
    }
    @Path("/isFamiliaNovedadJpaRepositoryAvailable")
    public boolean isFamiliaNovedadJpaRepositoryAvailable() {
        return genericService.getFamiliaNovedadJpaRepository() != null;
    }
    @Path("/isUsuarioNovedadJpaRepositoryAvailable")
    public boolean isUsuarioNovedadJpaRepositoryAvailable() {
        return genericService.getUsuarioNovedadJpaRepository() != null;
    }
    @Path("/isConfiguracionANSJpaRepositoryAvailable")
    public boolean isConfiguracionANSJpaRepositoryAvailable() {
        return genericService.getConfiguracionANSJpaRepository() != null;
    }
}
