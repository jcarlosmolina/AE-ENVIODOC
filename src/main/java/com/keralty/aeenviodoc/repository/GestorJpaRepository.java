package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestorJpaRepository extends JpaRepository<Gestor, Long>, GestorJpaRepositoryCustom {
}
