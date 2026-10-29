package com.keralty.aeenviodoc.viewmodel.tipodireccion.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.tipodireccion.ds.AutoDS;

public class PIUTipoDireccionResponse extends QueryResponse {

    private List<AutoDS> results;

    public PIUTipoDireccionResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUTipoDireccionResponse(List<AutoDS> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<AutoDS> getResults() {
        return this.results;
    }
    
    public void setResults(List<AutoDS> results) {
        this.results = results;
    }
}
