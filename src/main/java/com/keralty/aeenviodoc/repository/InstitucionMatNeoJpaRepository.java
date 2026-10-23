package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AnexoMat;
import com.keralty.aeenviodoc.persistence.AnexoNeo;
import com.keralty.aeenviodoc.persistence.InstitucionMatNeo;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitucionMatNeoJpaRepository extends JpaRepository<InstitucionMatNeo, String>, InstitucionMatNeoJpaRepositoryCustom {

    List<InstitucionMatNeo> findByAnexoMat(AnexoMat anexoMat);

    List<InstitucionMatNeo> findByAnexoNeo(AnexoNeo anexoNeo);

    List<InstitucionMatNeo> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<InstitucionMatNeo> findByMunicipio(Municipio municipio);
}
