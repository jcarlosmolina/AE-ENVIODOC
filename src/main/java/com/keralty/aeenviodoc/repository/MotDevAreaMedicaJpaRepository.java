package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.MotDevAreaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotDevAreaMedicaJpaRepository extends JpaRepository<MotDevAreaMedica, Long>, MotDevAreaMedicaJpaRepositoryCustom {
}
