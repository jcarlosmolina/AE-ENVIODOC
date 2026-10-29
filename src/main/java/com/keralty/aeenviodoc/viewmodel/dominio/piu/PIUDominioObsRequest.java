package com.keralty.aeenviodoc.viewmodel.dominio.piu;

import com.keralty.aeenviodoc.viewmodel.PopulationRequestViewModel;
import com.keralty.aeenviodoc.viewmodel.QueryRequest;
import com.keralty.aeenviodoc.viewmodel.dominio.filter.FDominioVarios;

public class PIUDominioObsRequest extends PopulationRequestViewModel{

    private FDominioVarios fDominioVarios;
    
    public void setFDominioVarios(FDominioVarios fDominioVarios) {
        this.fDominioVarios = fDominioVarios;
    }
    
    public FDominioVarios getFDominioVarios() {
        return fDominioVarios;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fDominioVarios != null)
            queryRequest.addFilterRequest(fDominioVarios.buildFilterRequest());
        return queryRequest;
    }
}
