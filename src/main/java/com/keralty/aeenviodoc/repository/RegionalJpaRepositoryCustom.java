package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Regional;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface RegionalJpaRepositoryCustom {

    List<Regional> solveQuery(SQLSelect query);
    
    List<Regional> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<Regional> executeQuery(String query);

    public List<Regional> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
