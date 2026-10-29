package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.oid.PreexistenciaOid;
import com.keralty.aeenviodoc.persistence.Preexistencia;
import com.keralty.aeenviodoc.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreexistenciaJpaRepository extends JpaRepository<Preexistencia, PreexistenciaOid>, PreexistenciaJpaRepositoryCustom {

    List<Preexistencia> findByUsuario(Usuario usuario);
}
