package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.DominioConstants;
import com.keralty.aeenviodoc.global.ParentescoConstants;
import com.keralty.aeenviodoc.global.ParentescoDomConstants;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.oid.DominioOid;
import com.keralty.aeenviodoc.persistence.oid.ParentescoDomOid;
import com.keralty.aeenviodoc.persistence.oid.ParentescoOid;
import com.keralty.aeenviodoc.persistence.Parentesco;
import com.keralty.aeenviodoc.persistence.ParentescoDom;
import com.keralty.aeenviodoc.repository.ParentescoDomJpaRepository;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import com.keralty.aeenviodoc.sqlutils.SQLWhereComparison;
import com.keralty.aeenviodoc.sqlutils.SQLWhereConjunction;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;
import com.keralty.aeenviodoc.viewmodel.FilterVariableRequest;
import com.keralty.aeenviodoc.viewmodel.InstanceResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.PopulationResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.PreloadResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.QueryRequest;
import com.keralty.aeenviodoc.viewmodel.QueryResponse;
import com.keralty.aeenviodoc.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class ParentescoDomService extends AbstractService {

    ParentescoDomJpaRepository repository;

    ParentescoDomService parentescoDomSrv;

    ParentescoService parentescoSrv;

    DominioService dominioSrv;

    @Inject
    public ParentescoDomService(
            ParentescoDomService parentescoDomSrv,
            ParentescoService parentescoSrv,
            DominioService dominioSrv,
            ParentescoDomJpaRepository repository) {
        this.parentescoDomSrv = parentescoDomSrv;
        this.parentescoSrv = parentescoSrv;
        this.dominioSrv = dominioSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ParentescoDomConstants.CLASS_NAME);
    }

    private List<ParentescoDom> solveParentescoDomTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ParentescoDomConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveParentescoDomTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ParentescoDomConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ParentescoDom
     *
     * @return Population of class ParentescoDom
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParentescoDom> get() {
        List<ParentescoDom> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ParentescoDom that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ParentescoDom matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParentescoDom> get(QueryRequest queryRequest) {
        return solveParentescoDomTypedQuery(queryRequest);
    }
    
    /*
     * Returns the list of instances that matches certain search criteria
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return List of instances that matches certain search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public QueryResponse getDynamic(QueryRequest queryRequest) {
        if (queryRequest.getDisplaySetName() == null || queryRequest.getDisplaySetName().isEmpty()) {
            return new PopulationResponseViewModel(solveParentescoDomTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveParentescoDomTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns the population of a class with a certain structure
     *
     * @param relatedInstanceJsonOid Json Oid of the related instance
     *
     * @param relatedInstancePath Path to the related instance
     *
     * @param displaySetItems DisplaySet items for each returned instance
     *
     * @return List of instances
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PopulationResponseViewModel getByRelatedDynamic(String relatedInstanceJsonOid, String relatedInstancePath, String displaySetItems) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstanceJsonOid))));
        return new PopulationResponseViewModel(solveParentescoDomTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of ParentescoDom by ID
     *
     * @param dominioNumero Identification field dominioNumero
     *
     * @param parentescoDomIdentificador Identification field parentescoDomIdentificador
     *
     * @return Instance of ParentescoDom
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ParentescoDom getById(Long dominioNumero, Long parentescoDomIdentificador) {
        return getByOID(new ParentescoDomOid(dominioNumero, parentescoDomIdentificador));
    }
    
    /*
     * Returns an instance of ParentescoDom by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ParentescoDom
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ParentescoDom getByOID(ParentescoDomOid oid) {
        Optional<ParentescoDom> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ParentescoDom by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ParentescoDom
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ParentescoDom getByOIDWithHV(ParentescoDomOid oid) {
        Optional<ParentescoDom> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ParentescoDom> getInstanceByOid(ParentescoDomOid oid) {
        return oid != null && oid.getDominioNumero() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of ParentescoDom by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ParentescoDom
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ParentescoDom getByJsonOid(String jsonOid) {
        ParentescoDomOid oid = new ParentescoDomOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of ParentescoDom related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ParentescoDom
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParentescoDom> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveParentescoDomTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of ParentescoDom related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ParentescoDom
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParentescoDom> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of ParentescoDom
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ParentescoDom
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getParentescoDomDynamic(ParentescoDomOid oid, String displaySetItems) {
        return new InstanceResponseViewModel(getByOIDWithHV(oid), displaySetItems);
    }

    /*
     * Returns the supplementary info for a given instance
     *
     * @param oid Instance OID
     *
     * @param dsName DisplaySet name
     *
     * @return Supplementary info for a given instance
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public String getSuppInfo(ParentescoDomOid oid, String dsName) {
        ParentescoDom instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ParentescoDom instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (ParentescoDomConstants.FILTER_NAME_IFPARENTESCODOM.equalsIgnoreCase(filterRequest.getName()))
            iFParentescoDom(filterRequest, sql);
    }


    private void iFParentescoDom(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Parentesco voParentesco = filterRequest.hasFilterVariable(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VOPARENTESCO) ? parentescoSrv.getByOID(new ParentescoOid().fromJsonString(toJson(filterRequest.getFilterVariable(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VOPARENTESCO)))) : null;
        Dominio voDominio = filterRequest.hasFilterVariable(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VODOMINIO) ? dominioSrv.getByOID(new DominioOid().fromJsonString(toJson(filterRequest.getFilterVariable(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VODOMINIO)))) : null;

        // Parentesco = voParentesco
        if (voParentesco != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ParentescoDomConstants.ROLE_NAME_PARENTESCO, ParentescoConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VOPARENTESCO, ParentescoConstants.CLASS_NAME, ParentescoConstants.CLASS_NAME, voParentesco.getOid(), ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VOPARENTESCO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // Dominio = voDominio
        if (voDominio != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(ParentescoDomConstants.ROLE_NAME_DOMINIO, DominioConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VODOMINIO, DominioConstants.CLASS_NAME, DominioConstants.CLASS_NAME, voDominio.getOid(), ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VODOMINIO);
            comparison2.setSecondOperand(operand224);
            conjunction.addOperand(comparison2);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUParentescoDom
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ParentescoDom using displayset DSParentescoDomPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeenviodoc.viewmodel.parentescodom.ds.DSParentescoDomPIU> piuPIUParentescoDom(QueryRequest queryRequest) {
        return solveParentescoDomTypedQuery(queryRequest).stream()
                .map(com.keralty.aeenviodoc.viewmodel.parentescodom.ds.DSParentescoDomPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUParentescoDom
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUParentescoDom
     */
    public int getCount4PIUParentescoDom(QueryRequest queryRequest) {
        return solveParentescoDomTypedQuery(queryRequest).size();
    }

    // Service: eliminar

    /*
     * Entry point for service eliminar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse eliminar(
            com.keralty.aeenviodoc.viewmodel.parentescodom.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = parentescoDomSrv.eliminarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service eliminar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse eliminarTxn(
            com.keralty.aeenviodoc.viewmodel.parentescodom.siu.EliminarRequest request) throws ModelException {
        request.setParentescoDomeliminarpthisParentescoDomInstance(parentescoDomSrv.getByOIDWithHV(request.getParentescoDomeliminarpthisParentescoDom()));
        return parentescoDomSrv.eliminarImplementation(request);
    }

    /*
     * Implements service eliminar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse eliminarImplementation(
            com.keralty.aeenviodoc.viewmodel.parentescodom.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ParentescoDom parentescoDomeliminarpthisParentescoDom = request.getParentescoDomeliminarpthisParentescoDomInstance();

        request.checkArguments();
        ParentescoDom instance = parentescoDomeliminarpthisParentescoDom;


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(ParentescoDomConstants.CLASS_NAME, ParentescoDomConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ATTR_FIELD_DOMINIONUMERO, false), true);
        select.getOrderByPart().addItem(Constants.normalizePath(ParentescoDomConstants.CLASS_NAME, ParentescoDomConstants.ATTR_FIELD_PARENTESCODOMIDENTIFICADOR, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildParentescoDomRelatedInstance(relatedInstanceJsonOid);
        }
        if (ParentescoDomConstants.ROLE_NAME_DOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildParentescoDomRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        if (ParentescoDomConstants.ROLE_NAME_PARENTESCO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildParentescoDomRelatedInstance4Parentesco(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildParentescoDomRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new ParentescoDom(mapper.readValue(relatedInstanceJsonOid, ParentescoDomOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildParentescoDomRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildParentescoDomRelatedInstance4Parentesco(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Parentesco(mapper.readValue(relatedInstanceJsonOid, ParentescoOid.class)): Parentesco.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ParentescoDomOid oid = new ParentescoDomOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ParentescoDomConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ATTR_FIELD_DOMINIONUMERO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getDominioNumero(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(ParentescoDomConstants.ATTR_FIELD_PARENTESCODOMIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getParentescoDomIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(ParentescoDomService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
