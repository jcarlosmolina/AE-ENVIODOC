package com.keralty.aeenviodoc.viewmodel.reportesdominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.ReportesDominioConstants;
import com.keralty.aeenviodoc.persistence.oid.ReportesDominioOid;
import com.keralty.aeenviodoc.persistence.ReportesDominio;

public class EliminarRequest {

    @JsonProperty("p_thisreportesdominio_oid")
    private ReportesDominioOid reportesDominioeliminarpthisReportesDominio;
    
    @JsonIgnore
    private ReportesDominio reportesDominioeliminarpthisReportesDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkReportesDominioeliminarpthisReportesDominio();
    }

    @JsonIgnore
    public ReportesDominioOid getReportesDominioeliminarpthisReportesDominio() {
        return this.reportesDominioeliminarpthisReportesDominio;
    }
    
    public void setReportesDominioeliminarpthisReportesDominio(ReportesDominioOid reportesDominioeliminarpthisReportesDominio) {
        this.reportesDominioeliminarpthisReportesDominio = reportesDominioeliminarpthisReportesDominio;
    }

    @JsonIgnore
    public ReportesDominio getReportesDominioeliminarpthisReportesDominioInstance() {
        return this.reportesDominioeliminarpthisReportesDominioInstance;
    }
    
    public void setReportesDominioeliminarpthisReportesDominioInstance(ReportesDominio reportesDominioeliminarpthisReportesDominio) {
        this.reportesDominioeliminarpthisReportesDominioInstance = reportesDominioeliminarpthisReportesDominio;
        this.reportesDominioeliminarpthisReportesDominio = reportesDominioeliminarpthisReportesDominio != null ? reportesDominioeliminarpthisReportesDominio.getOid() : null;
    }

    private void checkReportesDominioeliminarpthisReportesDominio() throws NotNullArgumentException {
        if(this.getReportesDominioeliminarpthisReportesDominioInstance() == null || this.getReportesDominioeliminarpthisReportesDominioInstance().isNull()) {
            throw new NotNullArgumentException(ReportesDominioConstants.SERV_ID_ELIMINAR, ReportesDominioConstants.CLASS_ID, ReportesDominioConstants.ARG_ID_ELIMINAR_REPORTESDOMINIOELIMINARPTHISREPORTESDOMINIO, ReportesDominioConstants.SERV_ALIAS_ELIMINAR, ReportesDominioConstants.CLASS_ALIAS, ReportesDominioConstants.ARG_ALIAS_ELIMINAR_REPORTESDOMINIOELIMINARPTHISREPORTESDOMINIO);
        }
    }
}
