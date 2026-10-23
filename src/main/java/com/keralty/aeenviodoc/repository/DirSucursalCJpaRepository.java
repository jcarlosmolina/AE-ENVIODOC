package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.Pais;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.TelefonoC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirSucursalCJpaRepository extends JpaRepository<DirSucursalC, Long>, DirSucursalCJpaRepositoryCustom {

    List<DirSucursalC> findByTelefonosC(TelefonoC telefonosC);

    List<DirSucursalC> findByPersonaC(PersonaC personaC);

    List<DirSucursalC> findByMunicipioC(Municipio municipioC);

    List<DirSucursalC> findByDepartamentoC(Departamento departamentoC);

    List<DirSucursalC> findByPaisC(Pais paisC);
}
