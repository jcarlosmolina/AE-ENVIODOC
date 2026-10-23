package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.Companyia;
import com.keralty.aeenviodoc.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyiaJpaRepository extends JpaRepository<Companyia, Long>, CompanyiaJpaRepositoryCustom {

    List<Companyia> findByAtributosUsuario(Usuario atributosUsuario);

    List<Companyia> findByBeneficiariosC(BeneficiarioC beneficiariosC);
}
