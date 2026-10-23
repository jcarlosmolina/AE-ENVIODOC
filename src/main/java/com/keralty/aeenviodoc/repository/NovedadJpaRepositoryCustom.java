package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.Novedad;
import com.keralty.aeenviodoc.persistence.Planes;
import com.keralty.aeenviodoc.sqlutils.SQLCount;
import com.keralty.aeenviodoc.sqlutils.SQLExists;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface NovedadJpaRepositoryCustom {

    List<Novedad> solveQuery(SQLSelect query);
    
    List<Novedad> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Long assocOperator000(Novedad instance) throws SystemException;

    Long assocOperator000Range1(Novedad instance) throws SystemException;
    
    SQLCount assocOperator000Range1Query(Novedad instance) throws SystemException;

    Long assocOperator001(Novedad instance) throws SystemException;

    Long assocOperator001Range1(Novedad instance) throws SystemException;
    
    SQLCount assocOperator001Range1Query(Novedad instance) throws SystemException;

    Boolean assocOperator003(Planes solicitudNovedadTCREARptpagrPlan) throws SystemException;

    Boolean assocOperator003Range1(Planes solicitudNovedadTCREARptpagrPlan) throws SystemException;
    
    SQLExists assocOperator003Range1Query(Planes solicitudNovedadTCREARptpagrPlan, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator004(Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan) throws SystemException;

    Boolean assocOperator004Range1(Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan) throws SystemException;
    
    SQLExists assocOperator004Range1Query(Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator005(Dominio grupoAsignacionNovedadTCREARptpagrDominio) throws SystemException;

    Boolean assocOperator005Range1(Dominio grupoAsignacionNovedadTCREARptpagrDominio) throws SystemException;
    
    SQLExists assocOperator005Range1Query(Dominio grupoAsignacionNovedadTCREARptpagrDominio, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator006(Dominio configuracionANSTCREARptpagrDominio) throws SystemException;

    Boolean assocOperator006Range1(Dominio configuracionANSTCREARptpagrDominio) throws SystemException;
    
    SQLExists assocOperator006Range1Query(Dominio configuracionANSTCREARptpagrDominio, SQLSelect superQuery) throws SystemException;

    public List<Novedad> executeQuery(String query);

    public List<Novedad> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
