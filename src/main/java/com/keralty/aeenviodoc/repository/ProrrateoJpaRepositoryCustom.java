package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Prorrateo;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface ProrrateoJpaRepositoryCustom {

    List<Prorrateo> solveQuery(SQLSelect query);
    
    List<Prorrateo> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);
    
    Long nextProrrateoidProrrateo(Long dominioNumero);

    public List<Prorrateo> executeQuery(String query);

    public List<Prorrateo> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
