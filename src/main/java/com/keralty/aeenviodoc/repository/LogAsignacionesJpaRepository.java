package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.LogAsignaciones;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogAsignacionesJpaRepository extends JpaRepository<LogAsignaciones, Long>, LogAsignacionesJpaRepositoryCustom {

    List<LogAsignaciones> findBySolicitud(Solicitud solicitud);
}
