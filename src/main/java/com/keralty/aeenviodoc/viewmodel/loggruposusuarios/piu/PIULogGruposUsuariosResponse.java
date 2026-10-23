package com.keralty.aeenviodoc.viewmodel.loggruposusuarios.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.loggruposusuarios.ds.DSLogGruposUsuarios;

public class PIULogGruposUsuariosResponse extends QueryResponse {

    private List<DSLogGruposUsuarios> results;

    public PIULogGruposUsuariosResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogGruposUsuariosResponse(List<DSLogGruposUsuarios> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogGruposUsuarios> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogGruposUsuarios> results) {
        this.results = results;
    }
}
