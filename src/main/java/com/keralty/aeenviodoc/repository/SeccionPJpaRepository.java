package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.CuestionarioP;
import com.keralty.aeenviodoc.persistence.PreguntaP;
import com.keralty.aeenviodoc.persistence.SeccionP;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeccionPJpaRepository extends JpaRepository<SeccionP, Long>, SeccionPJpaRepositoryCustom {

    List<SeccionP> findByPreguntas(PreguntaP preguntas);

    List<SeccionP> findByCuestionario(CuestionarioP cuestionario);
}
