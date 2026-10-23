package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.global.AnexoCConstants;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.AnexoC;
import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.oid.AnexoCOid;
import com.keralty.aeenviodoc.persistence.oid.BeneficiarioCOid;
import com.keralty.aeenviodoc.persistence.oid.PeriodicidadOid;
import com.keralty.aeenviodoc.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeenviodoc.persistence.Periodicidad;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import com.keralty.aeenviodoc.repository.AnexoCJpaRepository;
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
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class AnexoCService extends AbstractService {

    AnexoCJpaRepository repository;

    AnexoCService anexoCSrv;

    PeriodicidadService periodicidadSrv;

    BeneficiarioCService beneficiarioCSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    @Inject
    public AnexoCService(
            AnexoCService anexoCSrv,
            PeriodicidadService periodicidadSrv,
            BeneficiarioCService beneficiarioCSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            AnexoCJpaRepository repository) {
        this.anexoCSrv = anexoCSrv;
        this.periodicidadSrv = periodicidadSrv;
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, AnexoCConstants.CLASS_NAME);
    }

    private List<AnexoC> solveAnexoCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, AnexoCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveAnexoCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, AnexoCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class AnexoC
     *
     * @return Population of class AnexoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<AnexoC> get() {
        List<AnexoC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class AnexoC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class AnexoC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<AnexoC> get(QueryRequest queryRequest) {
        return solveAnexoCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveAnexoCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveAnexoCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveAnexoCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of AnexoC by ID
     *
     * @param anexoCIdentificador Identification field anexoCIdentificador
     *
     * @return Instance of AnexoC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public AnexoC getById(Long anexoCIdentificador) {
        return getByOID(new AnexoCOid(anexoCIdentificador));
    }
    
    /*
     * Returns an instance of AnexoC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of AnexoC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public AnexoC getByOID(AnexoCOid oid) {
        Optional<AnexoC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of AnexoC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of AnexoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public AnexoC getByOIDWithHV(AnexoCOid oid) {
        Optional<AnexoC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<AnexoC> getInstanceByOid(AnexoCOid oid) {
        return oid != null && oid.getAnexoCIdentificador() != null
               ? repository.findById(oid.getAnexoCIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of AnexoC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of AnexoC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public AnexoC getByJsonOid(String jsonOid) {
        AnexoCOid oid = new AnexoCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of AnexoC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of AnexoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<AnexoC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveAnexoCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of AnexoC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of AnexoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<AnexoC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of AnexoC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of AnexoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getAnexoCDynamic(AnexoCOid oid, String displaySetItems) {
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
    public String getSuppInfo(AnexoCOid oid, String dsName) {
        AnexoC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(AnexoC instance, String dsName) {
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
            com.keralty.aeenviodoc.viewmodel.anexoc.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = anexoCSrv.crearTxn(request);
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
            com.keralty.aeenviodoc.viewmodel.anexoc.siu.CrearRequest request) throws ModelException {
        request.setAnexoCcrearpagrPeriodicidadCInstance(periodicidadSrv.getByOIDWithHV(request.getAnexoCcrearpagrPeriodicidadC()));
        request.setAnexoCcrearpagrBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getAnexoCcrearpagrBeneficiarioC()));
        request.setAnexoCcrearpagrTipoIdentificacionCInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getAnexoCcrearpagrTipoIdentificacionC()));
        return anexoCSrv.crearImplementation(request);
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
            com.keralty.aeenviodoc.viewmodel.anexoc.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad anexoCcrearpagrPeriodicidadC = request.getAnexoCcrearpagrPeriodicidadCInstance();
        BeneficiarioC anexoCcrearpagrBeneficiarioC = request.getAnexoCcrearpagrBeneficiarioCInstance();
        TipoIdentificacion anexoCcrearpagrTipoIdentificacionC = request.getAnexoCcrearpagrTipoIdentificacionCInstance();
        String anexoCcrearpatrCodigoPlanAnexo = request.getAnexoCcrearpatrCodigoPlanAnexo();
        String anexoCcrearpatrCodigoTarifa = request.getAnexoCcrearpatrCodigoTarifa();
        Date anexoCcrearpatrFechaInicioVigencia = request.getAnexoCcrearpatrFechaInicioVigencia();
        Date anexoCcrearpatrFechaUltimaRegla = request.getAnexoCcrearpatrFechaUltimaRegla();
        Long anexoCcrearpatrNumCuotasPago = request.getAnexoCcrearpatrNumCuotasPago();
        String anexoCcrearpatrNumIdentificacion = request.getAnexoCcrearpatrNumIdentificacion();
        String anexoCcrearpatrCodSucursal = request.getAnexoCcrearpatrCodSucursal();

        request.checkArguments();
        AnexoC instance = new AnexoC();
        if (anexoCcrearpagrPeriodicidadC != null && !anexoCcrearpagrPeriodicidadC.isNull()) {
            instance.add2PeriodicidadC(anexoCcrearpagrPeriodicidadC);
        }

        instance.add2BeneficiarioC(anexoCcrearpagrBeneficiarioC);

        if (anexoCcrearpagrTipoIdentificacionC != null && !anexoCcrearpagrTipoIdentificacionC.isNull()) {
            instance.add2TipoIdentificacionC(anexoCcrearpagrTipoIdentificacionC);
        }

        instance.setAnexoCIdentificador(Long.valueOf(0));
        instance.setAnexoCCodigoPlanAnexo(anexoCcrearpatrCodigoPlanAnexo);
        instance.setAnexoCCodigoTarifa(anexoCcrearpatrCodigoTarifa);
        instance.setAnexoCFechaInicioVigencia(anexoCcrearpatrFechaInicioVigencia);
        instance.setAnexoCFechaUltimaRegla(anexoCcrearpatrFechaUltimaRegla);
        instance.setAnexoCNumCuotasPago(anexoCcrearpatrNumCuotasPago);
        instance.setAnexoCNumIdentificacion(anexoCcrearpatrNumIdentificacion);
        instance.setAnexoCCodSucursal(anexoCcrearpatrCodSucursal);

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
        select.getOrderByPart().addItem(Constants.normalizePath(AnexoCConstants.CLASS_NAME, AnexoCConstants.ATTR_FIELD_ANEXOCIDENTIFICADOR, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildAnexoCRelatedInstance(relatedInstanceJsonOid);
        }
        if (AnexoCConstants.ROLE_NAME_BENEFICIARIOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildAnexoCRelatedInstance4BeneficiarioC(relatedInstanceJsonOid, remainingPath);
        }
        if (AnexoCConstants.ROLE_NAME_PERIODICIDADC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildAnexoCRelatedInstance4Periodicidad(relatedInstanceJsonOid, remainingPath);
        }
        if (AnexoCConstants.ROLE_NAME_TIPOIDENTIFICACIONC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildAnexoCRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildAnexoCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new AnexoC(mapper.readValue(relatedInstanceJsonOid, AnexoCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildAnexoCRelatedInstance4BeneficiarioC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new BeneficiarioC(mapper.readValue(relatedInstanceJsonOid, BeneficiarioCOid.class)): BeneficiarioC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildAnexoCRelatedInstance4Periodicidad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Periodicidad(mapper.readValue(relatedInstanceJsonOid, PeriodicidadOid.class)): Periodicidad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildAnexoCRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            AnexoCOid oid = new AnexoCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(AnexoCConstants.ATTR_FIELD_ANEXOCIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getAnexoCIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(AnexoCService.class).error(e.getMessage());
        }
    }

}
