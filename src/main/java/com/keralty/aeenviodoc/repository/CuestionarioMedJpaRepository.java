package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.CuestionarioMed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuestionarioMedJpaRepository extends JpaRepository<CuestionarioMed, Long>, CuestionarioMedJpaRepositoryCustom {
}
