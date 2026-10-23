package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.oid.PregCuesMedCOid;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.PregCuesMedC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PregCuesMedCJpaRepository extends JpaRepository<PregCuesMedC, PregCuesMedCOid>, PregCuesMedCJpaRepositoryCustom {

    List<PregCuesMedC> findByPersonaC(PersonaC personaC);
}
