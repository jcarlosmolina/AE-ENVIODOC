package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AnexoC;
import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.Periodicidad;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoCJpaRepository extends JpaRepository<AnexoC, Long>, AnexoCJpaRepositoryCustom {

    List<AnexoC> findByBeneficiarioC(BeneficiarioC beneficiarioC);

    List<AnexoC> findByPeriodicidadC(Periodicidad periodicidadC);

    List<AnexoC> findByTipoIdentificacionC(TipoIdentificacion tipoIdentificacionC);
}
