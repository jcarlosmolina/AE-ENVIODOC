package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.global.UserFunctions;
import com.keralty.aeenviodoc.persistence.LogIncompatibilidades;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import com.keralty.aeenviodoc.sqlutils.TypedQueryBuilder;
import com.keralty.aeenviodoc.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class LogIncompatibilidadesJpaRepositoryImpl extends AbstractRepository implements LogIncompatibilidadesJpaRepositoryCustom {

    @Inject
    public LogIncompatibilidadesJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<LogIncompatibilidades> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<LogIncompatibilidades> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<LogIncompatibilidades> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<LogIncompatibilidades> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, LogIncompatibilidades.class);
    }

    @Override	
    public List<LogIncompatibilidades> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<LogIncompatibilidades> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
