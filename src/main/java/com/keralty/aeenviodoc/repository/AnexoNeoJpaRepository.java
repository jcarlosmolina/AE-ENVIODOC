package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AnexoNeo;
import com.keralty.aeenviodoc.persistence.Periodicidad;
import com.keralty.aeenviodoc.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoNeoJpaRepository extends JpaRepository<AnexoNeo, Long>, AnexoNeoJpaRepositoryCustom {

    List<AnexoNeo> findByUsuario(Usuario usuario);

    List<AnexoNeo> findByPeriodicidad(Periodicidad periodicidad);
}
