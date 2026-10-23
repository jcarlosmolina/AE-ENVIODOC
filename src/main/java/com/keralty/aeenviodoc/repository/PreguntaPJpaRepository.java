package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.PreguntaP;
import com.keralty.aeenviodoc.persistence.SeccionP;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaPJpaRepository extends JpaRepository<PreguntaP, Long>, PreguntaPJpaRepositoryCustom {

    List<PreguntaP> findBySeccion(SeccionP seccion);
}
