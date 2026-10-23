package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.Eps;
import com.keralty.aeenviodoc.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpsJpaRepository extends JpaRepository<Eps, Long>, EpsJpaRepositoryCustom {

    List<Eps> findByAtributosUsuario(Usuario atributosUsuario);

    List<Eps> findByBeneficiariosC(BeneficiarioC beneficiariosC);
}
