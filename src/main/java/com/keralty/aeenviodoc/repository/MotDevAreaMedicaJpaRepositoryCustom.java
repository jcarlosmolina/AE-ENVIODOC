package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.MotDevAreaMedica;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface MotDevAreaMedicaJpaRepositoryCustom {

    List<MotDevAreaMedica> solveQuery(SQLSelect query);
    
    List<MotDevAreaMedica> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<MotDevAreaMedica> executeQuery(String query);

    public List<MotDevAreaMedica> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
