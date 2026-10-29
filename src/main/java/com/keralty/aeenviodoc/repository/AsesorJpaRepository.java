package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AgentexProducto;
import com.keralty.aeenviodoc.persistence.Asesor;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsesorJpaRepository extends JpaRepository<Asesor, Long>, AsesorJpaRepositoryCustom {

    List<Asesor> findByContratosC(ContratoC contratosC);

    List<Asesor> findByAgentexProducto(AgentexProducto agentexProducto);

    List<Asesor> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);
}
