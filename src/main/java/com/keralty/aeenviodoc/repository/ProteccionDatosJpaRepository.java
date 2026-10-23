package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Productos;
import com.keralty.aeenviodoc.persistence.ProteccionDatos;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProteccionDatosJpaRepository extends JpaRepository<ProteccionDatos, Long>, ProteccionDatosJpaRepositoryCustom {

    List<ProteccionDatos> findByProductoT(Productos productoT);

    List<ProteccionDatos> findBySolicitud(Solicitud solicitud);
}
