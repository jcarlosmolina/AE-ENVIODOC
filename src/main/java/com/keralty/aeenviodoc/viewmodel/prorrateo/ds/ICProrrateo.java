package com.keralty.aeenviodoc.viewmodel.prorrateo.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.persistence.Prorrateo;
public class ICProrrateo {

    @JsonProperty("porcentaje")
    private Double iCProrrateoPorcentaje;

    public ICProrrateo() {
    
    }

    public ICProrrateo(Prorrateo instance) {
        if (instance != null) {
            this.iCProrrateoPorcentaje = instance.getProrrateoPorcentaje();
        }
    }

    @JsonIgnore
    public Double getICProrrateoPorcentaje() {
        return this.iCProrrateoPorcentaje;
    }
    
    public void setICProrrateoPorcentaje(Double iCProrrateoPorcentaje) {
        this.iCProrrateoPorcentaje = iCProrrateoPorcentaje;
    }
}
