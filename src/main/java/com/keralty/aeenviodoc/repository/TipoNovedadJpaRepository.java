package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Novedad;
import com.keralty.aeenviodoc.persistence.TipoNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoNovedadJpaRepository extends JpaRepository<TipoNovedad, Long>, TipoNovedadJpaRepositoryCustom {

    List<TipoNovedad> findByNovedades(Novedad novedades);
}
