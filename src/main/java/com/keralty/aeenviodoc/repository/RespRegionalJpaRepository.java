package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Firma;
import com.keralty.aeenviodoc.persistence.RespRegional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespRegionalJpaRepository extends JpaRepository<RespRegional, Long>, RespRegionalJpaRepositoryCustom {

    List<RespRegional> findByFirma(Firma firma);
}
