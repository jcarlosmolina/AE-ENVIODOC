package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.LogDatosDominio;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface LogDatosDominioJpaRepositoryCustom {

    List<LogDatosDominio> solveQuery(SQLSelect query);
    
    List<LogDatosDominio> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<LogDatosDominio> executeQuery(String query);

    public List<LogDatosDominio> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
