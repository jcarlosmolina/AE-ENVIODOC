package com.keralty.aeenviodoc.viewmodel.titular.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.TitularConstants;
import com.keralty.aeenviodoc.persistence.oid.TitularOid;
import com.keralty.aeenviodoc.persistence.Titular;

public class EliminarRequest {

    @JsonProperty("p_thistitular_oid")
    private TitularOid titulareliminarpthisTitular;
    
    @JsonIgnore
    private Titular titulareliminarpthisTitularInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTitulareliminarpthisTitular();
    }

    @JsonIgnore
    public TitularOid getTitulareliminarpthisTitular() {
        return this.titulareliminarpthisTitular;
    }
    
    public void setTitulareliminarpthisTitular(TitularOid titulareliminarpthisTitular) {
        this.titulareliminarpthisTitular = titulareliminarpthisTitular;
    }

    @JsonIgnore
    public Titular getTitulareliminarpthisTitularInstance() {
        return this.titulareliminarpthisTitularInstance;
    }
    
    public void setTitulareliminarpthisTitularInstance(Titular titulareliminarpthisTitular) {
        this.titulareliminarpthisTitularInstance = titulareliminarpthisTitular;
        this.titulareliminarpthisTitular = titulareliminarpthisTitular != null ? titulareliminarpthisTitular.getOid() : null;
    }

    private void checkTitulareliminarpthisTitular() throws NotNullArgumentException {
        if(this.getTitulareliminarpthisTitularInstance() == null || this.getTitulareliminarpthisTitularInstance().isNull()) {
            throw new NotNullArgumentException(TitularConstants.SERV_ID_ELIMINAR, TitularConstants.CLASS_ID, TitularConstants.ARG_ID_ELIMINAR_TITULARELIMINARPTHISTITULAR, TitularConstants.SERV_ALIAS_ELIMINAR, TitularConstants.CLASS_ALIAS, TitularConstants.ARG_ALIAS_ELIMINAR_TITULARELIMINARPTHISTITULAR);
        }
    }
}
