package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.DetalleTarifa;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface DetalleTarifaJpaRepositoryCustom {

    List<DetalleTarifa> solveQuery(SQLSelect query);
    
    List<DetalleTarifa> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<DetalleTarifa> executeQuery(String query);

    public List<DetalleTarifa> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
