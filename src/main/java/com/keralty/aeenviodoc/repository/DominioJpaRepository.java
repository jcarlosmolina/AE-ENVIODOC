package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AgentexProducto;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.FormaPago;
import com.keralty.aeenviodoc.persistence.GrupoAsignacion;
import com.keralty.aeenviodoc.persistence.LogDatosDominio;
import com.keralty.aeenviodoc.persistence.LogGruposUsuarios;
import com.keralty.aeenviodoc.persistence.LogIncompatibilidades;
import com.keralty.aeenviodoc.persistence.Novedad;
import com.keralty.aeenviodoc.persistence.ParentescoDom;
import com.keralty.aeenviodoc.persistence.Periodicidad;
import com.keralty.aeenviodoc.persistence.PlantillaEmail;
import com.keralty.aeenviodoc.persistence.Productos;
import com.keralty.aeenviodoc.persistence.Prorrateo;
import com.keralty.aeenviodoc.persistence.ReportesDominio;
import com.keralty.aeenviodoc.persistence.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DominioJpaRepository extends JpaRepository<Dominio, Long>, DominioJpaRepositoryCustom {

    List<Dominio> findByDominiosConIncompatibilidad(Dominio dominiosConIncompatibilidad);

    List<Dominio> findByParentescosDom(ParentescoDom parentescosDom);

    List<Dominio> findByAgentexProducto(AgentexProducto agentexProducto);

    List<Dominio> findByProrrateos(Prorrateo prorrateos);

    List<Dominio> findByLogGruposUsuarios(LogGruposUsuarios logGruposUsuarios);

    List<Dominio> findByReportesDominio(ReportesDominio reportesDominio);

    List<Dominio> findByLogIncompatibilidades(LogIncompatibilidades logIncompatibilidades);

    List<Dominio> findByLogDatosDominio(LogDatosDominio logDatosDominio);

    List<Dominio> findByProductoT(Productos productoT);

    List<Dominio> findByPeriodicidades(Periodicidad periodicidades);

    List<Dominio> findByRoles(Rol roles);

    List<Dominio> findByFormasPago(FormaPago formasPago);

    List<Dominio> findByPlantillas(PlantillaEmail plantillas);

    List<Dominio> findByNovedades(Novedad novedades);

    List<Dominio> findByGrupoAsesores(GrupoAsignacion grupoAsesores);

    List<Dominio> findByGrupoAreaMedica(GrupoAsignacion grupoAreaMedica);

    List<Dominio> findByDominiosIncompatibles(Dominio dominiosIncompatibles);
}
