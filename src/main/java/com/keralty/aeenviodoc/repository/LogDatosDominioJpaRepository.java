package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.LogDatosDominio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDatosDominioJpaRepository extends JpaRepository<LogDatosDominio, Long>, LogDatosDominioJpaRepositoryCustom {

    List<LogDatosDominio> findByDominio(Dominio dominio);
}
