package com.keralty.aeenviodoc.viewmodel.asesor.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.asesor.ds.DSAsesorNull;

public class PIUAsesorNullResponse extends QueryResponse {

    private List<DSAsesorNull> results;

    public PIUAsesorNullResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUAsesorNullResponse(List<DSAsesorNull> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSAsesorNull> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSAsesorNull> results) {
        this.results = results;
    }
}
