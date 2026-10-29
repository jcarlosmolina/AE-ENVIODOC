package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.TipoEntidadC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEntidadCJpaRepository extends JpaRepository<TipoEntidadC, Long>, TipoEntidadCJpaRepositoryCustom {

    List<TipoEntidadC> findByPersonasC(PersonaC personasC);
}
