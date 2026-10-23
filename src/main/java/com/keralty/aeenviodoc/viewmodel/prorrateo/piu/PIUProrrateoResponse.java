package com.keralty.aeenviodoc.viewmodel.prorrateo.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.prorrateo.ds.DSProrrateo;

public class PIUProrrateoResponse extends QueryResponse {

    private List<DSProrrateo> results;

    public PIUProrrateoResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUProrrateoResponse(List<DSProrrateo> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSProrrateo> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSProrrateo> results) {
        this.results = results;
    }
}
