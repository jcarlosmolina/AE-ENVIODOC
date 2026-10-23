package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.LogIncompatibilidades;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface LogIncompatibilidadesJpaRepositoryCustom {

    List<LogIncompatibilidades> solveQuery(SQLSelect query);
    
    List<LogIncompatibilidades> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<LogIncompatibilidades> executeQuery(String query);

    public List<LogIncompatibilidades> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
