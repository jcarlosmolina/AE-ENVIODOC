package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.DocumentoG;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface DocumentoGJpaRepositoryCustom {

    List<DocumentoG> solveQuery(SQLSelect query);
    
    List<DocumentoG> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<DocumentoG> executeQuery(String query);

    public List<DocumentoG> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
