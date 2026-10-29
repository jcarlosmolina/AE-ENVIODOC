package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.persistence.EstadoCivil;
import com.keralty.aeenviodoc.persistence.EstadoPersona;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.PreexistenciaC;
import com.keralty.aeenviodoc.persistence.PregCuesMedC;
import com.keralty.aeenviodoc.persistence.ProteccionDatosC;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaCJpaRepository extends JpaRepository<PersonaC, Long>, PersonaCJpaRepositoryCustom {

    List<PersonaC> findByDireccionesC(DireccionC direccionesC);

    List<PersonaC> findByProteccionesDatosC(ProteccionDatosC proteccionesDatosC);

    List<PersonaC> findByPregsCuesMedC(PregCuesMedC pregsCuesMedC);

    List<PersonaC> findByDirSucursalesC(DirSucursalC dirSucursalesC);

    List<PersonaC> findByPreexistenciasC(PreexistenciaC preexistenciasC);

    List<PersonaC> findByEstadoCivilC(EstadoCivil estadoCivilC);

    List<PersonaC> findByEstadoPersonaC(EstadoPersona estadoPersonaC);

    List<PersonaC> findByTipoIdentificacionC(TipoIdentificacion tipoIdentificacionC);

    List<PersonaC> findByMunicipioCNac(Municipio municipioCNac);

    List<PersonaC> findByDepartamentoCNac(Departamento departamentoCNac);

    List<PersonaC> findByContratosC(ContratoC contratosC);

    List<PersonaC> findByFamiliasC(FamiliaC familiasC);

    List<PersonaC> findByBeneficiariosC(BeneficiarioC beneficiariosC);
}
