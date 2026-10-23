package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.PlantillaReporte;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface PlantillaReporteJpaRepositoryCustom {

    List<PlantillaReporte> solveQuery(SQLSelect query);
    
    List<PlantillaReporte> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<PlantillaReporte> executeQuery(String query);

    public List<PlantillaReporte> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
