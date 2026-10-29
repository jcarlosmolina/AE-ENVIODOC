package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Causal;
import com.keralty.aeenviodoc.persistence.Novedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CausalJpaRepository extends JpaRepository<Causal, Long>, CausalJpaRepositoryCustom {

    List<Causal> findByNovedad(Novedad novedad);
}
