package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.PregCuesMedC;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface PregCuesMedCJpaRepositoryCustom {

    List<PregCuesMedC> solveQuery(SQLSelect query);
    
    List<PregCuesMedC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<PregCuesMedC> executeQuery(String query);

    public List<PregCuesMedC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
