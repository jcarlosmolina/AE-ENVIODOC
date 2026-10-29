package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.Usuario;
import com.keralty.aeenviodoc.sqlutils.SQLExists;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface MunicipioJpaRepositoryCustom {

    List<Municipio> solveQuery(SQLSelect query);
    
    List<Municipio> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator005(Usuario usuarioTMODUSUARIOpthisUsuario) throws SystemException;

    Boolean assocOperator005Range1(Usuario usuarioTMODUSUARIOpthisUsuario) throws SystemException;
    
    SQLExists assocOperator005Range1Query(Usuario usuarioTMODUSUARIOpthisUsuario, SQLSelect superQuery) throws SystemException;

    public List<Municipio> executeQuery(String query);

    public List<Municipio> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
