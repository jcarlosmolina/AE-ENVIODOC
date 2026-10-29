package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AgentexProducto;
import com.keralty.aeenviodoc.persistence.Asesor;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.oid.AgentexProductoOid;
import com.keralty.aeenviodoc.persistence.Productos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentexProductoJpaRepository extends JpaRepository<AgentexProducto, AgentexProductoOid>, AgentexProductoJpaRepositoryCustom {

    List<AgentexProducto> findByProductoT(Productos productoT);

    List<AgentexProducto> findByAsesor(Asesor asesor);

    List<AgentexProducto> findByDominios(Dominio dominios);
}
