package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.EstadoPersona;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.PersonaC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoPersonaJpaRepository extends JpaRepository<EstadoPersona, Long>, EstadoPersonaJpaRepositoryCustom {

    List<EstadoPersona> findByPersonas(Persona personas);

    List<EstadoPersona> findByPersonasC(PersonaC personasC);
}
