package com.keralty.aeenviodoc.viewmodel.novedad.piu;

import com.keralty.aeenviodoc.viewmodel.PopulationRequestViewModel;
import com.keralty.aeenviodoc.viewmodel.QueryRequest;
import com.keralty.aeenviodoc.viewmodel.novedad.filter.FNovedad;

public class PIUNovedadRequest extends PopulationRequestViewModel{

    private FNovedad fNovedad;
    
    public void setFNovedad(FNovedad fNovedad) {
        this.fNovedad = fNovedad;
    }
    
    public FNovedad getFNovedad() {
        return fNovedad;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fNovedad != null)
            queryRequest.addFilterRequest(fNovedad.buildFilterRequest());
        return queryRequest;
    }
}
