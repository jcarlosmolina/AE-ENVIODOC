package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.LogEstado;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEstadoJpaRepository extends JpaRepository<LogEstado, Long>, LogEstadoJpaRepositoryCustom {

    List<LogEstado> findBySolicitud(Solicitud solicitud);
}
