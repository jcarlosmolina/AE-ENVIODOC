package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.sqlutils.SQLExists;
import com.keralty.aeenviodoc.sqlutils.SQLGetOne;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLPopulation;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface ContratoCJpaRepositoryCustom {

    List<ContratoC> solveQuery(SQLSelect query);
    
    List<ContratoC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Double assocOperator000(ContratoC instance) throws SystemException;

    Collection<BeneficiarioC> assocOperator000Range1(ContratoC instance) throws SystemException;
    
    SQLPopulation assocOperator000Range1Query(ContratoC instance) throws SystemException;

    Double assocOperator001(ContratoC instance) throws SystemException;

    Collection<BeneficiarioC> assocOperator001Range1(ContratoC instance) throws SystemException;
    
    SQLPopulation assocOperator001Range1Query(ContratoC instance) throws SystemException;

    Double assocOperator002(ContratoC instance) throws SystemException;

    Collection<BeneficiarioC> assocOperator002Range1(ContratoC instance) throws SystemException;
    
    SQLPopulation assocOperator002Range1Query(ContratoC instance) throws SystemException;

    Boolean assocOperator003(ContratoC instance) throws SystemException;

    Boolean assocOperator003Range1(ContratoC instance) throws SystemException;
    
    SQLExists assocOperator003Range1Query(ContratoC instance) throws SystemException;

    Boolean assocOperator004(ContratoC instance) throws SystemException;

    Boolean assocOperator004Range1(ContratoC instance) throws SystemException;
    
    SQLExists assocOperator004Range1Query(ContratoC instance) throws SystemException;

    Boolean assocOperator005(ContratoC instance) throws SystemException;

    Boolean assocOperator005Range1(ContratoC instance) throws SystemException;
    
    SQLExists assocOperator005Range1Query(ContratoC instance) throws SystemException;

    String assocOperator006(ContratoC instance) throws SystemException;

    String assocOperator006Range1(ContratoC instance) throws SystemException;
    
    SQLGetOne assocOperator006Range1Query(ContratoC instance) throws SystemException;

    Double assocOperator007(ContratoC instance) throws SystemException;

    Collection<BeneficiarioC> assocOperator007Range1(ContratoC instance) throws SystemException;
    
    SQLPopulation assocOperator007Range1Query(ContratoC instance) throws SystemException;

    Double assocOperator008(ContratoC instance) throws SystemException;

    Collection<BeneficiarioC> assocOperator008Range1(ContratoC instance) throws SystemException;
    
    SQLPopulation assocOperator008Range1Query(ContratoC instance) throws SystemException;

    Collection<DireccionC> assocOperator018(ContratoC instance, Solicitud contratoCTCREAROMODDIRDEAEpSolicitudAE) throws SystemException;

    Collection<DireccionC> assocOperator018Range1(ContratoC instance, Solicitud contratoCTCREAROMODDIRDEAEpSolicitudAE) throws SystemException;
    
    SQLPopulation assocOperator018Range1Query(ContratoC instance, Solicitud contratoCTCREAROMODDIRDEAEpSolicitudAE) throws SystemException;

    Boolean assocOperator020(Solicitud contratoCTCREAROMODDIRDEAEpSolicitudAE) throws SystemException;

    Boolean assocOperator020Range1(Solicitud contratoCTCREAROMODDIRDEAEpSolicitudAE) throws SystemException;
    
    SQLExists assocOperator020Range1Query(Solicitud contratoCTCREAROMODDIRDEAEpSolicitudAE, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator021(ContratoC instance, Solicitud contratoCTCREAROMODDIRDEAEpSolicitudAE) throws SystemException;

    Boolean assocOperator021Range1(ContratoC instance, Solicitud contratoCTCREAROMODDIRDEAEpSolicitudAE) throws SystemException;
    
    SQLExists assocOperator021Range1Query(ContratoC instance, Solicitud contratoCTCREAROMODDIRDEAEpSolicitudAE) throws SystemException;

    public List<ContratoC> executeQuery(String query);

    public List<ContratoC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
