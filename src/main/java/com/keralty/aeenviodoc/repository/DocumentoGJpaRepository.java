package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.DocumentoG;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoGJpaRepository extends JpaRepository<DocumentoG, Long>, DocumentoGJpaRepositoryCustom {

    List<DocumentoG> findBySolicitud(Solicitud solicitud);

    List<DocumentoG> findByPersona(Persona persona);
}
