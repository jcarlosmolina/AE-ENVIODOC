package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.Funcionario;
import com.keralty.aeenviodoc.persistence.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolJpaRepository extends JpaRepository<Rol, Long>, RolJpaRepositoryCustom {

    List<Rol> findByAgentes(Funcionario agentes);

    List<Rol> findByDominios(Dominio dominios);
}
