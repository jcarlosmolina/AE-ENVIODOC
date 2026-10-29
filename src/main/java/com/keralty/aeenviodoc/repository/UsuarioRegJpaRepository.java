package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.EstadoBeneficiario;
import com.keralty.aeenviodoc.persistence.oid.UsuarioRegOid;
import com.keralty.aeenviodoc.persistence.Parentesco;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.UsuarioReg;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRegJpaRepository extends JpaRepository<UsuarioReg, UsuarioRegOid>, UsuarioRegJpaRepositoryCustom {

    List<UsuarioReg> findBySolicitud(Solicitud solicitud);

    List<UsuarioReg> findByEstadoBeneficiario(EstadoBeneficiario estadoBeneficiario);

    List<UsuarioReg> findByParentesco(Parentesco parentesco);
}
