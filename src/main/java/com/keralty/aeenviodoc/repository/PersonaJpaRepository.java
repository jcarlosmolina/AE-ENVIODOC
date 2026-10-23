package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.Direccion;
import com.keralty.aeenviodoc.persistence.DocumentoG;
import com.keralty.aeenviodoc.persistence.EstadoCivil;
import com.keralty.aeenviodoc.persistence.EstadoPersona;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import com.keralty.aeenviodoc.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJpaRepository extends JpaRepository<Persona, Long>, PersonaJpaRepositoryCustom {

    List<Persona> findBySolicitudesContratante(Solicitud solicitudesContratante);

    List<Persona> findBySolicitudesTitular(Solicitud solicitudesTitular);

    List<Persona> findByDirecciones(Direccion direcciones);

    List<Persona> findByUsuarios(Usuario usuarios);

    List<Persona> findByDocumentosG(DocumentoG documentosG);

    List<Persona> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<Persona> findByDepartamentoNacimiento(Departamento departamentoNacimiento);

    List<Persona> findByMunicipioNacimiento(Municipio municipioNacimiento);

    List<Persona> findByEstadoCivil(EstadoCivil estadoCivil);

    List<Persona> findByEstadoPersona(EstadoPersona estadoPersona);
}
