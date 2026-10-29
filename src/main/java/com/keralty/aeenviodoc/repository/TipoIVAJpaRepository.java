package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Productos;
import com.keralty.aeenviodoc.persistence.TipoIVA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoIVAJpaRepository extends JpaRepository<TipoIVA, Long>, TipoIVAJpaRepositoryCustom {

    List<TipoIVA> findByProductoT(Productos productoT);
}
