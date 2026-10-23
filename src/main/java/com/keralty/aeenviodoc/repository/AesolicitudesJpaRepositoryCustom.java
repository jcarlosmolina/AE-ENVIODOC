package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Aesolicitudes;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface AesolicitudesJpaRepositoryCustom {

    List<Aesolicitudes> solveQuery(SQLSelect query);
    
    List<Aesolicitudes> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<Aesolicitudes> executeQuery(String query);

    public List<Aesolicitudes> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
