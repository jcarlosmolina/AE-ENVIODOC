package com.keralty.aeenviodoc.viewmodel.usuario.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.UsuarioConstants;
import com.keralty.aeenviodoc.persistence.EstadoBeneficiario;
import com.keralty.aeenviodoc.persistence.oid.EstadoBeneficiarioOid;
import com.keralty.aeenviodoc.persistence.oid.UsuarioOid;
import com.keralty.aeenviodoc.persistence.Usuario;

public class InsEstadoBeneficiarioCORERequest {

    @JsonProperty("p_thisusuario_oid")
    private UsuarioOid usuarioInsEstadoBeneficiarioCOREpthisUsuario;
    
    @JsonIgnore
    private Usuario usuarioInsEstadoBeneficiarioCOREpthisUsuarioInstance;

    @JsonProperty("p_evcestadobeneficiario_oid")
    private EstadoBeneficiarioOid usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario;
    
    @JsonIgnore
    private EstadoBeneficiario usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkUsuarioInsEstadoBeneficiarioCOREpthisUsuario();
        checkUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario();
    }

    @JsonIgnore
    public UsuarioOid getUsuarioInsEstadoBeneficiarioCOREpthisUsuario() {
        return this.usuarioInsEstadoBeneficiarioCOREpthisUsuario;
    }
    
    public void setUsuarioInsEstadoBeneficiarioCOREpthisUsuario(UsuarioOid usuarioInsEstadoBeneficiarioCOREpthisUsuario) {
        this.usuarioInsEstadoBeneficiarioCOREpthisUsuario = usuarioInsEstadoBeneficiarioCOREpthisUsuario;
    }

    @JsonIgnore
    public Usuario getUsuarioInsEstadoBeneficiarioCOREpthisUsuarioInstance() {
        return this.usuarioInsEstadoBeneficiarioCOREpthisUsuarioInstance;
    }
    
    public void setUsuarioInsEstadoBeneficiarioCOREpthisUsuarioInstance(Usuario usuarioInsEstadoBeneficiarioCOREpthisUsuario) {
        this.usuarioInsEstadoBeneficiarioCOREpthisUsuarioInstance = usuarioInsEstadoBeneficiarioCOREpthisUsuario;
        this.usuarioInsEstadoBeneficiarioCOREpthisUsuario = usuarioInsEstadoBeneficiarioCOREpthisUsuario != null ? usuarioInsEstadoBeneficiarioCOREpthisUsuario.getOid() : null;
    }

    private void checkUsuarioInsEstadoBeneficiarioCOREpthisUsuario() throws NotNullArgumentException {
        if(this.getUsuarioInsEstadoBeneficiarioCOREpthisUsuarioInstance() == null || this.getUsuarioInsEstadoBeneficiarioCOREpthisUsuarioInstance().isNull()) {
            throw new NotNullArgumentException(UsuarioConstants.SERV_ID_INSESTADOBENEFICIARIOCORE, UsuarioConstants.CLASS_ID, UsuarioConstants.ARG_ID_INSESTADOBENEFICIARIOCORE_USUARIOINSESTADOBENEFICIARIOCOREPTHISUSUARIO, UsuarioConstants.SERV_ALIAS_INSESTADOBENEFICIARIOCORE, UsuarioConstants.CLASS_ALIAS, UsuarioConstants.ARG_ALIAS_INSESTADOBENEFICIARIOCORE_USUARIOINSESTADOBENEFICIARIOCOREPTHISUSUARIO);
        }
    }

    @JsonIgnore
    public EstadoBeneficiarioOid getUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario() {
        return this.usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario;
    }
    
    public void setUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario(EstadoBeneficiarioOid usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario) {
        this.usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario = usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario;
    }

    @JsonIgnore
    public EstadoBeneficiario getUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance() {
        return this.usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance;
    }
    
    public void setUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance(EstadoBeneficiario usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario) {
        this.usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance = usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario;
        this.usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario = usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario != null ? usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario.getOid() : null;
    }

    private void checkUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario() throws NotNullArgumentException {
        if(this.getUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance() == null || this.getUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance().isNull()) {
            throw new NotNullArgumentException(UsuarioConstants.SERV_ID_INSESTADOBENEFICIARIOCORE, UsuarioConstants.CLASS_ID, UsuarioConstants.ARG_ID_INSESTADOBENEFICIARIOCORE_USUARIOINSESTADOBENEFICIARIOCOREPEVCESTADOBENEFICIARIO, UsuarioConstants.SERV_ALIAS_INSESTADOBENEFICIARIOCORE, UsuarioConstants.CLASS_ALIAS, UsuarioConstants.ARG_ALIAS_INSESTADOBENEFICIARIOCORE_USUARIOINSESTADOBENEFICIARIOCOREPEVCESTADOBENEFICIARIO);
        }
    }
}
