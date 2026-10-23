package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.PreexistenciaC;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface PreexistenciaCJpaRepositoryCustom {

    List<PreexistenciaC> solveQuery(SQLSelect query);
    
    List<PreexistenciaC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);
    
    Long nextPreexistenciaCIdentificador(Long personaCID);

    public List<PreexistenciaC> executeQuery(String query);

    public List<PreexistenciaC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
