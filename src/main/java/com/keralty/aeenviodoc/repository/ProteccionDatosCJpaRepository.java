package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.oid.ProteccionDatosCOid;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.Productos;
import com.keralty.aeenviodoc.persistence.ProteccionDatosC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProteccionDatosCJpaRepository extends JpaRepository<ProteccionDatosC, ProteccionDatosCOid>, ProteccionDatosCJpaRepositoryCustom {

    List<ProteccionDatosC> findByPersonaC(PersonaC personaC);

    List<ProteccionDatosC> findByProductoC(Productos productoC);
}
