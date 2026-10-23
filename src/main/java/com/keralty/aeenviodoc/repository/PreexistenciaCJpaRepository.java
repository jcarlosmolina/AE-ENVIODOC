package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.oid.PreexistenciaCOid;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.PreexistenciaC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreexistenciaCJpaRepository extends JpaRepository<PreexistenciaC, PreexistenciaCOid>, PreexistenciaCJpaRepositoryCustom {

    List<PreexistenciaC> findByPersonaC(PersonaC personaC);
}
