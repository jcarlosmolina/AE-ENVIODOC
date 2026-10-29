package com.keralty.aeenviodoc.viewmodel.novedad.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeenviodoc.persistence.oid.TipoNovedadOid;
import com.keralty.aeenviodoc.global.NovedadConstants;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;

public class FNovedad {

    @JsonProperty("vftipo")
    private TipoNovedadOid vfTipo;

    @JsonProperty("vfnombre")
    private String vfNombre;

    public FNovedad() {
        // Default constructor
    }

    @JsonIgnore
    public TipoNovedadOid getVfTipo() {
        return this.vfTipo;
    }
    
    public void setVfTipo(TipoNovedadOid vfTipo) {
        this.vfTipo = vfTipo;
    }

    @JsonIgnore
    public String getVfNombre() {
        return this.vfNombre;
    }
    
    public void setVfNombre(String vfNombre) {
        this.vfNombre = vfNombre;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(NovedadConstants.FILTER_NAME_FNOVEDAD);
        filterRequest.addFilterVariable(NovedadConstants.VAR_NAME_FNOVEDAD_VFTIPO, vfTipo);
        filterRequest.addFilterVariable(NovedadConstants.VAR_NAME_FNOVEDAD_VFNOMBRE, vfNombre);
        return filterRequest;
    }
}
