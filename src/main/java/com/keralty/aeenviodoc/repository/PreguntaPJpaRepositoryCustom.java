package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.PreguntaP;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface PreguntaPJpaRepositoryCustom {

    List<PreguntaP> solveQuery(SQLSelect query);
    
    List<PreguntaP> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<PreguntaP> executeQuery(String query);

    public List<PreguntaP> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
