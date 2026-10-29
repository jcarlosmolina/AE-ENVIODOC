package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.Parentesco;
import com.keralty.aeenviodoc.persistence.ParentescoDom;
import com.keralty.aeenviodoc.persistence.Usuario;
import com.keralty.aeenviodoc.persistence.UsuarioReg;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentescoJpaRepository extends JpaRepository<Parentesco, Long>, ParentescoJpaRepositoryCustom {

    List<Parentesco> findByAtributosUsuario(Usuario atributosUsuario);

    List<Parentesco> findByBeneficiariosC(BeneficiarioC beneficiariosC);

    List<Parentesco> findByUsuariosReg(UsuarioReg usuariosReg);

    List<Parentesco> findByParentescosDom(ParentescoDom parentescosDom);
}
