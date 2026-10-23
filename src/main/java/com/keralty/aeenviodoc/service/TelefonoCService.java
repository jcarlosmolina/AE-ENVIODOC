package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.TelefonoCConstants;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.persistence.oid.DireccionCOid;
import com.keralty.aeenviodoc.persistence.oid.DirSucursalCOid;
import com.keralty.aeenviodoc.persistence.oid.TelefonoCOid;
import com.keralty.aeenviodoc.persistence.TelefonoC;
import com.keralty.aeenviodoc.repository.TelefonoCJpaRepository;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import com.keralty.aeenviodoc.sqlutils.SQLWhereComparison;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandPathAttribute;
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
public class TelefonoCService extends AbstractService {

    TelefonoCJpaRepository repository;

    TelefonoCService telefonoCSrv;

    DirSucursalCService dirSucursalCSrv;

    DireccionCService direccionCSrv;

    @Inject
    public TelefonoCService(
            TelefonoCService telefonoCSrv,
            DirSucursalCService dirSucursalCSrv,
            DireccionCService direccionCSrv,
            TelefonoCJpaRepository repository) {
        this.telefonoCSrv = telefonoCSrv;
        this.dirSucursalCSrv = dirSucursalCSrv;
        this.direccionCSrv = direccionCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TelefonoCConstants.CLASS_NAME);
    }

    private List<TelefonoC> solveTelefonoCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TelefonoCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTelefonoCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TelefonoCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class TelefonoC
     *
     * @return Population of class TelefonoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TelefonoC> get() {
        List<TelefonoC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class TelefonoC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class TelefonoC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TelefonoC> get(QueryRequest queryRequest) {
        return solveTelefonoCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTelefonoCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTelefonoCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveTelefonoCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of TelefonoC by ID
     *
     * @param telefonoCID Identification field telefonoCID
     *
     * @return Instance of TelefonoC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TelefonoC getById(Long telefonoCID) {
        return getByOID(new TelefonoCOid(telefonoCID));
    }
    
    /*
     * Returns an instance of TelefonoC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TelefonoC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TelefonoC getByOID(TelefonoCOid oid) {
        Optional<TelefonoC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of TelefonoC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TelefonoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public TelefonoC getByOIDWithHV(TelefonoCOid oid) {
        Optional<TelefonoC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<TelefonoC> getInstanceByOid(TelefonoCOid oid) {
        return oid != null && oid.getTelefonoCID() != null
               ? repository.findById(oid.getTelefonoCID())
               : Optional.empty();
    }

    /*
     * Returns an instance of TelefonoC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of TelefonoC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TelefonoC getByJsonOid(String jsonOid) {
        TelefonoCOid oid = new TelefonoCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of TelefonoC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TelefonoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TelefonoC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveTelefonoCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of TelefonoC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TelefonoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TelefonoC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of TelefonoC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of TelefonoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTelefonoCDynamic(TelefonoCOid oid, String displaySetItems) {
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
    public String getSuppInfo(TelefonoCOid oid, String dsName) {
        TelefonoC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(TelefonoC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods



    // Service: crear

    /*
     * Entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse crear(
            com.keralty.aeenviodoc.viewmodel.telefonoc.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = telefonoCSrv.crearTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse crearTxn(
            com.keralty.aeenviodoc.viewmodel.telefonoc.siu.CrearRequest request) throws ModelException {
        request.setTelefonoCcrearpagrDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getTelefonoCcrearpagrDirSucursalC()));
        request.setTelefonoCcrearpagrDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getTelefonoCcrearpagrDireccionC()));
        return telefonoCSrv.crearImplementation(request);
    }

    /*
     * Implements service crear
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse crearImplementation(
            com.keralty.aeenviodoc.viewmodel.telefonoc.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC telefonoCcrearpagrDirSucursalC = request.getTelefonoCcrearpagrDirSucursalCInstance();
        DireccionC telefonoCcrearpagrDireccionC = request.getTelefonoCcrearpagrDireccionCInstance();
        String telefonoCcrearpatrTelefono = request.getTelefonoCcrearpatrTelefono();
        String telefonoCcrearpatrIndicativoPais = request.getTelefonoCcrearpatrIndicativoPais();
        String telefonoCcrearpatrCodigoArea = request.getTelefonoCcrearpatrCodigoArea();
        String telefonoCcrearpatrExt = request.getTelefonoCcrearpatrExt();
        Long telefonoCcrearpatrCodigo = request.getTelefonoCcrearpatrCodigo();

        request.checkArguments();
        TelefonoC instance = new TelefonoC();
        if (telefonoCcrearpagrDirSucursalC != null && !telefonoCcrearpagrDirSucursalC.isNull()) {
            instance.add2DirSucursalC(telefonoCcrearpagrDirSucursalC);
        }

        if (telefonoCcrearpagrDireccionC != null && !telefonoCcrearpagrDireccionC.isNull()) {
            instance.add2DireccionC(telefonoCcrearpagrDireccionC);
        }

        instance.setTelefonoCID(Long.valueOf(0));
        instance.setTelefonoCTelefono(telefonoCcrearpatrTelefono);
        instance.setTelefonoCIndicativoPais(telefonoCcrearpatrIndicativoPais);
        instance.setTelefonoCCodigoArea(telefonoCcrearpatrCodigoArea);
        instance.setTelefonoCExt(telefonoCcrearpatrExt);
        instance.setTelefonoCCodigo(telefonoCcrearpatrCodigo);

        response.setInstance(repository.save(instance));

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
        select.getOrderByPart().addItem(Constants.normalizePath(TelefonoCConstants.CLASS_NAME, TelefonoCConstants.ATTR_FIELD_TELEFONOCID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildTelefonoCRelatedInstance(relatedInstanceJsonOid);
        }
        if (TelefonoCConstants.ROLE_NAME_DIRSUCURSALC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTelefonoCRelatedInstance4DirSucursalC(relatedInstanceJsonOid, remainingPath);
        }
        if (TelefonoCConstants.ROLE_NAME_DIRECCIONC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTelefonoCRelatedInstance4DireccionC(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildTelefonoCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new TelefonoC(mapper.readValue(relatedInstanceJsonOid, TelefonoCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildTelefonoCRelatedInstance4DirSucursalC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DirSucursalC(mapper.readValue(relatedInstanceJsonOid, DirSucursalCOid.class)): DirSucursalC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildTelefonoCRelatedInstance4DireccionC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DireccionC(mapper.readValue(relatedInstanceJsonOid, DireccionCOid.class)): DireccionC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            TelefonoCOid oid = new TelefonoCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TelefonoCConstants.ATTR_FIELD_TELEFONOCID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTelefonoCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TelefonoCService.class).error(e.getMessage());
        }
    }

}
