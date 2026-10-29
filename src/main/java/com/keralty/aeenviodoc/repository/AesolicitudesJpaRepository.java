package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Aesolicitudes;
import com.keralty.aeenviodoc.persistence.Productos;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AesolicitudesJpaRepository extends JpaRepository<Aesolicitudes, Long>, AesolicitudesJpaRepositoryCustom {

    List<Aesolicitudes> findBySolicitud(Solicitud solicitud);

    List<Aesolicitudes> findByProductoT(Productos productoT);
}
