package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Contratante;
import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratanteJpaRepository extends JpaRepository<Contratante, Long>, ContratanteJpaRepositoryCustom {

    List<Contratante> findBySolicitud(Solicitud solicitud);

    List<Contratante> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<Contratante> findByDepartamentoNac(Departamento departamentoNac);

    List<Contratante> findByMunicipioNac(Municipio municipioNac);

    List<Contratante> findByDepartamentoRes(Departamento departamentoRes);

    List<Contratante> findByMunicipioRes(Municipio municipioRes);
}
