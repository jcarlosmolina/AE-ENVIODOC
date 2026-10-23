package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.CausaExclusion;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface CausaExclusionJpaRepositoryCustom {

    List<CausaExclusion> solveQuery(SQLSelect query);
    
    List<CausaExclusion> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<CausaExclusion> executeQuery(String query);

    public List<CausaExclusion> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
