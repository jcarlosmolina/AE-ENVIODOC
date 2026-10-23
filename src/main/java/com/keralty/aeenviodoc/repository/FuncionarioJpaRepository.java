package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Funcionario;
import com.keralty.aeenviodoc.persistence.GrupoAsignacion;
import com.keralty.aeenviodoc.persistence.Rol;
import com.keralty.aeenviodoc.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioJpaRepository extends JpaRepository<Funcionario, Long>, FuncionarioJpaRepositoryCustom {

    List<Funcionario> findBySolicitudes(Solicitud solicitudes);

    List<Funcionario> findBySolicitudesAfiliacion(Solicitud solicitudesAfiliacion);

    List<Funcionario> findByGrupoAsignacion(GrupoAsignacion grupoAsignacion);

    List<Funcionario> findByRol(Rol rol);
}
