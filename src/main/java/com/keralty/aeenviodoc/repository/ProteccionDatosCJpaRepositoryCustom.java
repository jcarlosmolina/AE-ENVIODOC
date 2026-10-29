package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.ProteccionDatosC;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface ProteccionDatosCJpaRepositoryCustom {

    List<ProteccionDatosC> solveQuery(SQLSelect query);
    
    List<ProteccionDatosC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<ProteccionDatosC> executeQuery(String query);

    public List<ProteccionDatosC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
