package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.Direccion;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import com.keralty.aeenviodoc.persistence.Titular;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitularJpaRepository extends JpaRepository<Titular, Long>, TitularJpaRepositoryCustom {

    List<Titular> findByDireccion(Direccion direccion);

    List<Titular> findBySolicitud(Solicitud solicitud);

    List<Titular> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<Titular> findByDepartamentoNac(Departamento departamentoNac);

    List<Titular> findByMunicipioNac(Municipio municipioNac);
}
