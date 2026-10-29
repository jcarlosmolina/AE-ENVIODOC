package com.keralty.aeenviodoc.viewmodel.grupoasociado.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeenviodoc.global.GrupoAsociadoConstants;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;

public class IFGrupoAsociadoCodigoLegal {

    @JsonProperty("v_codigolegal")
    private String vCodigoLegal;

    public IFGrupoAsociadoCodigoLegal() {
        // Default constructor
    }

    @JsonIgnore
    public String getVCodigoLegal() {
        return this.vCodigoLegal;
    }
    
    public void setVCodigoLegal(String vCodigoLegal) {
        this.vCodigoLegal = vCodigoLegal;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(GrupoAsociadoConstants.FILTER_NAME_IFGRUPOASOCIADOCODIGOLEGAL);
        filterRequest.addFilterVariable(GrupoAsociadoConstants.VAR_NAME_IFGRUPOASOCIADOCODIGOLEGAL_VCODIGOLEGAL, vCodigoLegal);
        return filterRequest;
    }
}
