package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.UsuarioSistemaExterno;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface UsuarioSistemaExternoJpaRepositoryCustom {

    List<UsuarioSistemaExterno> solveQuery(SQLSelect query);
    
    List<UsuarioSistemaExterno> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<UsuarioSistemaExterno> executeQuery(String query);

    public List<UsuarioSistemaExterno> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
