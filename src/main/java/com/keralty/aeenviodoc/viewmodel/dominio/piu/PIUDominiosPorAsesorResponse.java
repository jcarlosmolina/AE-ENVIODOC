package com.keralty.aeenviodoc.viewmodel.dominio.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.dominio.ds.DSDominiosPorAsesor;

public class PIUDominiosPorAsesorResponse extends QueryResponse {

    private List<DSDominiosPorAsesor> results;

    public PIUDominiosPorAsesorResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUDominiosPorAsesorResponse(List<DSDominiosPorAsesor> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSDominiosPorAsesor> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSDominiosPorAsesor> results) {
        this.results = results;
    }
}
