package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.CausaExclusion;
import com.keralty.aeenviodoc.persistence.Novedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CausaExclusionJpaRepository extends JpaRepository<CausaExclusion, Long>, CausaExclusionJpaRepositoryCustom {

    List<CausaExclusion> findByNovedad(Novedad novedad);
}
