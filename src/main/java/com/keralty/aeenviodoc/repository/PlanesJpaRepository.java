package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Aesolicitudes;
import com.keralty.aeenviodoc.persistence.Afiliaciones;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.GrupoAsociado;
import com.keralty.aeenviodoc.persistence.Planes;
import com.keralty.aeenviodoc.persistence.Productos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanesJpaRepository extends JpaRepository<Planes, Long>, PlanesJpaRepositoryCustom {

    List<Planes> findByDominios(Dominio dominios);

    List<Planes> findByGruposAsociados(GrupoAsociado gruposAsociados);

    List<Planes> findByAESOLICITUDES(Aesolicitudes aESOLICITUDES);

    List<Planes> findByContratosC(ContratoC contratosC);

    List<Planes> findByAfiliaciones(Afiliaciones afiliaciones);

    List<Planes> findByProductoT(Productos productoT);
}
