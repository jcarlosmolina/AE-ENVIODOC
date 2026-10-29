package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.GrupoAsociado;
import com.keralty.aeenviodoc.persistence.Productos;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoAsociadoJpaRepository extends JpaRepository<GrupoAsociado, Long>, GrupoAsociadoJpaRepositoryCustom {

    List<GrupoAsociado> findBySolicitudes(Solicitud solicitudes);

    List<GrupoAsociado> findByContratosC(ContratoC contratosC);

    List<GrupoAsociado> findByProductoT(Productos productoT);
}
