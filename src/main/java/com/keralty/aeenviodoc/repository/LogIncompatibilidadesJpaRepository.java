package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.LogIncompatibilidades;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogIncompatibilidadesJpaRepository extends JpaRepository<LogIncompatibilidades, Long>, LogIncompatibilidadesJpaRepositoryCustom {

    List<LogIncompatibilidades> findByDominio(Dominio dominio);
}
