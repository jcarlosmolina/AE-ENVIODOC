package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.LogGruposUsuarios;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface LogGruposUsuariosJpaRepositoryCustom {

    List<LogGruposUsuarios> solveQuery(SQLSelect query);
    
    List<LogGruposUsuarios> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<LogGruposUsuarios> executeQuery(String query);

    public List<LogGruposUsuarios> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
