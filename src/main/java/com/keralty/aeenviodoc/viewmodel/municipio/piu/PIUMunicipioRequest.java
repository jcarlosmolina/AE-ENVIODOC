package com.keralty.aeenviodoc.viewmodel.municipio.piu;

import com.keralty.aeenviodoc.viewmodel.PopulationRequestViewModel;
import com.keralty.aeenviodoc.viewmodel.QueryRequest;
import com.keralty.aeenviodoc.viewmodel.municipio.filter.FMunicipioVarios;

public class PIUMunicipioRequest extends PopulationRequestViewModel{

    private FMunicipioVarios fMunicipioVarios;
    
    public void setFMunicipioVarios(FMunicipioVarios fMunicipioVarios) {
        this.fMunicipioVarios = fMunicipioVarios;
    }
    
    public FMunicipioVarios getFMunicipioVarios() {
        return fMunicipioVarios;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fMunicipioVarios != null)
            queryRequest.addFilterRequest(fMunicipioVarios.buildFilterRequest());
        return queryRequest;
    }
}
