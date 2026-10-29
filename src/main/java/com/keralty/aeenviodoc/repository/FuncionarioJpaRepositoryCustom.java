package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.persistence.Admin;
import com.keralty.aeenviodoc.persistence.Afiliaciones;
import com.keralty.aeenviodoc.persistence.AreaMedica;
import com.keralty.aeenviodoc.persistence.Asesor;
import com.keralty.aeenviodoc.persistence.Funcionario;
import com.keralty.aeenviodoc.persistence.Gestor;
import com.keralty.aeenviodoc.persistence.GrupoAsignacion;
import com.keralty.aeenviodoc.persistence.MesaDeApoyo;
import com.keralty.aeenviodoc.persistence.RespRegional;
import com.keralty.aeenviodoc.persistence.SuperAdmin;
import com.keralty.aeenviodoc.sqlutils.SQLCount;
import com.keralty.aeenviodoc.sqlutils.SQLExists;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import java.util.List;

public interface FuncionarioJpaRepositoryCustom {

    List<Funcionario> solveQuery(SQLSelect query);
    
    List<Funcionario> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Admin findChildAdmin(Long oid);

    SuperAdmin findChildSuperAdmin(Long oid);

    Asesor findChildAsesor(Long oid);

    AreaMedica findChildAreaMedica(Long oid);

    Afiliaciones findChildAfiliaciones(Long oid);

    RespRegional findChildRespRegional(Long oid);

    Gestor findChildGestor(Long oid);

    MesaDeApoyo findChildMesaDeApoyo(Long oid);

    Long assocOperator000(Funcionario instance) throws SystemException;

    Long assocOperator000Range1(Funcionario instance) throws SystemException;
    
    SQLCount assocOperator000Range1Query(Funcionario instance) throws SystemException;

    Boolean assocOperator002(GrupoAsignacion grupoAsignacionTADDUSUARIOpthisGrupoAsignacion) throws SystemException;

    Boolean assocOperator002Range1(GrupoAsignacion grupoAsignacionTADDUSUARIOpthisGrupoAsignacion) throws SystemException;
    
    SQLExists assocOperator002Range1Query(GrupoAsignacion grupoAsignacionTADDUSUARIOpthisGrupoAsignacion, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator003(GrupoAsignacion grupoAsignacionTDELUSUARIOpthisGrupoAsignacion) throws SystemException;

    Boolean assocOperator003Range1(GrupoAsignacion grupoAsignacionTDELUSUARIOpthisGrupoAsignacion) throws SystemException;
    
    SQLExists assocOperator003Range1Query(GrupoAsignacion grupoAsignacionTDELUSUARIOpthisGrupoAsignacion, SQLSelect superQuery) throws SystemException;

    public List<Funcionario> executeQuery(String query);

    public List<Funcionario> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
