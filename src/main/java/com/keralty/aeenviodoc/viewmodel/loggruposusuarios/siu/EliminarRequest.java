package com.keralty.aeenviodoc.viewmodel.loggruposusuarios.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.LogGruposUsuariosConstants;
import com.keralty.aeenviodoc.persistence.LogGruposUsuarios;
import com.keralty.aeenviodoc.persistence.oid.LogGruposUsuariosOid;

public class EliminarRequest {

    @JsonProperty("p_thisloggruposusuarios_oid")
    private LogGruposUsuariosOid logGruposUsuarioseliminarpthisLogGruposUsuarios;
    
    @JsonIgnore
    private LogGruposUsuarios logGruposUsuarioseliminarpthisLogGruposUsuariosInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogGruposUsuarioseliminarpthisLogGruposUsuarios();
    }

    @JsonIgnore
    public LogGruposUsuariosOid getLogGruposUsuarioseliminarpthisLogGruposUsuarios() {
        return this.logGruposUsuarioseliminarpthisLogGruposUsuarios;
    }
    
    public void setLogGruposUsuarioseliminarpthisLogGruposUsuarios(LogGruposUsuariosOid logGruposUsuarioseliminarpthisLogGruposUsuarios) {
        this.logGruposUsuarioseliminarpthisLogGruposUsuarios = logGruposUsuarioseliminarpthisLogGruposUsuarios;
    }

    @JsonIgnore
    public LogGruposUsuarios getLogGruposUsuarioseliminarpthisLogGruposUsuariosInstance() {
        return this.logGruposUsuarioseliminarpthisLogGruposUsuariosInstance;
    }
    
    public void setLogGruposUsuarioseliminarpthisLogGruposUsuariosInstance(LogGruposUsuarios logGruposUsuarioseliminarpthisLogGruposUsuarios) {
        this.logGruposUsuarioseliminarpthisLogGruposUsuariosInstance = logGruposUsuarioseliminarpthisLogGruposUsuarios;
        this.logGruposUsuarioseliminarpthisLogGruposUsuarios = logGruposUsuarioseliminarpthisLogGruposUsuarios != null ? logGruposUsuarioseliminarpthisLogGruposUsuarios.getOid() : null;
    }

    private void checkLogGruposUsuarioseliminarpthisLogGruposUsuarios() throws NotNullArgumentException {
        if(this.getLogGruposUsuarioseliminarpthisLogGruposUsuariosInstance() == null || this.getLogGruposUsuarioseliminarpthisLogGruposUsuariosInstance().isNull()) {
            throw new NotNullArgumentException(LogGruposUsuariosConstants.SERV_ID_ELIMINAR, LogGruposUsuariosConstants.CLASS_ID, LogGruposUsuariosConstants.ARG_ID_ELIMINAR_LOGGRUPOSUSUARIOSELIMINARPTHISLOGGRUPOSUSUARIOS, LogGruposUsuariosConstants.SERV_ALIAS_ELIMINAR, LogGruposUsuariosConstants.CLASS_ALIAS, LogGruposUsuariosConstants.ARG_ALIAS_ELIMINAR_LOGGRUPOSUSUARIOSELIMINARPTHISLOGGRUPOSUSUARIOS);
        }
    }
}
