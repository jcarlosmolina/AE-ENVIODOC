package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.EstadoBeneficiario;
import com.keralty.aeenviodoc.persistence.Parentesco;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import com.keralty.aeenviodoc.persistence.UsuarioNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioNovedadJpaRepository extends JpaRepository<UsuarioNovedad, Long>, UsuarioNovedadJpaRepositoryCustom {

    List<UsuarioNovedad> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<UsuarioNovedad> findByParentesco(Parentesco parentesco);

    List<UsuarioNovedad> findByEstadoBeneficiario(EstadoBeneficiario estadoBeneficiario);

    List<UsuarioNovedad> findByParentescoNuevo(Parentesco parentescoNuevo);

    List<UsuarioNovedad> findByTipoIdentificacionCD(TipoIdentificacion tipoIdentificacionCD);
}
