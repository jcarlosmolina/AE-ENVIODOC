package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeccionJpaRepository extends JpaRepository<Seccion, Long>, SeccionJpaRepositoryCustom {
}
