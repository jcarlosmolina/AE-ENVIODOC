package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.Funcionario;
import com.keralty.aeenviodoc.persistence.RespRegional;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface RespRegionalJpaRepositoryCustom {

    List<RespRegional> solveQuery(SQLSelect query);
    
    List<RespRegional> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Funcionario findParent(Long oid);

    public List<RespRegional> executeQuery(String query);

    public List<RespRegional> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
