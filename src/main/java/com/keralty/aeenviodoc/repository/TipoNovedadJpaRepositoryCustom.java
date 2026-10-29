package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.TipoNovedad;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface TipoNovedadJpaRepositoryCustom {

    List<TipoNovedad> solveQuery(SQLSelect query);
    
    List<TipoNovedad> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<TipoNovedad> executeQuery(String query);

    public List<TipoNovedad> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
