package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.Firma;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.NovedadCT;
import com.keralty.aeenviodoc.persistence.Pais;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovedadCTJpaRepository extends JpaRepository<NovedadCT, Long>, NovedadCTJpaRepositoryCustom {

    List<NovedadCT> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<NovedadCT> findByFirma(Firma firma);

    List<NovedadCT> findByMunicipioNac(Municipio municipioNac);

    List<NovedadCT> findByMunicipioRes(Municipio municipioRes);

    List<NovedadCT> findByDepartamentoNac(Departamento departamentoNac);

    List<NovedadCT> findByDepartamentoRes(Departamento departamentoRes);

    List<NovedadCT> findByPais(Pais pais);
}
