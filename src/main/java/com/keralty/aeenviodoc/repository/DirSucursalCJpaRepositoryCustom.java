package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface DirSucursalCJpaRepositoryCustom {

    List<DirSucursalC> solveQuery(SQLSelect query);
    
    List<DirSucursalC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<DirSucursalC> executeQuery(String query);

    public List<DirSucursalC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
