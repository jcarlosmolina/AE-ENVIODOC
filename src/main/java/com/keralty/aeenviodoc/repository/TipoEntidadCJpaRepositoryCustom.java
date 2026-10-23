package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.TipoEntidadC;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface TipoEntidadCJpaRepositoryCustom {

    List<TipoEntidadC> solveQuery(SQLSelect query);
    
    List<TipoEntidadC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<TipoEntidadC> executeQuery(String query);

    public List<TipoEntidadC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
