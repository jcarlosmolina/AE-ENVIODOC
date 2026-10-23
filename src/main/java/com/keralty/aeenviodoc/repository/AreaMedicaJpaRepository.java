package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AreaMedica;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaMedicaJpaRepository extends JpaRepository<AreaMedica, Long>, AreaMedicaJpaRepositoryCustom {

    List<AreaMedica> findBySolicitudesArMe(Solicitud solicitudesArMe);
}
