package com.keralty.aeenviodoc.viewmodel.cuestionariop.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.cuestionariop.ds.DSCuestionarioPNull;

public class PIUCuestionarioPNullResponse extends QueryResponse {

    private List<DSCuestionarioPNull> results;

    public PIUCuestionarioPNullResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUCuestionarioPNullResponse(List<DSCuestionarioPNull> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSCuestionarioPNull> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSCuestionarioPNull> results) {
        this.results = results;
    }
}
