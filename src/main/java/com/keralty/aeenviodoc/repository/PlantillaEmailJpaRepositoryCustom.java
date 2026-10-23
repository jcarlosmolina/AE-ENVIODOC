package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.PlantillaEmail;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface PlantillaEmailJpaRepositoryCustom {

    List<PlantillaEmail> solveQuery(SQLSelect query);
    
    List<PlantillaEmail> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<PlantillaEmail> executeQuery(String query);

    public List<PlantillaEmail> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
