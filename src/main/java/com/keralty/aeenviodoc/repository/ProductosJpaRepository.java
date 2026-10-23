package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Aesolicitudes;
import com.keralty.aeenviodoc.persistence.AgentexProducto;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.GrupoAsociado;
import com.keralty.aeenviodoc.persistence.Productos;
import com.keralty.aeenviodoc.persistence.ProteccionDatos;
import com.keralty.aeenviodoc.persistence.ProteccionDatosC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosJpaRepository extends JpaRepository<Productos, Long>, ProductosJpaRepositoryCustom {

    List<Productos> findByDominios(Dominio dominios);

    List<Productos> findByGruposAsociados(GrupoAsociado gruposAsociados);

    List<Productos> findByAESOLICITUDES(Aesolicitudes aESOLICITUDES);

    List<Productos> findByProteccionDatosC(ProteccionDatosC proteccionDatosC);

    List<Productos> findByProteccionesDatos(ProteccionDatos proteccionesDatos);

    List<Productos> findByContratosC(ContratoC contratosC);

    List<Productos> findByAgentexProducto(AgentexProducto agentexProducto);
}
