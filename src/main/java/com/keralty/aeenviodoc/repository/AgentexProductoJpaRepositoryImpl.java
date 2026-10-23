package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.global.AgentexProductoConstants;
import com.keralty.aeenviodoc.global.AsesorConstants;
import com.keralty.aeenviodoc.global.UserFunctions;
import com.keralty.aeenviodoc.persistence.AgentexProducto;
import com.keralty.aeenviodoc.sqlutils.SQLMax;
import com.keralty.aeenviodoc.sqlutils.SQLParam;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import com.keralty.aeenviodoc.sqlutils.SQLWhereComparison;
import com.keralty.aeenviodoc.sqlutils.SQLWhereConjunction;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeenviodoc.sqlutils.TypedQueryBuilder;
import com.keralty.aeenviodoc.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;
import org.slf4j.LoggerFactory;

public class AgentexProductoJpaRepositoryImpl extends AbstractRepository implements AgentexProductoJpaRepositoryCustom {

    @Inject
    public AgentexProductoJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<AgentexProducto> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<AgentexProducto> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<AgentexProducto> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<AgentexProducto> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, AgentexProducto.class);
    }

    @Override	
    public List<AgentexProducto> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<AgentexProducto> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public Long nextAgentexProductoId(Long funcionarioAsesorId) {
        try {
            SQLMax sql = new SQLMax(null, AgentexProductoConstants.CLASS_NAME, AgentexProductoConstants.CLASS_NAME + "." + AgentexProductoConstants.ATTR_FIELD_AGENTEXPRODUCTOID);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(AgentexProductoConstants.ROLE_NAME_ASESOR + "." + AsesorConstants.ATTR_FIELD_FUNCIONARIOID, "Autonumerico"));
            comparison1.setOperator("=");
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(funcionarioAsesorId, "Autonumerico"));
            conjunction.addComparison(comparison1);

            sql.getWherePart().addConjunction(conjunction);

            Long tempResult = new TypedQueryBuilder<>(em, Long.class).build(sql).getSingleResult();
            return (tempResult != null ? tempResult + 1L : 1L);
        } catch (Exception e) {
            LoggerFactory.getLogger(AgentexProducto.class).error(e.getMessage());
            return 1L;
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
