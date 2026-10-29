package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Pregunta;
import com.keralty.aeenviodoc.persistence.PreguntaP;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaJpaRepository extends JpaRepository<Pregunta, Long>, PreguntaJpaRepositoryCustom {

    List<Pregunta> findByPreguntasPersona(PreguntaP preguntasPersona);
}
