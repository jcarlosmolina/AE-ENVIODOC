package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Firma;
import com.keralty.aeenviodoc.persistence.Funcionario;
import com.keralty.aeenviodoc.persistence.GrupoAsociado;
import com.keralty.aeenviodoc.persistence.Novedad;
import com.keralty.aeenviodoc.persistence.Periodicidad;
import com.keralty.aeenviodoc.persistence.ProteccionDatos;
import com.keralty.aeenviodoc.persistence.SolicitudNovedad;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudNovedadJpaRepository extends JpaRepository<SolicitudNovedad, Long>, SolicitudNovedadJpaRepositoryCustom {

    List<SolicitudNovedad> findByNovedad(Novedad novedad);

    List<SolicitudNovedad> findByProteccionDatos(ProteccionDatos proteccionDatos);

    List<SolicitudNovedad> findByTipoIdentContratante(TipoIdentificacion tipoIdentContratante);

    List<SolicitudNovedad> findByGrupoAsociado(GrupoAsociado grupoAsociado);

    List<SolicitudNovedad> findByPeriodicidad(Periodicidad periodicidad);

    List<SolicitudNovedad> findByAgente(Funcionario agente);

    List<SolicitudNovedad> findByPeriodicidadNueva(Periodicidad periodicidadNueva);

    List<SolicitudNovedad> findByUsrAfiliacion(Funcionario usrAfiliacion);

    List<SolicitudNovedad> findByFirma(Firma firma);
}
