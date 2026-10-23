package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Contratante;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.Direccion;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.Titular;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioJpaRepository extends JpaRepository<Municipio, Long>, MunicipioJpaRepositoryCustom {

    List<Municipio> findByPersonas(Persona personas);

    List<Municipio> findByPersonasC(PersonaC personasC);

    List<Municipio> findByDireccionesC(DireccionC direccionesC);

    List<Municipio> findByDirecciones(Direccion direcciones);

    List<Municipio> findByContratantes(Contratante contratantes);

    List<Municipio> findByContratanteRes(Contratante contratanteRes);

    List<Municipio> findByContratosC(ContratoC contratosC);

    List<Municipio> findByDirSucursalC(DirSucursalC dirSucursalC);

    List<Municipio> findByFamiliaC(FamiliaC familiaC);

    List<Municipio> findByTitulares(Titular titulares);

    List<Municipio> findByDepartamento(Departamento departamento);
}
