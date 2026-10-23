package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AnexoC;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface AnexoCJpaRepositoryCustom {

    List<AnexoC> solveQuery(SQLSelect query);
    
    List<AnexoC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<AnexoC> executeQuery(String query);

    public List<AnexoC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
