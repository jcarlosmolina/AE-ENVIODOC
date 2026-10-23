package com.keralty.aeenviodoc.viewmodel.reportesdominio.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeenviodoc.persistence.oid.SolicitudNovedadOid;
import com.keralty.aeenviodoc.global.ReportesDominioConstants;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;

public class FIParaSolicitudNovedad {

    @JsonProperty("vfsolicitudnovedad")
    private SolicitudNovedadOid vfSolicitudNovedad;

    public FIParaSolicitudNovedad() {
        // Default constructor
    }

    @JsonIgnore
    public SolicitudNovedadOid getVfSolicitudNovedad() {
        return this.vfSolicitudNovedad;
    }
    
    public void setVfSolicitudNovedad(SolicitudNovedadOid vfSolicitudNovedad) {
        this.vfSolicitudNovedad = vfSolicitudNovedad;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(ReportesDominioConstants.FILTER_NAME_FIPARASOLICITUDNOVEDAD);
        filterRequest.addFilterVariable(ReportesDominioConstants.VAR_NAME_FIPARASOLICITUDNOVEDAD_VFSOLICITUDNOVEDAD, vfSolicitudNovedad);
        return filterRequest;
    }
}
