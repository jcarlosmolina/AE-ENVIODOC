package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.DirSucursalCConstants;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.oid.DepartamentoOid;
import com.keralty.aeenviodoc.persistence.oid.DirSucursalCOid;
import com.keralty.aeenviodoc.persistence.oid.MunicipioOid;
import com.keralty.aeenviodoc.persistence.oid.PaisOid;
import com.keralty.aeenviodoc.persistence.oid.PersonaCOid;
import com.keralty.aeenviodoc.persistence.oid.RegionalOid;
import com.keralty.aeenviodoc.persistence.oid.TelefonoCOid;
import com.keralty.aeenviodoc.persistence.Pais;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.Regional;
import com.keralty.aeenviodoc.persistence.TelefonoC;
import com.keralty.aeenviodoc.repository.DirSucursalCJpaRepository;
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
public class DirSucursalCService extends AbstractService {

    DirSucursalCJpaRepository repository;

    DirSucursalCService dirSucursalCSrv;

    PersonaCService personaCSrv;

    MunicipioService municipioSrv;

    DepartamentoService departamentoSrv;

    PaisService paisSrv;

    RegionalService regionalSrv;

    TelefonoCService telefonoCSrv;

    @Inject
    public DirSucursalCService(
            DirSucursalCService dirSucursalCSrv,
            PersonaCService personaCSrv,
            MunicipioService municipioSrv,
            DepartamentoService departamentoSrv,
            PaisService paisSrv,
            RegionalService regionalSrv,
            TelefonoCService telefonoCSrv,
            DirSucursalCJpaRepository repository) {
        this.dirSucursalCSrv = dirSucursalCSrv;
        this.personaCSrv = personaCSrv;
        this.municipioSrv = municipioSrv;
        this.departamentoSrv = departamentoSrv;
        this.paisSrv = paisSrv;
        this.regionalSrv = regionalSrv;
        this.telefonoCSrv = telefonoCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, DirSucursalCConstants.CLASS_NAME);
    }

    private List<DirSucursalC> solveDirSucursalCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, DirSucursalCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveDirSucursalCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, DirSucursalCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class DirSucursalC
     *
     * @return Population of class DirSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DirSucursalC> get() {
        List<DirSucursalC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class DirSucursalC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class DirSucursalC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DirSucursalC> get(QueryRequest queryRequest) {
        return solveDirSucursalCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveDirSucursalCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveDirSucursalCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveDirSucursalCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of DirSucursalC by ID
     *
     * @param dirSucursalCCodigo Identification field dirSucursalCCodigo
     *
     * @return Instance of DirSucursalC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public DirSucursalC getById(Long dirSucursalCCodigo) {
        return getByOID(new DirSucursalCOid(dirSucursalCCodigo));
    }
    
    /*
     * Returns an instance of DirSucursalC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of DirSucursalC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public DirSucursalC getByOID(DirSucursalCOid oid) {
        Optional<DirSucursalC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of DirSucursalC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of DirSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public DirSucursalC getByOIDWithHV(DirSucursalCOid oid) {
        Optional<DirSucursalC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<DirSucursalC> getInstanceByOid(DirSucursalCOid oid) {
        return oid != null && oid.getDirSucursalCCodigo() != null
               ? repository.findById(oid.getDirSucursalCCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of DirSucursalC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of DirSucursalC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public DirSucursalC getByJsonOid(String jsonOid) {
        DirSucursalCOid oid = new DirSucursalCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of DirSucursalC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of DirSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DirSucursalC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveDirSucursalCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of DirSucursalC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of DirSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DirSucursalC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of DirSucursalC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of DirSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getDirSucursalCDynamic(DirSucursalCOid oid, String displaySetItems) {
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
    public String getSuppInfo(DirSucursalCOid oid, String dsName) {
        DirSucursalC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(DirSucursalC instance, String dsName) {
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
            com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.crearTxn(request);
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
            com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.CrearRequest request) throws ModelException {
        request.setDirSucursalCcrearpagrPersonaCInstance(personaCSrv.getByOIDWithHV(request.getDirSucursalCcrearpagrPersonaC()));
        request.setDirSucursalCcrearpagrMunicipioCInstance(municipioSrv.getByOIDWithHV(request.getDirSucursalCcrearpagrMunicipioC()));
        request.setDirSucursalCcrearpagrDepartamentoCInstance(departamentoSrv.getByOIDWithHV(request.getDirSucursalCcrearpagrDepartamentoC()));
        request.setDirSucursalCcrearpagrPaisCInstance(paisSrv.getByOIDWithHV(request.getDirSucursalCcrearpagrPaisC()));
        request.setDirSucursalCcrearpagrRegionalCInstance(regionalSrv.getByOIDWithHV(request.getDirSucursalCcrearpagrRegionalC()));
        return dirSucursalCSrv.crearImplementation(request);
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
            com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC dirSucursalCcrearpagrPersonaC = request.getDirSucursalCcrearpagrPersonaCInstance();
        Municipio dirSucursalCcrearpagrMunicipioC = request.getDirSucursalCcrearpagrMunicipioCInstance();
        Departamento dirSucursalCcrearpagrDepartamentoC = request.getDirSucursalCcrearpagrDepartamentoCInstance();
        Pais dirSucursalCcrearpagrPaisC = request.getDirSucursalCcrearpagrPaisCInstance();
        Regional dirSucursalCcrearpagrRegionalC = request.getDirSucursalCcrearpagrRegionalCInstance();
        String dirSucursalCcrearpatrCodLegalSucursal = request.getDirSucursalCcrearpatrCodLegalSucursal();
        String dirSucursalCcrearpatrNombre = request.getDirSucursalCcrearpatrNombre();
        Boolean dirSucursalCcrearpatrEsPrincipal = request.getDirSucursalCcrearpatrEsPrincipal();
        Boolean dirSucursalCcrearpatrActiva = request.getDirSucursalCcrearpatrActiva();
        String dirSucursalCcrearpatrDireccion = request.getDirSucursalCcrearpatrDireccion();
        String dirSucursalCcrearpatrMail = request.getDirSucursalCcrearpatrMail();

        request.checkArguments();
        DirSucursalC instance = new DirSucursalC();
        instance.add2PersonaC(dirSucursalCcrearpagrPersonaC);

        if (dirSucursalCcrearpagrMunicipioC != null && !dirSucursalCcrearpagrMunicipioC.isNull()) {
            instance.add2MunicipioC(dirSucursalCcrearpagrMunicipioC);
        }

        if (dirSucursalCcrearpagrDepartamentoC != null && !dirSucursalCcrearpagrDepartamentoC.isNull()) {
            instance.add2DepartamentoC(dirSucursalCcrearpagrDepartamentoC);
        }

        if (dirSucursalCcrearpagrPaisC != null && !dirSucursalCcrearpagrPaisC.isNull()) {
            instance.add2PaisC(dirSucursalCcrearpagrPaisC);
        }

        if (dirSucursalCcrearpagrRegionalC != null && !dirSucursalCcrearpagrRegionalC.isNull()) {
            instance.add2RegionalC(dirSucursalCcrearpagrRegionalC);
        }

        instance.setDirSucursalCCodigo(Long.valueOf(0));
        instance.setDirSucursalCCodLegalSucursal(dirSucursalCcrearpatrCodLegalSucursal);
        instance.setDirSucursalCNombre(dirSucursalCcrearpatrNombre);
        instance.setDirSucursalCEsPrincipal(dirSucursalCcrearpatrEsPrincipal);
        instance.setDirSucursalCActiva(dirSucursalCcrearpatrActiva);
        instance.setDirSucursalCDireccion(dirSucursalCcrearpatrDireccion);
        instance.setDirSucursalCMail(dirSucursalCcrearpatrMail);

        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: TCREAR

    /*
     * Entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREAR(
            com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.tCREARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREARTxn(
            com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.TCREARRequest request) throws ModelException {
        request.setDirSucursalCTCREARpagrMunicipioCInstance(municipioSrv.getByOIDWithHV(request.getDirSucursalCTCREARpagrMunicipioC()));
        request.setDirSucursalCTCREARpagrDepartamentoCInstance(departamentoSrv.getByOIDWithHV(request.getDirSucursalCTCREARpagrDepartamentoC()));
        request.setDirSucursalCTCREARpagrPaisCInstance(paisSrv.getByOIDWithHV(request.getDirSucursalCTCREARpagrPaisC()));
        request.setDirSucursalCTCREARpagrPersonaCInstance(personaCSrv.getByOIDWithHV(request.getDirSucursalCTCREARpagrPersonaC()));
        request.setDirSucursalCTCREARpagrRegionalCInstance(regionalSrv.getByOIDWithHV(request.getDirSucursalCTCREARpagrRegionalC()));
        return dirSucursalCSrv.tCREARImplementation(request);
    }

    /*
     * Implements service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREARImplementation(
            com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC dirSucursalCTCREARpagrPersonaC = request.getDirSucursalCTCREARpagrPersonaCInstance();
        Municipio dirSucursalCTCREARpagrMunicipioC = request.getDirSucursalCTCREARpagrMunicipioCInstance();
        Departamento dirSucursalCTCREARpagrDepartamentoC = request.getDirSucursalCTCREARpagrDepartamentoCInstance();
        Pais dirSucursalCTCREARpagrPaisC = request.getDirSucursalCTCREARpagrPaisCInstance();
        Regional dirSucursalCTCREARpagrRegionalC = request.getDirSucursalCTCREARpagrRegionalCInstance();
        String dirSucursalCTCREARpatrCodLegalSucursal = request.getDirSucursalCTCREARpatrCodLegalSucursal();
        String dirSucursalCTCREARpatrNombre = request.getDirSucursalCTCREARpatrNombre();
        Boolean dirSucursalCTCREARpatrEsPrincipal = request.getDirSucursalCTCREARpatrEsPrincipal();
        Boolean dirSucursalCTCREARpatrActiva = request.getDirSucursalCTCREARpatrActiva();
        String dirSucursalCTCREARpatrDireccion = request.getDirSucursalCTCREARpatrDireccion();
        String dirSucursalCTCREARpatrMail = request.getDirSucursalCTCREARpatrMail();
        String dirSucursalCTCREARpTelefonoFijo = request.getDirSucursalCTCREARpTelefonoFijo();
        String dirSucursalCTCREARpTelefonoCelular = request.getDirSucursalCTCREARpTelefonoCelular();

        request.checkArguments();
        DirSucursalC instance;

        com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.CrearRequest step1Request = new com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.CrearRequest();
        step1Request.setDirSucursalCcrearpagrPersonaCInstance(dirSucursalCTCREARpagrPersonaC);
        step1Request.setDirSucursalCcrearpagrMunicipioCInstance(dirSucursalCTCREARpagrMunicipioC);
        step1Request.setDirSucursalCcrearpagrDepartamentoCInstance(dirSucursalCTCREARpagrDepartamentoC);
        step1Request.setDirSucursalCcrearpagrPaisCInstance(dirSucursalCTCREARpagrPaisC);
        step1Request.setDirSucursalCcrearpagrRegionalCInstance(dirSucursalCTCREARpagrRegionalC);
        step1Request.setDirSucursalCcrearpatrCodLegalSucursal(dirSucursalCTCREARpatrCodLegalSucursal);
        step1Request.setDirSucursalCcrearpatrNombre(dirSucursalCTCREARpatrNombre);
        step1Request.setDirSucursalCcrearpatrEsPrincipal(dirSucursalCTCREARpatrEsPrincipal);
        step1Request.setDirSucursalCcrearpatrActiva(dirSucursalCTCREARpatrActiva);
        step1Request.setDirSucursalCcrearpatrDireccion(dirSucursalCTCREARpatrDireccion);
        step1Request.setDirSucursalCcrearpatrMail(dirSucursalCTCREARpatrMail);
        instance = (DirSucursalC) dirSucursalCSrv.crearImplementation(step1Request).getInstance();

        /* Action 2: IF (pTelefonoFijo <> NULL) */
        boolean ifCondition2 = dirSucursalCTCREARpTelefonoFijo != null;
        if (ifCondition2) {
            com.keralty.aeenviodoc.viewmodel.telefonoc.siu.CrearRequest step20Request = new com.keralty.aeenviodoc.viewmodel.telefonoc.siu.CrearRequest();
            step20Request.setTelefonoCcrearpagrDirSucursalCInstance(instance);
            step20Request.setTelefonoCcrearpagrDireccionCInstance(null);
            step20Request.setTelefonoCcrearpatrTelefono(dirSucursalCTCREARpTelefonoFijo);
            step20Request.setTelefonoCcrearpatrIndicativoPais(null);
            step20Request.setTelefonoCcrearpatrCodigoArea(null);
            step20Request.setTelefonoCcrearpatrExt(null);
            step20Request.setTelefonoCcrearpatrCodigo(Long.valueOf(1));
            telefonoCSrv.crearImplementation(step20Request);
        }

        /* Action 3: IF (pTelefonoCelular <> NULL) */
        boolean ifCondition3 = dirSucursalCTCREARpTelefonoCelular != null;
        if (ifCondition3) {
            com.keralty.aeenviodoc.viewmodel.telefonoc.siu.CrearRequest step30Request = new com.keralty.aeenviodoc.viewmodel.telefonoc.siu.CrearRequest();
            step30Request.setTelefonoCcrearpagrDirSucursalCInstance(instance);
            step30Request.setTelefonoCcrearpagrDireccionCInstance(null);
            step30Request.setTelefonoCcrearpatrTelefono(dirSucursalCTCREARpTelefonoCelular);
            step30Request.setTelefonoCcrearpatrIndicativoPais(null);
            step30Request.setTelefonoCcrearpatrCodigoArea(null);
            step30Request.setTelefonoCcrearpatrExt(null);
            step30Request.setTelefonoCcrearpatrCodigo(Long.valueOf(2));
            telefonoCSrv.crearImplementation(step30Request);
        }

        response.setInstance(getByOID(instance.getOid()));

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
        select.getOrderByPart().addItem(Constants.normalizePath(DirSucursalCConstants.CLASS_NAME, DirSucursalCConstants.ATTR_FIELD_DIRSUCURSALCCODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildDirSucursalCRelatedInstance(relatedInstanceJsonOid);
        }
        if (DirSucursalCConstants.ROLE_NAME_TELEFONOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4TelefonoC(relatedInstanceJsonOid, remainingPath);
        }
        if (DirSucursalCConstants.ROLE_NAME_PERSONAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        if (DirSucursalCConstants.ROLE_NAME_MUNICIPIOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        if (DirSucursalCConstants.ROLE_NAME_DEPARTAMENTOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4Departamento(relatedInstanceJsonOid, remainingPath);
        }
        if (DirSucursalCConstants.ROLE_NAME_PAISC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4Pais(relatedInstanceJsonOid, remainingPath);
        }
        if (DirSucursalCConstants.ROLE_NAME_REGIONALC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4Regional(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildDirSucursalCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new DirSucursalC(mapper.readValue(relatedInstanceJsonOid, DirSucursalCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4TelefonoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TelefonoC(mapper.readValue(relatedInstanceJsonOid, TelefonoCOid.class)): TelefonoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4Departamento(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Departamento(mapper.readValue(relatedInstanceJsonOid, DepartamentoOid.class)): Departamento.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4Pais(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Pais(mapper.readValue(relatedInstanceJsonOid, PaisOid.class)): Pais.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4Regional(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Regional(mapper.readValue(relatedInstanceJsonOid, RegionalOid.class)): Regional.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            DirSucursalCOid oid = new DirSucursalCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(DirSucursalCConstants.ATTR_FIELD_DIRSUCURSALCCODIGO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getDirSucursalCCodigo(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(DirSucursalCService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
