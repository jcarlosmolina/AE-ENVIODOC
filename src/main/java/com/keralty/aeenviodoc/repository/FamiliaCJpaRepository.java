package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.oid.FamiliaCOid;
import com.keralty.aeenviodoc.persistence.PersonaC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaCJpaRepository extends JpaRepository<FamiliaC, FamiliaCOid>, FamiliaCJpaRepositoryCustom {

    List<FamiliaC> findByPersonaCTitular(PersonaC personaCTitular);

    List<FamiliaC> findByBeneficiariosC(BeneficiarioC beneficiariosC);

    List<FamiliaC> findByContratoC(ContratoC contratoC);

    List<FamiliaC> findByMunicipioC(Municipio municipioC);
}
