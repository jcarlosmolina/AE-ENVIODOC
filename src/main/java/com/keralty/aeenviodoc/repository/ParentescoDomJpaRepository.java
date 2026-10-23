package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.oid.ParentescoDomOid;
import com.keralty.aeenviodoc.persistence.Parentesco;
import com.keralty.aeenviodoc.persistence.ParentescoDom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentescoDomJpaRepository extends JpaRepository<ParentescoDom, ParentescoDomOid>, ParentescoDomJpaRepositoryCustom {

    List<ParentescoDom> findByDominio(Dominio dominio);

    List<ParentescoDom> findByParentesco(Parentesco parentesco);
}
