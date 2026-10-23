package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.Usuario;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLPopulation;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface FamiliaCJpaRepositoryCustom {

    List<FamiliaC> solveQuery(SQLSelect query);
    
    List<FamiliaC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);
    
    Long nextFamiliaCID(Long contratoCContratoCID);

    Collection<Usuario> assocOperator003(Solicitud familiaCTNEWDESDEAEptSolicitud) throws SystemException;

    Collection<Usuario> assocOperator003Range1(Solicitud familiaCTNEWDESDEAEptSolicitud) throws SystemException;
    
    SQLPopulation assocOperator003Range1Query(Solicitud familiaCTNEWDESDEAEptSolicitud) throws SystemException;

    public List<FamiliaC> executeQuery(String query);

    public List<FamiliaC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
