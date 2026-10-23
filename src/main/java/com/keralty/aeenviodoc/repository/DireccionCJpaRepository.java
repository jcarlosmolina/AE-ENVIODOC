package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.Pais;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.TelefonoC;
import com.keralty.aeenviodoc.persistence.TipoDireccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionCJpaRepository extends JpaRepository<DireccionC, Long>, DireccionCJpaRepositoryCustom {

    List<DireccionC> findByTelefonosC(TelefonoC telefonosC);

    List<DireccionC> findByPersonaC(PersonaC personaC);

    List<DireccionC> findByContratoC(ContratoC contratoC);

    List<DireccionC> findByTipoDireccionC(TipoDireccion tipoDireccionC);

    List<DireccionC> findByDepartamentoC(Departamento departamentoC);

    List<DireccionC> findByMunicipioC(Municipio municipioC);

    List<DireccionC> findByPaisC(Pais paisC);
}
