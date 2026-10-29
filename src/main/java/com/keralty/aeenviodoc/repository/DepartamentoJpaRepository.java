package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Contratante;
import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.Direccion;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.Titular;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoJpaRepository extends JpaRepository<Departamento, Long>, DepartamentoJpaRepositoryCustom {

    List<Departamento> findByPersonas(Persona personas);

    List<Departamento> findByMunicipios(Municipio municipios);

    List<Departamento> findByPersonasC(PersonaC personasC);

    List<Departamento> findByDireccionesC(DireccionC direccionesC);

    List<Departamento> findByDirecciones(Direccion direcciones);

    List<Departamento> findByContratantes(Contratante contratantes);

    List<Departamento> findByContratantesRes(Contratante contratantesRes);

    List<Departamento> findByDirSucursalC(DirSucursalC dirSucursalC);

    List<Departamento> findByTitulares(Titular titulares);
}
