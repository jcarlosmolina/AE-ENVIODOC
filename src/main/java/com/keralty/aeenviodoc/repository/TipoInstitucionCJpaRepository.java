package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.TipoInstitucionC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoInstitucionCJpaRepository extends JpaRepository<TipoInstitucionC, Long>, TipoInstitucionCJpaRepositoryCustom {

    List<TipoInstitucionC> findByPersonasC(PersonaC personasC);
}
