package com.keralty.aeenviodoc.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.DominioConstants;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.GrupoAsignacion;
import com.keralty.aeenviodoc.persistence.oid.DominioOid;
import com.keralty.aeenviodoc.persistence.oid.GrupoAsignacionOid;

public class DelGrupoAsesoresRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioDelGrupoAsesorespthisDominio;
    
    @JsonIgnore
    private Dominio dominioDelGrupoAsesorespthisDominioInstance;

    @JsonProperty("p_evcgrupoasignacion_oid")
    private GrupoAsignacionOid dominioDelGrupoAsesorespevcGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion dominioDelGrupoAsesorespevcGrupoAsignacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioDelGrupoAsesorespthisDominio();
        checkDominioDelGrupoAsesorespevcGrupoAsignacion();
    }

    @JsonIgnore
    public DominioOid getDominioDelGrupoAsesorespthisDominio() {
        return this.dominioDelGrupoAsesorespthisDominio;
    }
    
    public void setDominioDelGrupoAsesorespthisDominio(DominioOid dominioDelGrupoAsesorespthisDominio) {
        this.dominioDelGrupoAsesorespthisDominio = dominioDelGrupoAsesorespthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioDelGrupoAsesorespthisDominioInstance() {
        return this.dominioDelGrupoAsesorespthisDominioInstance;
    }
    
    public void setDominioDelGrupoAsesorespthisDominioInstance(Dominio dominioDelGrupoAsesorespthisDominio) {
        this.dominioDelGrupoAsesorespthisDominioInstance = dominioDelGrupoAsesorespthisDominio;
        this.dominioDelGrupoAsesorespthisDominio = dominioDelGrupoAsesorespthisDominio != null ? dominioDelGrupoAsesorespthisDominio.getOid() : null;
    }

    private void checkDominioDelGrupoAsesorespthisDominio() throws NotNullArgumentException {
        if(this.getDominioDelGrupoAsesorespthisDominioInstance() == null || this.getDominioDelGrupoAsesorespthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELGRUPOASESORES, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELGRUPOASESORES_DOMINIODELGRUPOASESORESPTHISDOMINIO, DominioConstants.SERV_ALIAS_DELGRUPOASESORES, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELGRUPOASESORES_DOMINIODELGRUPOASESORESPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getDominioDelGrupoAsesorespevcGrupoAsignacion() {
        return this.dominioDelGrupoAsesorespevcGrupoAsignacion;
    }
    
    public void setDominioDelGrupoAsesorespevcGrupoAsignacion(GrupoAsignacionOid dominioDelGrupoAsesorespevcGrupoAsignacion) {
        this.dominioDelGrupoAsesorespevcGrupoAsignacion = dominioDelGrupoAsesorespevcGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getDominioDelGrupoAsesorespevcGrupoAsignacionInstance() {
        return this.dominioDelGrupoAsesorespevcGrupoAsignacionInstance;
    }
    
    public void setDominioDelGrupoAsesorespevcGrupoAsignacionInstance(GrupoAsignacion dominioDelGrupoAsesorespevcGrupoAsignacion) {
        this.dominioDelGrupoAsesorespevcGrupoAsignacionInstance = dominioDelGrupoAsesorespevcGrupoAsignacion;
        this.dominioDelGrupoAsesorespevcGrupoAsignacion = dominioDelGrupoAsesorespevcGrupoAsignacion != null ? dominioDelGrupoAsesorespevcGrupoAsignacion.getOid() : null;
    }

    private void checkDominioDelGrupoAsesorespevcGrupoAsignacion() throws NotNullArgumentException {
        if(this.getDominioDelGrupoAsesorespevcGrupoAsignacionInstance() == null || this.getDominioDelGrupoAsesorespevcGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELGRUPOASESORES, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELGRUPOASESORES_DOMINIODELGRUPOASESORESPEVCGRUPOASIGNACION, DominioConstants.SERV_ALIAS_DELGRUPOASESORES, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELGRUPOASESORES_DOMINIODELGRUPOASESORESPEVCGRUPOASIGNACION);
        }
    }
}
