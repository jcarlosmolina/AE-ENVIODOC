package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Planes;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface PlanesJpaRepositoryCustom {

    List<Planes> solveQuery(SQLSelect query);
    
    List<Planes> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<Planes> executeQuery(String query);

    public List<Planes> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
