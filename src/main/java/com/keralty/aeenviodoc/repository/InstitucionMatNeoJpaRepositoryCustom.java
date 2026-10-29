package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.InstitucionMatNeo;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface InstitucionMatNeoJpaRepositoryCustom {

    List<InstitucionMatNeo> solveQuery(SQLSelect query);
    
    List<InstitucionMatNeo> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<InstitucionMatNeo> executeQuery(String query);

    public List<InstitucionMatNeo> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
