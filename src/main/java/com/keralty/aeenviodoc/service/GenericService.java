package com.keralty.aeenviodoc.service;

import com.keralty.aeenviodoc.repository.ConfiguracionANSJpaRepository;
import com.keralty.aeenviodoc.repository.FamiliaNovedadJpaRepository;
import com.keralty.aeenviodoc.repository.SolicitudNovedadJpaRepository;
import com.keralty.aeenviodoc.repository.UsuarioNovedadJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class GenericService {

    private SolicitudNovedadJpaRepository solicitudNovedadJpaRepository;

    private FamiliaNovedadJpaRepository familiaNovedadJpaRepository;

    private UsuarioNovedadJpaRepository usuarioNovedadJpaRepository;

    private ConfiguracionANSJpaRepository configuracionANSJpaRepository;
	
	@Inject
	public GenericService(
            SolicitudNovedadJpaRepository solicitudNovedadJpaRepository,
            FamiliaNovedadJpaRepository familiaNovedadJpaRepository,
            UsuarioNovedadJpaRepository usuarioNovedadJpaRepository,
            ConfiguracionANSJpaRepository configuracionANSJpaRepository
			) {
        this.solicitudNovedadJpaRepository = solicitudNovedadJpaRepository;
        this.familiaNovedadJpaRepository = familiaNovedadJpaRepository;
        this.usuarioNovedadJpaRepository = usuarioNovedadJpaRepository;
        this.configuracionANSJpaRepository = configuracionANSJpaRepository;
	}

	public SolicitudNovedadJpaRepository getSolicitudNovedadJpaRepository() {
		return this.solicitudNovedadJpaRepository;
	}
	public FamiliaNovedadJpaRepository getFamiliaNovedadJpaRepository() {
		return this.familiaNovedadJpaRepository;
	}
	public UsuarioNovedadJpaRepository getUsuarioNovedadJpaRepository() {
		return this.usuarioNovedadJpaRepository;
	}
	public ConfiguracionANSJpaRepository getConfiguracionANSJpaRepository() {
		return this.configuracionANSJpaRepository;
	}
}
