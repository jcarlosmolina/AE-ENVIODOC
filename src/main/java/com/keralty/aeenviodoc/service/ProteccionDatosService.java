package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.ProteccionDatosConstants;
import com.keralty.aeenviodoc.global.UserFunctions;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.oid.ProductosOid;
import com.keralty.aeenviodoc.persistence.oid.ProteccionDatosOid;
import com.keralty.aeenviodoc.persistence.oid.SolicitudNovedadOid;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Productos;
import com.keralty.aeenviodoc.persistence.ProteccionDatos;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.SolicitudNovedad;
import com.keralty.aeenviodoc.repository.ProteccionDatosJpaRepository;
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
public class ProteccionDatosService extends AbstractService {

    ProteccionDatosJpaRepository repository;

    ProteccionDatosService proteccionDatosSrv;

    ProductosService productosSrv;

    SolicitudService solicitudSrv;

    SolicitudNovedadService solicitudNovedadSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public ProteccionDatosService(
            ProteccionDatosService proteccionDatosSrv,
            ProductosService productosSrv,
            SolicitudService solicitudSrv,
            SolicitudNovedadService solicitudNovedadSrv,
            UserFunctions userFunctionsSrv,
            ProteccionDatosJpaRepository repository) {
        this.proteccionDatosSrv = proteccionDatosSrv;
        this.productosSrv = productosSrv;
        this.solicitudSrv = solicitudSrv;
        this.solicitudNovedadSrv = solicitudNovedadSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ProteccionDatosConstants.CLASS_NAME);
    }

    private List<ProteccionDatos> solveProteccionDatosTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ProteccionDatosConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveProteccionDatosTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ProteccionDatosConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ProteccionDatos
     *
     * @return Population of class ProteccionDatos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProteccionDatos> get() {
        List<ProteccionDatos> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ProteccionDatos that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ProteccionDatos matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProteccionDatos> get(QueryRequest queryRequest) {
        return solveProteccionDatosTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveProteccionDatosTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveProteccionDatosTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveProteccionDatosTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of ProteccionDatos by ID
     *
     * @param proteccionDatosIdentificador Identification field proteccionDatosIdentificador
     *
     * @return Instance of ProteccionDatos
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ProteccionDatos getById(Long proteccionDatosIdentificador) {
        return getByOID(new ProteccionDatosOid(proteccionDatosIdentificador));
    }
    
    /*
     * Returns an instance of ProteccionDatos by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ProteccionDatos
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ProteccionDatos getByOID(ProteccionDatosOid oid) {
        Optional<ProteccionDatos> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ProteccionDatos by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ProteccionDatos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ProteccionDatos getByOIDWithHV(ProteccionDatosOid oid) {
        Optional<ProteccionDatos> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ProteccionDatos> getInstanceByOid(ProteccionDatosOid oid) {
        return oid != null && oid.getProteccionDatosIdentificador() != null
               ? repository.findById(oid.getProteccionDatosIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of ProteccionDatos by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ProteccionDatos
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ProteccionDatos getByJsonOid(String jsonOid) {
        ProteccionDatosOid oid = new ProteccionDatosOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of ProteccionDatos related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ProteccionDatos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProteccionDatos> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveProteccionDatosTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of ProteccionDatos related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ProteccionDatos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProteccionDatos> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of ProteccionDatos
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ProteccionDatos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getProteccionDatosDynamic(ProteccionDatosOid oid, String displaySetItems) {
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
    public String getSuppInfo(ProteccionDatosOid oid, String dsName) {
        ProteccionDatos instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ProteccionDatos instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUProteccionDatosNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ProteccionDatos using displayset DSProteccionDatosNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeenviodoc.viewmodel.protecciondatos.ds.DSProteccionDatosNull> piuPIUProteccionDatosNull(QueryRequest queryRequest) {
        return solveProteccionDatosTypedQuery(queryRequest).stream()
                .map(com.keralty.aeenviodoc.viewmodel.protecciondatos.ds.DSProteccionDatosNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUProteccionDatosNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUProteccionDatosNull
     */
    public int getCount4PIUProteccionDatosNull(QueryRequest queryRequest) {
        return solveProteccionDatosTypedQuery(queryRequest).size();
    }

    // Service: InsProductoProteccionDatos

    /*
     * Entry point for service InsProductoProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insProductoProteccionDatos(
            com.keralty.aeenviodoc.viewmodel.protecciondatos.siu.InsProductoProteccionDatosRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = proteccionDatosSrv.insProductoProteccionDatosTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsProductoProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insProductoProteccionDatosTxn(
            com.keralty.aeenviodoc.viewmodel.protecciondatos.siu.InsProductoProteccionDatosRequest request) throws ModelException {
        request.setProteccionDatosInsProductoProteccionDatospthisProteccionDatosInstance(proteccionDatosSrv.getByOIDWithHV(request.getProteccionDatosInsProductoProteccionDatospthisProteccionDatos()));
        request.setProteccionDatosInsProductoProteccionDatospevcProductosInstance(productosSrv.getByOIDWithHV(request.getProteccionDatosInsProductoProteccionDatospevcProductos()));
        return proteccionDatosSrv.insProductoProteccionDatosImplementation(request, true);
    }

    /*
     * Implements service InsProductoProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insProductoProteccionDatosImplementation(
            com.keralty.aeenviodoc.viewmodel.protecciondatos.siu.InsProductoProteccionDatosRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ProteccionDatos proteccionDatosInsProductoProteccionDatospthisProteccionDatos = request.getProteccionDatosInsProductoProteccionDatospthisProteccionDatosInstance();
        Productos proteccionDatosInsProductoProteccionDatospevcProductos = request.getProteccionDatosInsProductoProteccionDatospevcProductosInstance();

        request.checkArguments();
        ProteccionDatos instance = proteccionDatosInsProductoProteccionDatospthisProteccionDatos;

        instance.add2ProductoT(proteccionDatosInsProductoProteccionDatospevcProductos);
        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.productos.siu.InsProductoProteccionDatosRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.productos.siu.InsProductoProteccionDatosRequest();
            relatedRequest.setProductosInsProductoProteccionDatospthisProductoInstance(proteccionDatosInsProductoProteccionDatospevcProductos);
            relatedRequest.setProductosInsProductoProteccionDatospevcProteccionDatosInstance(proteccionDatosInsProductoProteccionDatospthisProteccionDatos);
            productosSrv.insProductoProteccionDatosImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsSolicitudProteccionDatos

    /*
     * Entry point for service InsSolicitudProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insSolicitudProteccionDatos(
            com.keralty.aeenviodoc.viewmodel.protecciondatos.siu.InsSolicitudProteccionDatosRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = proteccionDatosSrv.insSolicitudProteccionDatosTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsSolicitudProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insSolicitudProteccionDatosTxn(
            com.keralty.aeenviodoc.viewmodel.protecciondatos.siu.InsSolicitudProteccionDatosRequest request) throws ModelException {
        request.setProteccionDatosInsSolicitudProteccionDatospthisProteccionDatosInstance(proteccionDatosSrv.getByOIDWithHV(request.getProteccionDatosInsSolicitudProteccionDatospthisProteccionDatos()));
        request.setProteccionDatosInsSolicitudProteccionDatospevcSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getProteccionDatosInsSolicitudProteccionDatospevcSolicitud()));
        return proteccionDatosSrv.insSolicitudProteccionDatosImplementation(request, true);
    }

    /*
     * Implements service InsSolicitudProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insSolicitudProteccionDatosImplementation(
            com.keralty.aeenviodoc.viewmodel.protecciondatos.siu.InsSolicitudProteccionDatosRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ProteccionDatos proteccionDatosInsSolicitudProteccionDatospthisProteccionDatos = request.getProteccionDatosInsSolicitudProteccionDatospthisProteccionDatosInstance();
        Solicitud proteccionDatosInsSolicitudProteccionDatospevcSolicitud = request.getProteccionDatosInsSolicitudProteccionDatospevcSolicitudInstance();

        request.checkArguments();
        ProteccionDatos instance = proteccionDatosInsSolicitudProteccionDatospthisProteccionDatos;

        instance.add2Solicitud(proteccionDatosInsSolicitudProteccionDatospevcSolicitud);
        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.solicitud.siu.InsSolicitudProteccionDatosRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.solicitud.siu.InsSolicitudProteccionDatosRequest();
            relatedRequest.setSolicitudInsSolicitudProteccionDatospthisSolicitudInstance(proteccionDatosInsSolicitudProteccionDatospevcSolicitud);
            relatedRequest.setSolicitudInsSolicitudProteccionDatospevcProteccionDatosInstance(proteccionDatosInsSolicitudProteccionDatospthisProteccionDatos);
            solicitudSrv.insSolicitudProteccionDatosImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelSolicitudProteccionDatos

    /*
     * Entry point for service DelSolicitudProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delSolicitudProteccionDatos(
            com.keralty.aeenviodoc.viewmodel.protecciondatos.siu.DelSolicitudProteccionDatosRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = proteccionDatosSrv.delSolicitudProteccionDatosTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelSolicitudProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delSolicitudProteccionDatosTxn(
            com.keralty.aeenviodoc.viewmodel.protecciondatos.siu.DelSolicitudProteccionDatosRequest request) throws ModelException {
        request.setProteccionDatosDelSolicitudProteccionDatospthisProteccionDatosInstance(proteccionDatosSrv.getByOIDWithHV(request.getProteccionDatosDelSolicitudProteccionDatospthisProteccionDatos()));
        request.setProteccionDatosDelSolicitudProteccionDatospevcSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getProteccionDatosDelSolicitudProteccionDatospevcSolicitud()));
        return proteccionDatosSrv.delSolicitudProteccionDatosImplementation(request, true);
    }

    /*
     * Implements service DelSolicitudProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delSolicitudProteccionDatosImplementation(
            com.keralty.aeenviodoc.viewmodel.protecciondatos.siu.DelSolicitudProteccionDatosRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ProteccionDatos proteccionDatosDelSolicitudProteccionDatospthisProteccionDatos = request.getProteccionDatosDelSolicitudProteccionDatospthisProteccionDatosInstance();
        Solicitud proteccionDatosDelSolicitudProteccionDatospevcSolicitud = request.getProteccionDatosDelSolicitudProteccionDatospevcSolicitudInstance();

        request.checkArguments();
        ProteccionDatos instance = proteccionDatosDelSolicitudProteccionDatospthisProteccionDatos;

        instance.add2Solicitud(null);
        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.solicitud.siu.DelSolicitudProteccionDatosRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.solicitud.siu.DelSolicitudProteccionDatosRequest();
            relatedRequest.setSolicitudDelSolicitudProteccionDatospthisSolicitudInstance(proteccionDatosDelSolicitudProteccionDatospevcSolicitud);
            relatedRequest.setSolicitudDelSolicitudProteccionDatospevcProteccionDatosInstance(proteccionDatosDelSolicitudProteccionDatospthisProteccionDatos);
            solicitudSrv.delSolicitudProteccionDatosImplementation(relatedRequest, false);
        }


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
        select.getOrderByPart().addItem(Constants.normalizePath(ProteccionDatosConstants.CLASS_NAME, ProteccionDatosConstants.ATTR_FIELD_PROTECCIONDATOSIDENTIFICADOR, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildProteccionDatosRelatedInstance(relatedInstanceJsonOid);
        }
        if (ProteccionDatosConstants.ROLE_NAME_SOLICITUDNOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildProteccionDatosRelatedInstance4SolicitudNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (ProteccionDatosConstants.ROLE_NAME_PRODUCTOT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildProteccionDatosRelatedInstance4Productos(relatedInstanceJsonOid, remainingPath);
        }
        if (ProteccionDatosConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildProteccionDatosRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildProteccionDatosRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new ProteccionDatos(mapper.readValue(relatedInstanceJsonOid, ProteccionDatosOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildProteccionDatosRelatedInstance4SolicitudNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new SolicitudNovedad(mapper.readValue(relatedInstanceJsonOid, SolicitudNovedadOid.class)): SolicitudNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildProteccionDatosRelatedInstance4Productos(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Productos(mapper.readValue(relatedInstanceJsonOid, ProductosOid.class)): Productos.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildProteccionDatosRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ProteccionDatosOid oid = new ProteccionDatosOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ProteccionDatosConstants.ATTR_FIELD_PROTECCIONDATOSIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getProteccionDatosIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ProteccionDatosService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
