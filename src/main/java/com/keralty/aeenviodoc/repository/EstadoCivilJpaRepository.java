package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.EstadoCivil;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.PersonaC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCivilJpaRepository extends JpaRepository<EstadoCivil, Long>, EstadoCivilJpaRepositoryCustom {

    List<EstadoCivil> findByPersonas(Persona personas);

    List<EstadoCivil> findByPersonasC(PersonaC personasC);
}
