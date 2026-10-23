package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.ConfiguracionANS;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.Novedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracionANSJpaRepository extends JpaRepository<ConfiguracionANS, Long>, ConfiguracionANSJpaRepositoryCustom {

    List<ConfiguracionANS> findByDominio(Dominio dominio);

    List<ConfiguracionANS> findByNovedad(Novedad novedad);
}
