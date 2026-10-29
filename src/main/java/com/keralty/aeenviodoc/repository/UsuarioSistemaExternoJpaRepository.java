package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.UsuarioSistemaExterno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioSistemaExternoJpaRepository extends JpaRepository<UsuarioSistemaExterno, String>, UsuarioSistemaExternoJpaRepositoryCustom {
}
