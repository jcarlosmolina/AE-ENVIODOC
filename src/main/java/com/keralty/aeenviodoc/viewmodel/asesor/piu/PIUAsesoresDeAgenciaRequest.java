package com.keralty.aeenviodoc.viewmodel.asesor.piu;

import com.keralty.aeenviodoc.viewmodel.PopulationRequestViewModel;
import com.keralty.aeenviodoc.viewmodel.QueryRequest;
import com.keralty.aeenviodoc.viewmodel.asesor.filter.FAsesoresAgencia;

public class PIUAsesoresDeAgenciaRequest extends PopulationRequestViewModel{

    private FAsesoresAgencia fAsesoresAgencia;
    
    public void setFAsesoresAgencia(FAsesoresAgencia fAsesoresAgencia) {
        this.fAsesoresAgencia = fAsesoresAgencia;
    }
    
    public FAsesoresAgencia getFAsesoresAgencia() {
        return fAsesoresAgencia;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fAsesoresAgencia != null)
            queryRequest.addFilterRequest(fAsesoresAgencia.buildFilterRequest());
        return queryRequest;
    }
}
