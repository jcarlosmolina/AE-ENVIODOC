package com.keralty.aeenviodoc.viewmodel.direccion.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.persistence.Direccion;
public class DSDireccionNull {

    @JsonProperty("codigo")
    private Long dSDireccionNullCodigo;

    public DSDireccionNull() {
    
    }

    public DSDireccionNull(Direccion instance) {
        if (instance != null) {
            this.dSDireccionNullCodigo = instance.getDireccionCodigo();
        }
    }

    @JsonIgnore
    public Long getDSDireccionNullCodigo() {
        return this.dSDireccionNullCodigo;
    }
    
    public void setDSDireccionNullCodigo(Long dSDireccionNullCodigo) {
        this.dSDireccionNullCodigo = dSDireccionNullCodigo;
    }
}
