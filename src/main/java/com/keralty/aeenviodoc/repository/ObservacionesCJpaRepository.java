package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.ObservacionesC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservacionesCJpaRepository extends JpaRepository<ObservacionesC, Long>, ObservacionesCJpaRepositoryCustom {

    List<ObservacionesC> findByContratoC(ContratoC contratoC);
}
