package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AnexoMat;
import com.keralty.aeenviodoc.persistence.AnexoNeo;
import com.keralty.aeenviodoc.persistence.CuestionarioP;
import com.keralty.aeenviodoc.persistence.Eps;
import com.keralty.aeenviodoc.persistence.EstadoBeneficiario;
import com.keralty.aeenviodoc.persistence.oid.UsuarioOid;
import com.keralty.aeenviodoc.persistence.Parentesco;
import com.keralty.aeenviodoc.persistence.Persona;
import com.keralty.aeenviodoc.persistence.Preexistencia;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import com.keralty.aeenviodoc.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, UsuarioOid>, UsuarioJpaRepositoryCustom {

    List<Usuario> findByCuestionarioP(CuestionarioP cuestionarioP);

    List<Usuario> findByAnexoMat(AnexoMat anexoMat);

    List<Usuario> findByAnexoNeo(AnexoNeo anexoNeo);

    List<Usuario> findByPreexistencias(Preexistencia preexistencias);

    List<Usuario> findBySolicitud(Solicitud solicitud);

    List<Usuario> findByUsuarioPersona(Persona usuarioPersona);

    List<Usuario> findByEps(Eps eps);

    List<Usuario> findByParentesco(Parentesco parentesco);

    List<Usuario> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<Usuario> findByEstadoBeneficiario(EstadoBeneficiario estadoBeneficiario);
}
