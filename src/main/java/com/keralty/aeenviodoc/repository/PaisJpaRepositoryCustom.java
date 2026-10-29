package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Pais;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface PaisJpaRepositoryCustom {

    List<Pais> solveQuery(SQLSelect query);
    
    List<Pais> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<Pais> executeQuery(String query);

    public List<Pais> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
