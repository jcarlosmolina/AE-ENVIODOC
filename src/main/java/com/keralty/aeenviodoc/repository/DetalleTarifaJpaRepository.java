package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.DetalleTarifa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleTarifaJpaRepository extends JpaRepository<DetalleTarifa, Long>, DetalleTarifaJpaRepositoryCustom {
}
