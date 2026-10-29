package com.keralty.aeenviodoc.viewmodel.anexoneo.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.persistence.AnexoNeo;
public class DSAnexoNeoNull {

    @JsonProperty("identificador")
    private Long dSAnexoNeoNullIdentificador;

    public DSAnexoNeoNull() {
    
    }

    public DSAnexoNeoNull(AnexoNeo instance) {
        if (instance != null) {
            this.dSAnexoNeoNullIdentificador = instance.getAnexoNeoIdentificador();
        }
    }

    @JsonIgnore
    public Long getDSAnexoNeoNullIdentificador() {
        return this.dSAnexoNeoNullIdentificador;
    }
    
    public void setDSAnexoNeoNullIdentificador(Long dSAnexoNeoNullIdentificador) {
        this.dSAnexoNeoNullIdentificador = dSAnexoNeoNullIdentificador;
    }
}
