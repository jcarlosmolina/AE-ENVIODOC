package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.persistence.Pregunta;
import com.keralty.aeenviodoc.persistence.PreguntaP;
import com.keralty.aeenviodoc.persistence.Seccion;
import com.keralty.aeenviodoc.persistence.SeccionP;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLPopulation;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface SeccionPJpaRepositoryCustom {

    List<SeccionP> solveQuery(SQLSelect query);
    
    List<SeccionP> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Collection<Pregunta> assocOperator000(Seccion seccionPTCREARYPREGUNTASptSeccion) throws SystemException;

    Collection<Pregunta> assocOperator000Range1(Seccion seccionPTCREARYPREGUNTASptSeccion) throws SystemException;
    
    SQLPopulation assocOperator000Range1Query(Seccion seccionPTCREARYPREGUNTASptSeccion) throws SystemException;

    Collection<PreguntaP> assocOperator001(SeccionP instance) throws SystemException;

    Collection<PreguntaP> assocOperator001Range1(SeccionP instance) throws SystemException;
    
    SQLPopulation assocOperator001Range1Query(SeccionP instance) throws SystemException;

    public List<SeccionP> executeQuery(String query);

    public List<SeccionP> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
