package com.keralty.aeenviodoc.viewmodel.usuario.piu;

import com.keralty.aeenviodoc.viewmodel.PopulationRequestViewModel;
import com.keralty.aeenviodoc.viewmodel.QueryRequest;
import com.keralty.aeenviodoc.viewmodel.usuario.filter.FUsuariosPolizaInter;

public class PIUUsuariosPolInternacionalRequest extends PopulationRequestViewModel{

    private FUsuariosPolizaInter fUsuariosPolizaInter;
    
    public void setFUsuariosPolizaInter(FUsuariosPolizaInter fUsuariosPolizaInter) {
        this.fUsuariosPolizaInter = fUsuariosPolizaInter;
    }
    
    public FUsuariosPolizaInter getFUsuariosPolizaInter() {
        return fUsuariosPolizaInter;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fUsuariosPolizaInter != null)
            queryRequest.addFilterRequest(fUsuariosPolizaInter.buildFilterRequest());
        return queryRequest;
    }
}
