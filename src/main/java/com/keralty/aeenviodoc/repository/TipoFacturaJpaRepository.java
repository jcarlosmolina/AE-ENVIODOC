package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.TipoFactura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoFacturaJpaRepository extends JpaRepository<TipoFactura, String>, TipoFacturaJpaRepositoryCustom {

    List<TipoFactura> findBySolicitudes(Solicitud solicitudes);
}
