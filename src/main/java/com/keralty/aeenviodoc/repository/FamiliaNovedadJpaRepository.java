package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.FamiliaNovedad;
import com.keralty.aeenviodoc.persistence.Firma;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaNovedadJpaRepository extends JpaRepository<FamiliaNovedad, Long>, FamiliaNovedadJpaRepositoryCustom {

    List<FamiliaNovedad> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<FamiliaNovedad> findByFirma(Firma firma);
}
