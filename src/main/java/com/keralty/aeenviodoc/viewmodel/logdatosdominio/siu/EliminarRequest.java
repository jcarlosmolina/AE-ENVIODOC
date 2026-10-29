package com.keralty.aeenviodoc.viewmodel.logdatosdominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.LogDatosDominioConstants;
import com.keralty.aeenviodoc.persistence.LogDatosDominio;
import com.keralty.aeenviodoc.persistence.oid.LogDatosDominioOid;

public class EliminarRequest {

    @JsonProperty("p_thislogdatosdominio_oid")
    private LogDatosDominioOid logDatosDominioeliminarpthisLogDatosDominio;
    
    @JsonIgnore
    private LogDatosDominio logDatosDominioeliminarpthisLogDatosDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogDatosDominioeliminarpthisLogDatosDominio();
    }

    @JsonIgnore
    public LogDatosDominioOid getLogDatosDominioeliminarpthisLogDatosDominio() {
        return this.logDatosDominioeliminarpthisLogDatosDominio;
    }
    
    public void setLogDatosDominioeliminarpthisLogDatosDominio(LogDatosDominioOid logDatosDominioeliminarpthisLogDatosDominio) {
        this.logDatosDominioeliminarpthisLogDatosDominio = logDatosDominioeliminarpthisLogDatosDominio;
    }

    @JsonIgnore
    public LogDatosDominio getLogDatosDominioeliminarpthisLogDatosDominioInstance() {
        return this.logDatosDominioeliminarpthisLogDatosDominioInstance;
    }
    
    public void setLogDatosDominioeliminarpthisLogDatosDominioInstance(LogDatosDominio logDatosDominioeliminarpthisLogDatosDominio) {
        this.logDatosDominioeliminarpthisLogDatosDominioInstance = logDatosDominioeliminarpthisLogDatosDominio;
        this.logDatosDominioeliminarpthisLogDatosDominio = logDatosDominioeliminarpthisLogDatosDominio != null ? logDatosDominioeliminarpthisLogDatosDominio.getOid() : null;
    }

    private void checkLogDatosDominioeliminarpthisLogDatosDominio() throws NotNullArgumentException {
        if(this.getLogDatosDominioeliminarpthisLogDatosDominioInstance() == null || this.getLogDatosDominioeliminarpthisLogDatosDominioInstance().isNull()) {
            throw new NotNullArgumentException(LogDatosDominioConstants.SERV_ID_ELIMINAR, LogDatosDominioConstants.CLASS_ID, LogDatosDominioConstants.ARG_ID_ELIMINAR_LOGDATOSDOMINIOELIMINARPTHISLOGDATOSDOMINIO, LogDatosDominioConstants.SERV_ALIAS_ELIMINAR, LogDatosDominioConstants.CLASS_ALIAS, LogDatosDominioConstants.ARG_ALIAS_ELIMINAR_LOGDATOSDOMINIOELIMINARPTHISLOGDATOSDOMINIO);
        }
    }
}
