package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.AreaMedica;
import com.keralty.aeenviodoc.persistence.Funcionario;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface AreaMedicaJpaRepositoryCustom {

    List<AreaMedica> solveQuery(SQLSelect query);
    
    List<AreaMedica> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Funcionario findParent(Long oid);

    public List<AreaMedica> executeQuery(String query);

    public List<AreaMedica> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
