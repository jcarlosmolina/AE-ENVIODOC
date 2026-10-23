package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.GrupoAsociado;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface GrupoAsociadoJpaRepositoryCustom {

    List<GrupoAsociado> solveQuery(SQLSelect query);
    
    List<GrupoAsociado> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<GrupoAsociado> executeQuery(String query);

    public List<GrupoAsociado> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
