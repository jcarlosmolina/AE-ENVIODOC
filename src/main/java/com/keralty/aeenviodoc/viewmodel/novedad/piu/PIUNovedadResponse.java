package com.keralty.aeenviodoc.viewmodel.novedad.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.novedad.ds.DSNovedad;

public class PIUNovedadResponse extends QueryResponse {

    private List<DSNovedad> results;

    public PIUNovedadResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUNovedadResponse(List<DSNovedad> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSNovedad> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSNovedad> results) {
        this.results = results;
    }
}
