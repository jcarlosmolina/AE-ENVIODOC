package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AnexoMat;
import com.keralty.aeenviodoc.persistence.Periodicidad;
import com.keralty.aeenviodoc.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoMatJpaRepository extends JpaRepository<AnexoMat, Long>, AnexoMatJpaRepositoryCustom {

    List<AnexoMat> findByUsuario(Usuario usuario);

    List<AnexoMat> findByPeriodicidad(Periodicidad periodicidad);
}
