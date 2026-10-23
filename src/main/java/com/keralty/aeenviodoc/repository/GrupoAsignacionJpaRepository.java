package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.Funcionario;
import com.keralty.aeenviodoc.persistence.GrupoAsignacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoAsignacionJpaRepository extends JpaRepository<GrupoAsignacion, Long>, GrupoAsignacionJpaRepositoryCustom {

    List<GrupoAsignacion> findByDominioAsesores(Dominio dominioAsesores);

    List<GrupoAsignacion> findByDominioAreaMedica(Dominio dominioAreaMedica);

    List<GrupoAsignacion> findByFuncionario(Funcionario funcionario);
}
