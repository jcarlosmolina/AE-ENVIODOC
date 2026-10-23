package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.TipoDireccion;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface TipoDireccionJpaRepositoryCustom {

    List<TipoDireccion> solveQuery(SQLSelect query);
    
    List<TipoDireccion> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<TipoDireccion> executeQuery(String query);

    public List<TipoDireccion> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
