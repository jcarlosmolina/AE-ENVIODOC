package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.FormaPago;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.sqlutils.SQLExists;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface FormaPagoJpaRepositoryCustom {

    List<FormaPago> solveQuery(SQLSelect query);
    
    List<FormaPago> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Dominio dominioInsFormaPagopthisDominio) throws SystemException;

    Boolean assocOperator000Range1(Dominio dominioInsFormaPagopthisDominio) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Dominio dominioInsFormaPagopthisDominio, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator001(Dominio dominioDelFormaPagopthisDominio) throws SystemException;

    Boolean assocOperator001Range1(Dominio dominioDelFormaPagopthisDominio) throws SystemException;
    
    SQLExists assocOperator001Range1Query(Dominio dominioDelFormaPagopthisDominio, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator002(Solicitud solicitudTMODIFICARINFOPAGOpthisSolicitud) throws SystemException;

    Boolean assocOperator002Range1(Solicitud solicitudTMODIFICARINFOPAGOpthisSolicitud) throws SystemException;
    
    SQLExists assocOperator002Range1Query(Solicitud solicitudTMODIFICARINFOPAGOpthisSolicitud, SQLSelect superQuery) throws SystemException;

    public List<FormaPago> executeQuery(String query);

    public List<FormaPago> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
