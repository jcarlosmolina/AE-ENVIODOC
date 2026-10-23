package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.oid.ProrrateoOid;
import com.keralty.aeenviodoc.persistence.Prorrateo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProrrateoJpaRepository extends JpaRepository<Prorrateo, ProrrateoOid>, ProrrateoJpaRepositoryCustom {

    List<Prorrateo> findByDominio(Dominio dominio);
}
