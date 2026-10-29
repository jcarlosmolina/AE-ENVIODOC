package com.keralty.aeenviodoc.viewmodel.dominio.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeenviodoc.persistence.oid.ProductosOid;
import com.keralty.aeenviodoc.persistence.oid.PlanesOid;
import com.keralty.aeenviodoc.global.DominioConstants;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;

public class IFDominioProdPlan {

    @JsonProperty("vo_producto")
    private ProductosOid voProducto;

    @JsonProperty("vo_plan")
    private PlanesOid voPlan;

    public IFDominioProdPlan() {
        // Default constructor
    }

    @JsonIgnore
    public ProductosOid getVoProducto() {
        return this.voProducto;
    }
    
    public void setVoProducto(ProductosOid voProducto) {
        this.voProducto = voProducto;
    }

    @JsonIgnore
    public PlanesOid getVoPlan() {
        return this.voPlan;
    }
    
    public void setVoPlan(PlanesOid voPlan) {
        this.voPlan = voPlan;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(DominioConstants.FILTER_NAME_IFDOMINIOPRODPLAN);
        filterRequest.addFilterVariable(DominioConstants.VAR_NAME_IFDOMINIOPRODPLAN_VOPRODUCTO, voProducto);
        filterRequest.addFilterVariable(DominioConstants.VAR_NAME_IFDOMINIOPRODPLAN_VOPLAN, voPlan);
        return filterRequest;
    }
}
