package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.TelefonoC;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface TelefonoCJpaRepositoryCustom {

    List<TelefonoC> solveQuery(SQLSelect query);
    
    List<TelefonoC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<TelefonoC> executeQuery(String query);

    public List<TelefonoC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
