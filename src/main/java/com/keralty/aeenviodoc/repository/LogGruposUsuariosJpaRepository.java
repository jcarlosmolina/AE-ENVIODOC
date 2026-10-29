package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.LogGruposUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogGruposUsuariosJpaRepository extends JpaRepository<LogGruposUsuarios, Long>, LogGruposUsuariosJpaRepositoryCustom {

    List<LogGruposUsuarios> findByDominio(Dominio dominio);
}
