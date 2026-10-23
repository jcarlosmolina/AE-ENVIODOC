package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AnexoC;
import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Eps;
import com.keralty.aeenviodoc.persistence.EstadoBeneficiario;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.oid.BeneficiarioCOid;
import com.keralty.aeenviodoc.persistence.Parentesco;
import com.keralty.aeenviodoc.persistence.PersonaC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioCJpaRepository extends JpaRepository<BeneficiarioC, BeneficiarioCOid>, BeneficiarioCJpaRepositoryCustom {

    List<BeneficiarioC> findByPersonaC(PersonaC personaC);

    List<BeneficiarioC> findByAnexosC(AnexoC anexosC);

    List<BeneficiarioC> findByContratoC(ContratoC contratoC);

    List<BeneficiarioC> findByFamiliasC(FamiliaC familiasC);

    List<BeneficiarioC> findByEPSC(Eps ePSC);

    List<BeneficiarioC> findByParentescoC(Parentesco parentescoC);

    List<BeneficiarioC> findByEstadoBeneficiarioC(EstadoBeneficiario estadoBeneficiarioC);
}
