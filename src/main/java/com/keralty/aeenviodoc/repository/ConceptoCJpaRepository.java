package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.ConceptoC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConceptoCJpaRepository extends JpaRepository<ConceptoC, Long>, ConceptoCJpaRepositoryCustom {

    List<ConceptoC> findByBeneficiarioC(BeneficiarioC beneficiarioC);
}
