package com.keralty.aeenviodoc.viewmodel.logdatosdominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.LogDatosDominioConstants;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.oid.DominioOid;

public class TCREARRequest {

    @JsonProperty("pt_p_atrtramiteenlinea")
    private Boolean logDatosDominioTCREARptpatrTramiteEnLinea;

    @JsonProperty("pt_p_agrdominio_oid")
    private DominioOid logDatosDominioTCREARptpagrDominio;
    
    @JsonIgnore
    private Dominio logDatosDominioTCREARptpagrDominioInstance;

    @JsonProperty("ptvigenciaradicacion")
    private Boolean logDatosDominioTCREARptVigenciaRadicacion;

    public void checkArguments() throws NotNullArgumentException {
        checkLogDatosDominioTCREARptpatrTramiteEnLinea();
        checkLogDatosDominioTCREARptpagrDominio();
        checkLogDatosDominioTCREARptVigenciaRadicacion();
    }

    @JsonIgnore
    public Boolean getLogDatosDominioTCREARptpatrTramiteEnLinea() {
        return this.logDatosDominioTCREARptpatrTramiteEnLinea;
    }
    
    public void setLogDatosDominioTCREARptpatrTramiteEnLinea(Boolean logDatosDominioTCREARptpatrTramiteEnLinea) {
        this.logDatosDominioTCREARptpatrTramiteEnLinea = logDatosDominioTCREARptpatrTramiteEnLinea;
    }

    private void checkLogDatosDominioTCREARptpatrTramiteEnLinea() throws NotNullArgumentException {
        if(this.getLogDatosDominioTCREARptpatrTramiteEnLinea() == null) {
            throw new NotNullArgumentException(LogDatosDominioConstants.SERV_ID_TCREAR, LogDatosDominioConstants.CLASS_ID, LogDatosDominioConstants.ARG_ID_TCREAR_LOGDATOSDOMINIOTCREARPTPATRTRAMITEENLINEA, LogDatosDominioConstants.SERV_ALIAS_TCREAR, LogDatosDominioConstants.CLASS_ALIAS, LogDatosDominioConstants.ARG_ALIAS_TCREAR_LOGDATOSDOMINIOTCREARPTPATRTRAMITEENLINEA);
        }
    }

    @JsonIgnore
    public DominioOid getLogDatosDominioTCREARptpagrDominio() {
        return this.logDatosDominioTCREARptpagrDominio;
    }
    
    public void setLogDatosDominioTCREARptpagrDominio(DominioOid logDatosDominioTCREARptpagrDominio) {
        this.logDatosDominioTCREARptpagrDominio = logDatosDominioTCREARptpagrDominio;
    }

    @JsonIgnore
    public Dominio getLogDatosDominioTCREARptpagrDominioInstance() {
        return this.logDatosDominioTCREARptpagrDominioInstance;
    }
    
    public void setLogDatosDominioTCREARptpagrDominioInstance(Dominio logDatosDominioTCREARptpagrDominio) {
        this.logDatosDominioTCREARptpagrDominioInstance = logDatosDominioTCREARptpagrDominio;
        this.logDatosDominioTCREARptpagrDominio = logDatosDominioTCREARptpagrDominio != null ? logDatosDominioTCREARptpagrDominio.getOid() : null;
    }

    private void checkLogDatosDominioTCREARptpagrDominio() throws NotNullArgumentException {
        if(this.getLogDatosDominioTCREARptpagrDominioInstance() == null || this.getLogDatosDominioTCREARptpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(LogDatosDominioConstants.SERV_ID_TCREAR, LogDatosDominioConstants.CLASS_ID, LogDatosDominioConstants.ARG_ID_TCREAR_LOGDATOSDOMINIOTCREARPTPAGRDOMINIO, LogDatosDominioConstants.SERV_ALIAS_TCREAR, LogDatosDominioConstants.CLASS_ALIAS, LogDatosDominioConstants.ARG_ALIAS_TCREAR_LOGDATOSDOMINIOTCREARPTPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public Boolean getLogDatosDominioTCREARptVigenciaRadicacion() {
        return this.logDatosDominioTCREARptVigenciaRadicacion;
    }
    
    public void setLogDatosDominioTCREARptVigenciaRadicacion(Boolean logDatosDominioTCREARptVigenciaRadicacion) {
        this.logDatosDominioTCREARptVigenciaRadicacion = logDatosDominioTCREARptVigenciaRadicacion;
    }

    private void checkLogDatosDominioTCREARptVigenciaRadicacion() throws NotNullArgumentException {
        if(this.getLogDatosDominioTCREARptVigenciaRadicacion() == null) {
            throw new NotNullArgumentException(LogDatosDominioConstants.SERV_ID_TCREAR, LogDatosDominioConstants.CLASS_ID, LogDatosDominioConstants.ARG_ID_TCREAR_LOGDATOSDOMINIOTCREARPTVIGENCIARADICACION, LogDatosDominioConstants.SERV_ALIAS_TCREAR, LogDatosDominioConstants.CLASS_ALIAS, LogDatosDominioConstants.ARG_ALIAS_TCREAR_LOGDATOSDOMINIOTCREARPTVIGENCIARADICACION);
        }
    }
}
