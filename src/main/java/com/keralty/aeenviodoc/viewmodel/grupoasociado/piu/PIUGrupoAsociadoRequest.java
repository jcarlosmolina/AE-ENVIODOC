package com.keralty.aeenviodoc.viewmodel.grupoasociado.piu;

import com.keralty.aeenviodoc.viewmodel.PopulationRequestViewModel;
import com.keralty.aeenviodoc.viewmodel.QueryRequest;
import com.keralty.aeenviodoc.viewmodel.grupoasociado.filter.FGrupoAsociadoVarios;

public class PIUGrupoAsociadoRequest extends PopulationRequestViewModel{

    private FGrupoAsociadoVarios fGrupoAsociadoVarios;
    
    public void setFGrupoAsociadoVarios(FGrupoAsociadoVarios fGrupoAsociadoVarios) {
        this.fGrupoAsociadoVarios = fGrupoAsociadoVarios;
    }
    
    public FGrupoAsociadoVarios getFGrupoAsociadoVarios() {
        return fGrupoAsociadoVarios;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fGrupoAsociadoVarios != null)
            queryRequest.addFilterRequest(fGrupoAsociadoVarios.buildFilterRequest());
        return queryRequest;
    }
}
