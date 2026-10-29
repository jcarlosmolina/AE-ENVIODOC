package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.EstadoPersona;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface EstadoPersonaJpaRepositoryCustom {

    List<EstadoPersona> solveQuery(SQLSelect query);
    
    List<EstadoPersona> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<EstadoPersona> executeQuery(String query);

    public List<EstadoPersona> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
