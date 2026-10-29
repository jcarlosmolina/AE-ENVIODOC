package com.keralty.aeenviodoc.viewmodel.logestado.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.logestado.ds.DSLogEstado;

public class PIULogEstadoResponse extends QueryResponse {

    private List<DSLogEstado> results;

    public PIULogEstadoResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogEstadoResponse(List<DSLogEstado> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogEstado> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogEstado> results) {
        this.results = results;
    }
}
