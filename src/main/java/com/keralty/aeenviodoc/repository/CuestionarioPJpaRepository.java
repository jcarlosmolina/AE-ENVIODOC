package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.CuestionarioP;
import com.keralty.aeenviodoc.persistence.SeccionP;
import com.keralty.aeenviodoc.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuestionarioPJpaRepository extends JpaRepository<CuestionarioP, Long>, CuestionarioPJpaRepositoryCustom {

    List<CuestionarioP> findBySecciones(SeccionP secciones);

    List<CuestionarioP> findByUsuario(Usuario usuario);
}
