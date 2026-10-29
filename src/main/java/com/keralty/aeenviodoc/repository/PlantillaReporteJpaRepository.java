package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.PlantillaReporte;
import com.keralty.aeenviodoc.persistence.ReportesDominio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantillaReporteJpaRepository extends JpaRepository<PlantillaReporte, Long>, PlantillaReporteJpaRepositoryCustom {

    List<PlantillaReporte> findByReportesDominio(ReportesDominio reportesDominio);
}
