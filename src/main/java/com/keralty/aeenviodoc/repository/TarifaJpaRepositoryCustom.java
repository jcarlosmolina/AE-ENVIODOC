package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Tarifa;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface TarifaJpaRepositoryCustom {

    List<Tarifa> solveQuery(SQLSelect query);
    
    List<Tarifa> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<Tarifa> executeQuery(String query);

    public List<Tarifa> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
