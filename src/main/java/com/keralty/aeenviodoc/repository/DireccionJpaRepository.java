package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.Direccion;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.Pais;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.TipoDireccion;
import com.keralty.aeenviodoc.persistence.Titular;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionJpaRepository extends JpaRepository<Direccion, Long>, DireccionJpaRepositoryCustom {

    List<Direccion> findByPersona(Persona persona);

    List<Direccion> findByTipoDireccion(TipoDireccion tipoDireccion);

    List<Direccion> findByMunicipio(Municipio municipio);

    List<Direccion> findByDepartamento(Departamento departamento);

    List<Direccion> findByPais(Pais pais);

    List<Direccion> findByContrato(Solicitud contrato);

    List<Direccion> findByTitular(Titular titular);
}
