package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Afiliaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AfiliacionesJpaRepository extends JpaRepository<Afiliaciones, Long>, AfiliacionesJpaRepositoryCustom {
}
