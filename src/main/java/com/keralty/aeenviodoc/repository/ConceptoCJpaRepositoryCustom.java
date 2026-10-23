package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.ConceptoC;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface ConceptoCJpaRepositoryCustom {

    List<ConceptoC> solveQuery(SQLSelect query);
    
    List<ConceptoC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<ConceptoC> executeQuery(String query);

    public List<ConceptoC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
