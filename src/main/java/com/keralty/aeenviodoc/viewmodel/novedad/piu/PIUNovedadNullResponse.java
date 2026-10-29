package com.keralty.aeenviodoc.viewmodel.novedad.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.novedad.ds.DSNovedadNull;

public class PIUNovedadNullResponse extends QueryResponse {

    private List<DSNovedadNull> results;

    public PIUNovedadNullResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUNovedadNullResponse(List<DSNovedadNull> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSNovedadNull> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSNovedadNull> results) {
        this.results = results;
    }
}
