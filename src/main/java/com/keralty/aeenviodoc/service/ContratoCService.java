package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.ContratoCConstants;
import com.keralty.aeenviodoc.global.STDFunctions;
import com.keralty.aeenviodoc.global.UserFunctions;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.Asesor;
import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.EstadoContrato;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.FormaPago;
import com.keralty.aeenviodoc.persistence.GrupoAsociado;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.ObservacionesC;
import com.keralty.aeenviodoc.persistence.oid.AsesorOid;
import com.keralty.aeenviodoc.persistence.oid.BeneficiarioCOid;
import com.keralty.aeenviodoc.persistence.oid.ContratoCOid;
import com.keralty.aeenviodoc.persistence.oid.DireccionCOid;
import com.keralty.aeenviodoc.persistence.oid.EstadoContratoOid;
import com.keralty.aeenviodoc.persistence.oid.FamiliaCOid;
import com.keralty.aeenviodoc.persistence.oid.FormaPagoOid;
import com.keralty.aeenviodoc.persistence.oid.GrupoAsociadoOid;
import com.keralty.aeenviodoc.persistence.oid.MunicipioOid;
import com.keralty.aeenviodoc.persistence.oid.ObservacionesCOid;
import com.keralty.aeenviodoc.persistence.oid.PeriodicidadOid;
import com.keralty.aeenviodoc.persistence.oid.PersonaCOid;
import com.keralty.aeenviodoc.persistence.oid.PlanesOid;
import com.keralty.aeenviodoc.persistence.oid.ProductosOid;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.oid.SucursalOid;
import com.keralty.aeenviodoc.persistence.oid.TarifaOid;
import com.keralty.aeenviodoc.persistence.Periodicidad;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.Planes;
import com.keralty.aeenviodoc.persistence.Productos;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.Sucursal;
import com.keralty.aeenviodoc.persistence.Tarifa;
import com.keralty.aeenviodoc.repository.ContratoCJpaRepository;
import com.keralty.aeenviodoc.sqlutils.SQLSelect;
import com.keralty.aeenviodoc.sqlutils.SQLWhereComparison;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeenviodoc.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeenviodoc.utils.Utilities;
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
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class ContratoCService extends AbstractService {

    ContratoCJpaRepository repository;

    ContratoCService contratoCSrv;

    PersonaCService personaCSrv;

    PeriodicidadService periodicidadSrv;

    FormaPagoService formaPagoSrv;

    EstadoContratoService estadoContratoSrv;

    ProductosService productosSrv;

    PlanesService planesSrv;

    TarifaService tarifaSrv;

    GrupoAsociadoService grupoAsociadoSrv;

    MunicipioService municipioSrv;

    SucursalService sucursalSrv;

    SolicitudService solicitudSrv;

    ObservacionesCService observacionesCSrv;

    DireccionService direccionSrv;

    DireccionCService direccionCSrv;

    TipoDireccionService tipoDireccionSrv;

    DepartamentoService departamentoSrv;

    PaisService paisSrv;

    ContratanteService contratanteSrv;

    AesolicitudesService aesolicitudesSrv;

    FamiliaCService familiaCSrv;

    BeneficiarioCService beneficiarioCSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public ContratoCService(
            ContratoCService contratoCSrv,
            PersonaCService personaCSrv,
            PeriodicidadService periodicidadSrv,
            FormaPagoService formaPagoSrv,
            EstadoContratoService estadoContratoSrv,
            ProductosService productosSrv,
            PlanesService planesSrv,
            TarifaService tarifaSrv,
            GrupoAsociadoService grupoAsociadoSrv,
            MunicipioService municipioSrv,
            SucursalService sucursalSrv,
            SolicitudService solicitudSrv,
            ObservacionesCService observacionesCSrv,
            DireccionService direccionSrv,
            DireccionCService direccionCSrv,
            TipoDireccionService tipoDireccionSrv,
            DepartamentoService departamentoSrv,
            PaisService paisSrv,
            ContratanteService contratanteSrv,
            AesolicitudesService aesolicitudesSrv,
            FamiliaCService familiaCSrv,
            BeneficiarioCService beneficiarioCSrv,
            UserFunctions userFunctionsSrv,
            ContratoCJpaRepository repository) {
        this.contratoCSrv = contratoCSrv;
        this.personaCSrv = personaCSrv;
        this.periodicidadSrv = periodicidadSrv;
        this.formaPagoSrv = formaPagoSrv;
        this.estadoContratoSrv = estadoContratoSrv;
        this.productosSrv = productosSrv;
        this.planesSrv = planesSrv;
        this.tarifaSrv = tarifaSrv;
        this.grupoAsociadoSrv = grupoAsociadoSrv;
        this.municipioSrv = municipioSrv;
        this.sucursalSrv = sucursalSrv;
        this.solicitudSrv = solicitudSrv;
        this.observacionesCSrv = observacionesCSrv;
        this.direccionSrv = direccionSrv;
        this.direccionCSrv = direccionCSrv;
        this.tipoDireccionSrv = tipoDireccionSrv;
        this.departamentoSrv = departamentoSrv;
        this.paisSrv = paisSrv;
        this.contratanteSrv = contratanteSrv;
        this.aesolicitudesSrv = aesolicitudesSrv;
        this.familiaCSrv = familiaCSrv;
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ContratoCConstants.CLASS_NAME);
    }

    private List<ContratoC> solveContratoCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ContratoCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveContratoCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ContratoCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ContratoC
     *
     * @return Population of class ContratoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratoC> get() {
        List<ContratoC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ContratoC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ContratoC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratoC> get(QueryRequest queryRequest) {
        return solveContratoCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveContratoCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveContratoCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveContratoCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of ContratoC by ID
     *
     * @param contratoCID Identification field contratoCID
     *
     * @return Instance of ContratoC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratoC getById(Long contratoCID) {
        return getByOID(new ContratoCOid(contratoCID));
    }
    
    /*
     * Returns an instance of ContratoC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ContratoC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratoC getByOID(ContratoCOid oid) {
        Optional<ContratoC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ContratoC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ContratoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ContratoC getByOIDWithHV(ContratoCOid oid) {
        Optional<ContratoC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ContratoC> getInstanceByOid(ContratoCOid oid) {
        return oid != null && oid.getContratoCID() != null
               ? repository.findById(oid.getContratoCID())
               : Optional.empty();
    }

    /*
     * Returns an instance of ContratoC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ContratoC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratoC getByJsonOid(String jsonOid) {
        ContratoCOid oid = new ContratoCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of ContratoC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ContratoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratoC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveContratoCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of ContratoC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ContratoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratoC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of ContratoC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ContratoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getContratoCDynamic(ContratoCOid oid, String displaySetItems) {
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
    public String getSuppInfo(ContratoCOid oid, String dsName) {
        ContratoC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ContratoC instance, String dsName) {
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
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.crearTxn(request);
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
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.CrearRequest request) throws ModelException {
        request.setContratoCcrearpagrPersonaCContratanteInstance(personaCSrv.getByOIDWithHV(request.getContratoCcrearpagrPersonaCContratante()));
        request.setContratoCcrearpagrPeriodicidadCInstance(periodicidadSrv.getByOIDWithHV(request.getContratoCcrearpagrPeriodicidadC()));
        request.setContratoCcrearpagrFormaPagoCInstance(formaPagoSrv.getByOIDWithHV(request.getContratoCcrearpagrFormaPagoC()));
        request.setContratoCcrearpagrEstadoContratoCInstance(estadoContratoSrv.getByOIDWithHV(request.getContratoCcrearpagrEstadoContratoC()));
        request.setContratoCcrearpagrProductoCInstance(productosSrv.getByOIDWithHV(request.getContratoCcrearpagrProductoC()));
        request.setContratoCcrearpagrPlanCInstance(planesSrv.getByOIDWithHV(request.getContratoCcrearpagrPlanC()));
        request.setContratoCcrearpagrTarifaCInstance(tarifaSrv.getByOIDWithHV(request.getContratoCcrearpagrTarifaC()));
        request.setContratoCcrearpagrGrupoAsociadoCInstance(grupoAsociadoSrv.getByOIDWithHV(request.getContratoCcrearpagrGrupoAsociadoC()));
        request.setContratoCcrearpagrMunicipioCInstance(municipioSrv.getByOIDWithHV(request.getContratoCcrearpagrMunicipioC()));
        request.setContratoCcrearpagrSucursalCInstance(sucursalSrv.getByOIDWithHV(request.getContratoCcrearpagrSucursalC()));
        request.setContratoCcrearpagrAsesorCInstance(asesorSrv.getByOIDWithHV(request.getContratoCcrearpagrAsesorC()));
        return contratoCSrv.crearImplementation(request);
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
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad contratoCcrearpagrPeriodicidadC = request.getContratoCcrearpagrPeriodicidadCInstance();
        FormaPago contratoCcrearpagrFormaPagoC = request.getContratoCcrearpagrFormaPagoCInstance();
        EstadoContrato contratoCcrearpagrEstadoContratoC = request.getContratoCcrearpagrEstadoContratoCInstance();
        Productos contratoCcrearpagrProductoC = request.getContratoCcrearpagrProductoCInstance();
        Planes contratoCcrearpagrPlanC = request.getContratoCcrearpagrPlanCInstance();
        Tarifa contratoCcrearpagrTarifaC = request.getContratoCcrearpagrTarifaCInstance();
        GrupoAsociado contratoCcrearpagrGrupoAsociadoC = request.getContratoCcrearpagrGrupoAsociadoCInstance();
        Municipio contratoCcrearpagrMunicipioC = request.getContratoCcrearpagrMunicipioCInstance();
        Sucursal contratoCcrearpagrSucursalC = request.getContratoCcrearpagrSucursalCInstance();
        Asesor contratoCcrearpagrAsesorC = request.getContratoCcrearpagrAsesorCInstance();
        String contratoCcrearpatrNumSolicitud = request.getContratoCcrearpatrNumSolicitud();
        Date contratoCcrearpatrFechaSolicitud = request.getContratoCcrearpatrFechaSolicitud();
        Long contratoCcrearpatrTipoContrato = request.getContratoCcrearpatrTipoContrato();
        Date contratoCcrearpatrFechaIniVigencia = request.getContratoCcrearpatrFechaIniVigencia();
        String contratoCcrearpatrDireccion = request.getContratoCcrearpatrDireccion();
        String contratoCcrearpatrcorreoContratante = request.getContratoCcrearpatrcorreoContratante();
        String contratoCcrearpatrnumTelContratante = request.getContratoCcrearpatrnumTelContratante();
        String contratoCcrearpatrnumCelContratante = request.getContratoCcrearpatrnumCelContratante();
        String contratoCcrearpatrexentoIVA = request.getContratoCcrearpatrexentoIVA();
        Boolean contratoCcrearpatrContratoConFirma = request.getContratoCcrearpatrContratoConFirma();
        String contratoCcrearpatrNumContrato = request.getContratoCcrearpatrNumContrato();
        Date contratoCcrearpatrFechaFinVigencia = request.getContratoCcrearpatrFechaFinVigencia();
        Timestamp contratoCcrearpatrUltimaActualizacion = request.getContratoCcrearpatrUltimaActualizacion();
        String contratoCcrearpatrCodTarifa = request.getContratoCcrearpatrCodTarifa();

        request.checkArguments();
        ContratoC instance = new ContratoC();

        if (contratoCcrearpagrPeriodicidadC != null && !contratoCcrearpagrPeriodicidadC.isNull()) {
            instance.add2PeriodicidadC(contratoCcrearpagrPeriodicidadC);
        }

        if (contratoCcrearpagrFormaPagoC != null && !contratoCcrearpagrFormaPagoC.isNull()) {
            instance.add2FormaPagoC(contratoCcrearpagrFormaPagoC);
        }

        if (contratoCcrearpagrEstadoContratoC != null && !contratoCcrearpagrEstadoContratoC.isNull()) {
            instance.add2EstadoContratoC(contratoCcrearpagrEstadoContratoC);
        }

        instance.add2ProductoC(contratoCcrearpagrProductoC);

        instance.add2PlanC(contratoCcrearpagrPlanC);

        if (contratoCcrearpagrTarifaC != null && !contratoCcrearpagrTarifaC.isNull()) {
            instance.add2TarifaC(contratoCcrearpagrTarifaC);
        }

        if (contratoCcrearpagrGrupoAsociadoC != null && !contratoCcrearpagrGrupoAsociadoC.isNull()) {
            instance.add2GrupoAsociadoC(contratoCcrearpagrGrupoAsociadoC);
        }

        if (contratoCcrearpagrMunicipioC != null && !contratoCcrearpagrMunicipioC.isNull()) {
            instance.add2MunicipioC(contratoCcrearpagrMunicipioC);
        }

        if (contratoCcrearpagrSucursalC != null && !contratoCcrearpagrSucursalC.isNull()) {
            instance.add2SucursalC(contratoCcrearpagrSucursalC);
        }

        if (contratoCcrearpagrAsesorC != null && !contratoCcrearpagrAsesorC.isNull()) {
            instance.add2AsesorC(contratoCcrearpagrAsesorC);
        }

        instance.setContratoCID(Long.valueOf(0));
        instance.setContratoCvalorCuotaPeriodo(Utilities.convertToDouble(Long.valueOf(0)));
        instance.setContratoCdescuentoComercial(Utilities.convertToDouble(Long.valueOf(0)));
        instance.setContratoCIVA(Utilities.convertToDouble(Long.valueOf(0)));
        instance.setContratoCdescuentoFinanciero(Utilities.convertToDouble(Long.valueOf(0)));
        instance.setContratoCcontratoNotificado(Boolean.FALSE);
        instance.setContratoCNumSolicitud(contratoCcrearpatrNumSolicitud);
        instance.setContratoCFechaSolicitud(contratoCcrearpatrFechaSolicitud);
        instance.setContratoCTipoContrato(contratoCcrearpatrTipoContrato);
        instance.setContratoCFechaIniVigencia(contratoCcrearpatrFechaIniVigencia);
        instance.setContratoCDireccion(contratoCcrearpatrDireccion);
        instance.setContratoCcorreoContratante(contratoCcrearpatrcorreoContratante);
        instance.setContratoCnumTelContratante(contratoCcrearpatrnumTelContratante);
        instance.setContratoCnumCelContratante(contratoCcrearpatrnumCelContratante);
        instance.setContratoCexentoIVA(contratoCcrearpatrexentoIVA);
        instance.setContratoCContratoConFirma(contratoCcrearpatrContratoConFirma);
        instance.setContratoCNumContrato(contratoCcrearpatrNumContrato);
        instance.setContratoCFechaFinVigencia(contratoCcrearpatrFechaFinVigencia);
        instance.setContratoCUltimaActualizacion(contratoCcrearpatrUltimaActualizacion);
        instance.setContratoCCodTarifa(contratoCcrearpatrCodTarifa);

        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: CalcularLiquidacion

    /*
     * Entry point for service CalcularLiquidacion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse calcularLiquidacion(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.CalcularLiquidacionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.calcularLiquidacionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CalcularLiquidacion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse calcularLiquidacionTxn(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.CalcularLiquidacionRequest request) throws ModelException, SystemException {
        request.setContratoCCalcularLiquidacionpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCCalcularLiquidacionpthisContratoC()));
        return contratoCSrv.calcularLiquidacionImplementation(request);
    }

    /*
     * Implements service CalcularLiquidacion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse calcularLiquidacionImplementation(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.CalcularLiquidacionRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCCalcularLiquidacionpthisContratoC = request.getContratoCCalcularLiquidacionpthisContratoCInstance();

        request.checkArguments();
        ContratoC instance = contratoCCalcularLiquidacionpthisContratoC;
        instance.setContratoCvalorCuotaPeriodo(contratoCCalcularLiquidacionpthisContratoC.getContratoCdrvValorCuotaAfiliacion());
        instance.setContratoCdescuentoComercial(repository.assocOperator007(contratoCCalcularLiquidacionpthisContratoC));
        instance.setContratoCdescuentoFinanciero(repository.assocOperator008(contratoCCalcularLiquidacionpthisContratoC));


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsContratoCASolicitud

    /*
     * Entry point for service InsContratoCASolicitud
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insContratoCASolicitud(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.InsContratoCASolicitudRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.insContratoCASolicitudTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsContratoCASolicitud
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insContratoCASolicitudTxn(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.InsContratoCASolicitudRequest request) throws ModelException {
        request.setContratoCInsContratoCASolicitudpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCInsContratoCASolicitudpthisContratoC()));
        request.setContratoCInsContratoCASolicitudpevcSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getContratoCInsContratoCASolicitudpevcSolicitud()));
        return contratoCSrv.insContratoCASolicitudImplementation(request, true);
    }

    /*
     * Implements service InsContratoCASolicitud
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insContratoCASolicitudImplementation(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.InsContratoCASolicitudRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCInsContratoCASolicitudpthisContratoC = request.getContratoCInsContratoCASolicitudpthisContratoCInstance();
        Solicitud contratoCInsContratoCASolicitudpevcSolicitud = request.getContratoCInsContratoCASolicitudpevcSolicitudInstance();

        request.checkArguments();
        ContratoC instance = contratoCInsContratoCASolicitudpthisContratoC;

        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.solicitud.siu.InsContratoCASolicitudRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.solicitud.siu.InsContratoCASolicitudRequest();
            relatedRequest.setSolicitudInsContratoCASolicitudpthisSolicitudInstance(contratoCInsContratoCASolicitudpevcSolicitud);
            relatedRequest.setSolicitudInsContratoCASolicitudpevcContratoCInstance(contratoCInsContratoCASolicitudpthisContratoC);
            solicitudSrv.insContratoCASolicitudImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelContratoCASolicitud

    /*
     * Entry point for service DelContratoCASolicitud
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delContratoCASolicitud(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.DelContratoCASolicitudRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.delContratoCASolicitudTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelContratoCASolicitud
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delContratoCASolicitudTxn(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.DelContratoCASolicitudRequest request) throws ModelException {
        request.setContratoCDelContratoCASolicitudpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCDelContratoCASolicitudpthisContratoC()));
        request.setContratoCDelContratoCASolicitudpevcSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getContratoCDelContratoCASolicitudpevcSolicitud()));
        return contratoCSrv.delContratoCASolicitudImplementation(request, true);
    }

    /*
     * Implements service DelContratoCASolicitud
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delContratoCASolicitudImplementation(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.DelContratoCASolicitudRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCDelContratoCASolicitudpthisContratoC = request.getContratoCDelContratoCASolicitudpthisContratoCInstance();
        Solicitud contratoCDelContratoCASolicitudpevcSolicitud = request.getContratoCDelContratoCASolicitudpevcSolicitudInstance();

        request.checkArguments();
        ContratoC instance = contratoCDelContratoCASolicitudpthisContratoC;

        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.solicitud.siu.DelContratoCASolicitudRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.solicitud.siu.DelContratoCASolicitudRequest();
            relatedRequest.setSolicitudDelContratoCASolicitudpthisSolicitudInstance(contratoCDelContratoCASolicitudpevcSolicitud);
            relatedRequest.setSolicitudDelContratoCASolicitudpevcContratoCInstance(contratoCDelContratoCASolicitudpthisContratoC);
            solicitudSrv.delContratoCASolicitudImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: TCREAROBS100

    /*
     * Entry point for service TCREAROBS100
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREAROBS100(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROBS100Request request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.tCREAROBS100Txn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREAROBS100
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREAROBS100Txn(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROBS100Request request) throws ModelException {
        request.setContratoCTCREAROBS100pthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCTCREAROBS100pthisContratoC()));
        return contratoCSrv.tCREAROBS100Implementation(request);
    }

    /*
     * Implements service TCREAROBS100
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREAROBS100Implementation(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROBS100Request request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String contratoCTCREAROBS100pObsText = request.getContratoCTCREAROBS100pObsText();
        ContratoC contratoCTCREAROBS100pthisContratoC = request.getContratoCTCREAROBS100pthisContratoCInstance();

        request.checkArguments();
        ContratoC instance = contratoCTCREAROBS100pthisContratoC;

        /* Action 1: IF (length(pObsText) > 100) */
        boolean ifCondition1 = STDFunctions.lengthFun(contratoCTCREAROBS100pObsText) > Long.valueOf(100);
        if (ifCondition1) {
            com.keralty.aeenviodoc.viewmodel.observacionesc.siu.CrearRequest step10Request = new com.keralty.aeenviodoc.viewmodel.observacionesc.siu.CrearRequest();
            step10Request.setObservacionesCcrearpagrContratoCInstance(contratoCTCREAROBS100pthisContratoC);
            step10Request.setObservacionesCcrearpatrCodTipoObservacion("2");
            step10Request.setObservacionesCcrearpatrTipoObservacion("OBSERVACIONES MANUALES");
            step10Request.setObservacionesCcrearpatrObservacions(STDFunctions.leftSubstringFun(contratoCTCREAROBS100pObsText, Long.valueOf(100)));
            observacionesCSrv.crearImplementation(step10Request);
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROBS100Request step11Request = new com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROBS100Request();
            step11Request.setContratoCTCREAROBS100pthisContratoCInstance(contratoCTCREAROBS100pthisContratoC);
            step11Request.setContratoCTCREAROBS100pObsText(STDFunctions.substringFun(contratoCTCREAROBS100pObsText, Long.valueOf(100), STDFunctions.lengthFun(contratoCTCREAROBS100pObsText) - Long.valueOf(100)));
            contratoCSrv.tCREAROBS100Implementation(step11Request);
        } else {
            com.keralty.aeenviodoc.viewmodel.observacionesc.siu.CrearRequest step12Request = new com.keralty.aeenviodoc.viewmodel.observacionesc.siu.CrearRequest();
            step12Request.setObservacionesCcrearpagrContratoCInstance(contratoCTCREAROBS100pthisContratoC);
            step12Request.setObservacionesCcrearpatrCodTipoObservacion("2");
            step12Request.setObservacionesCcrearpatrTipoObservacion("OBSERVACIONES MANUALES");
            step12Request.setObservacionesCcrearpatrObservacions(contratoCTCREAROBS100pObsText);
            observacionesCSrv.crearImplementation(step12Request);
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TCREAROMODDIRDEAE

    /*
     * Entry point for service TCREAROMODDIRDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREAROMODDIRDEAE(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROMODDIRDEAERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.tCREAROMODDIRDEAETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREAROMODDIRDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREAROMODDIRDEAETxn(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROMODDIRDEAERequest request) throws ModelException, SystemException {
        request.setContratoCTCREAROMODDIRDEAEpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCTCREAROMODDIRDEAEpthisContratoC()));
        request.setContratoCTCREAROMODDIRDEAEpSolicitudAEInstance(solicitudSrv.getByOIDWithHV(request.getContratoCTCREAROMODDIRDEAEpSolicitudAE()));
        return contratoCSrv.tCREAROMODDIRDEAEImplementation(request);
    }

    /*
     * Implements service TCREAROMODDIRDEAE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREAROMODDIRDEAEImplementation(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROMODDIRDEAERequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Solicitud contratoCTCREAROMODDIRDEAEpSolicitudAE = request.getContratoCTCREAROMODDIRDEAEpSolicitudAEInstance();
        ContratoC contratoCTCREAROMODDIRDEAEpthisContratoC = request.getContratoCTCREAROMODDIRDEAEpthisContratoCInstance();

        request.checkArguments();
        ContratoC instance = contratoCTCREAROMODDIRDEAEpthisContratoC;

        /* Action 1: IF (EXIST (pSolicitudAE.Direccion) = true) */
        boolean ifCondition1 = repository.assocOperator020(contratoCTCREAROMODDIRDEAEpSolicitudAE).equals(true);
        if (ifCondition1) {
            for(DireccionC step1010Entity : repository.assocOperator018(contratoCTCREAROMODDIRDEAEpthisContratoC, contratoCTCREAROMODDIRDEAEpSolicitudAE)) {
                com.keralty.aeenviodoc.viewmodel.direccionc.siu.TMODIFICARRequest step10Request = new com.keralty.aeenviodoc.viewmodel.direccionc.siu.TMODIFICARRequest();
                step10Request.setDireccionCTMODIFICARpthisDireccionCInstance(step1010Entity);
                step10Request.setDireccionCTMODIFICARptpDireccionCompleta(contratoCTCREAROMODDIRDEAEpSolicitudAE.getDireccion().getDireccionDireccionCompleta());
                step10Request.setDireccionCTMODIFICARptpevcDepartamentoCInstance(contratoCTCREAROMODDIRDEAEpSolicitudAE.getDireccion().getDepartamento());
                step10Request.setDireccionCTMODIFICARptpevcMunicipioCInstance(contratoCTCREAROMODDIRDEAEpSolicitudAE.getDireccion().getMunicipio());
                step10Request.setDireccionCTMODIFICARptpevcPaisCInstance(contratoCTCREAROMODDIRDEAEpSolicitudAE.getDireccion().getPais());
                step10Request.setDireccionCTMODIFICARptpevcTipoDireccionCInstance(step1010Entity.getTipoDireccionC());
                step10Request.setDireccionCTMODIFICARptpLocalidad(step1010Entity.getDireccionCLocalidad());
                direccionCSrv.tMODIFICARImplementation(step10Request);
            }
            /* Action 11: IF (EXIST (DireccionesC) WHERE (DireccionesC.TipoDireccionC = pSolicitudAE.Direccion.TipoDireccion) = false) */
            boolean ifCondition11 = repository.assocOperator021(contratoCTCREAROMODDIRDEAEpthisContratoC, contratoCTCREAROMODDIRDEAEpSolicitudAE).equals(false);
            if (ifCondition11) {
                com.keralty.aeenviodoc.viewmodel.direccionc.siu.TCREARRequest step110Request = new com.keralty.aeenviodoc.viewmodel.direccionc.siu.TCREARRequest();
                step110Request.setDireccionCTCREARpagrDepartamentoCInstance(contratoCTCREAROMODDIRDEAEpSolicitudAE.getDireccion().getDepartamento());
                step110Request.setDireccionCTCREARpagrMunicipioCInstance(contratoCTCREAROMODDIRDEAEpSolicitudAE.getDireccion().getMunicipio());
                step110Request.setDireccionCTCREARpagrTipoDireccionCInstance(contratoCTCREAROMODDIRDEAEpSolicitudAE.getDireccion().getTipoDireccion());
                step110Request.setDireccionCTCREARpagrPaisCInstance(contratoCTCREAROMODDIRDEAEpSolicitudAE.getDireccion().getPais());
                step110Request.setDireccionCTCREARpagrPersonaCInstance(null);
                step110Request.setDireccionCTCREARpatrDireccionCompleta(contratoCTCREAROMODDIRDEAEpSolicitudAE.getDireccion().getDireccionDireccionCompleta());
                step110Request.setDireccionCTCREARpatrLocalidad(null);
                step110Request.setDireccionCTCREARpTelefonoFijo(contratoCTCREAROMODDIRDEAEpSolicitudAE.getContratante().getContratanteTelefonoFijo());
                step110Request.setDireccionCTCREARpTelefonoCelular(contratoCTCREAROMODDIRDEAEpSolicitudAE.getContratante().getContratanteTelefonoCelular());
                step110Request.setDireccionCTCREARpagrContratoCInstance(contratoCTCREAROMODDIRDEAEpthisContratoC);
                direccionCSrv.tCREARImplementation(step110Request);
            }
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TNEWDESDEAE

    /*
     * Entry point for service TNEWDESDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public com.keralty.aeenviodoc.viewmodel.contratoc.siu.TNEWDESDEAEResponse tNEWDESDEAE(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TNEWDESDEAERequest request) {
        com.keralty.aeenviodoc.viewmodel.contratoc.siu.TNEWDESDEAEResponse response = new com.keralty.aeenviodoc.viewmodel.contratoc.siu.TNEWDESDEAEResponse();
        try {
            response = contratoCSrv.tNEWDESDEAETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TNEWDESDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public com.keralty.aeenviodoc.viewmodel.contratoc.siu.TNEWDESDEAEResponse tNEWDESDEAETxn(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TNEWDESDEAERequest request) throws ModelException, SystemException {
        request.setContratoCTNEWDESDEAEptSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getContratoCTNEWDESDEAEptSolicitud()));
        return contratoCSrv.tNEWDESDEAEImplementation(request);
    }

    /*
     * Implements service TNEWDESDEAE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public com.keralty.aeenviodoc.viewmodel.contratoc.siu.TNEWDESDEAEResponse tNEWDESDEAEImplementation(
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TNEWDESDEAERequest request) throws ModelException, SystemException {
        com.keralty.aeenviodoc.viewmodel.contratoc.siu.TNEWDESDEAEResponse response = new com.keralty.aeenviodoc.viewmodel.contratoc.siu.TNEWDESDEAEResponse();
        Solicitud contratoCTNEWDESDEAEptSolicitud = request.getContratoCTNEWDESDEAEptSolicitudInstance();
        String contratoCTNEWDESDEAEptNumFamiliaCORE = request.getContratoCTNEWDESDEAEptNumFamiliaCORE();

        request.checkArguments();
        ContratoC instance;

        /* Action 1: IF (ptSolicitud.Aesolicitudes.Tiposolicitud = "E") */
        boolean ifCondition1 = (contratoCTNEWDESDEAEptSolicitud.getAesolicitudes().getAesolicitudesTiposolicitud()).equals("E");
        if (ifCondition1) {
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.CrearRequest step10Request = new com.keralty.aeenviodoc.viewmodel.contratoc.siu.CrearRequest();
            step10Request.setContratoCcrearpagrPersonaCContratanteInstance(null);
            step10Request.setContratoCcrearpagrPeriodicidadCInstance(contratoCTNEWDESDEAEptSolicitud.getPeriodicidad());
            step10Request.setContratoCcrearpagrFormaPagoCInstance(contratoCTNEWDESDEAEptSolicitud.getFormaPago());
            step10Request.setContratoCcrearpagrEstadoContratoCInstance(null);
            step10Request.setContratoCcrearpagrProductoCInstance(contratoCTNEWDESDEAEptSolicitud.getAesolicitudes().getProductoT());
            step10Request.setContratoCcrearpagrPlanCInstance(contratoCTNEWDESDEAEptSolicitud.getAesolicitudes().getPlanT());
            step10Request.setContratoCcrearpagrTarifaCInstance(null);
            step10Request.setContratoCcrearpagrGrupoAsociadoCInstance(contratoCTNEWDESDEAEptSolicitud.getGrupoAsociado());
            step10Request.setContratoCcrearpagrMunicipioCInstance(contratoCTNEWDESDEAEptSolicitud.getDireccion().getMunicipio());
            step10Request.setContratoCcrearpagrSucursalCInstance(contratoCTNEWDESDEAEptSolicitud.getAgente().getSucursal());
            step10Request.setContratoCcrearpagrAsesorCInstance(contratoCTNEWDESDEAEptSolicitud.getAgente().getAsesorFacet());
            step10Request.setContratoCcrearpatrNumSolicitud(STDFunctions.intToStrFun(contratoCTNEWDESDEAEptSolicitud.getSolicitudNumRadicado()));
            step10Request.setContratoCcrearpatrFechaSolicitud(contratoCTNEWDESDEAEptSolicitud.getAesolicitudes().getAesolicitudesFechaasignacion());
            step10Request.setContratoCcrearpatrTipoContrato(contratoCTNEWDESDEAEptSolicitud.getSolicitudtipoContrato());
            step10Request.setContratoCcrearpatrFechaIniVigencia(contratoCTNEWDESDEAEptSolicitud.getSolicitudFInicioContrato());
            step10Request.setContratoCcrearpatrDireccion(contratoCTNEWDESDEAEptSolicitud.getDireccion().getDireccionDireccionCompleta());
            step10Request.setContratoCcrearpatrcorreoContratante(contratoCTNEWDESDEAEptSolicitud.getContratante().getContratanteCorreoElectronico());
            step10Request.setContratoCcrearpatrnumTelContratante(contratoCTNEWDESDEAEptSolicitud.getContratante().getContratanteTelefonoFijo());
            step10Request.setContratoCcrearpatrnumCelContratante(contratoCTNEWDESDEAEptSolicitud.getContratante().getContratanteTelefonoCelular());
            step10Request.setContratoCcrearpatrexentoIVA(contratoCTNEWDESDEAEptSolicitud.getSolicitudexentoIVA());
            step10Request.setContratoCcrearpatrContratoConFirma(contratoCTNEWDESDEAEptSolicitud.getSolicitudcontratoConFirma());
            step10Request.setContratoCcrearpatrNumContrato(null);
            step10Request.setContratoCcrearpatrFechaFinVigencia(null);
            step10Request.setContratoCcrearpatrUltimaActualizacion(STDFunctions.systemDateTimeFun());
            step10Request.setContratoCcrearpatrCodTarifa(userFunctionsSrv.fuGetCodTarifaProdPlanAnyoFun(contratoCTNEWDESDEAEptSolicitud.getAesolicitudes().getProductoT(), contratoCTNEWDESDEAEptSolicitud.getAesolicitudes().getPlanT(), STDFunctions.getYearFun(contratoCTNEWDESDEAEptSolicitud.getSolicitudFInicioContrato()), contratoCTNEWDESDEAEptSolicitud.getSolicitudtipoContrato()));
            instance = (ContratoC) contratoCSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.CrearRequest step11Request = new com.keralty.aeenviodoc.viewmodel.contratoc.siu.CrearRequest();
            step11Request.setContratoCcrearpagrPersonaCContratanteInstance(null);
            step11Request.setContratoCcrearpagrPeriodicidadCInstance(null);
            step11Request.setContratoCcrearpagrFormaPagoCInstance(null);
            step11Request.setContratoCcrearpagrEstadoContratoCInstance(null);
            step11Request.setContratoCcrearpagrProductoCInstance(contratoCTNEWDESDEAEptSolicitud.getAesolicitudes().getProductoT());
            step11Request.setContratoCcrearpagrPlanCInstance(contratoCTNEWDESDEAEptSolicitud.getAesolicitudes().getPlanT());
            step11Request.setContratoCcrearpagrTarifaCInstance(null);
            step11Request.setContratoCcrearpagrGrupoAsociadoCInstance(null);
            step11Request.setContratoCcrearpagrMunicipioCInstance(null);
            step11Request.setContratoCcrearpagrSucursalCInstance(null);
            step11Request.setContratoCcrearpagrAsesorCInstance(null);
            step11Request.setContratoCcrearpatrNumSolicitud(contratoCTNEWDESDEAEptSolicitud.getAesolicitudes().getAesolicitudesNumerosolicitud());
            step11Request.setContratoCcrearpatrFechaSolicitud(null);
            step11Request.setContratoCcrearpatrTipoContrato(null);
            step11Request.setContratoCcrearpatrFechaIniVigencia(null);
            step11Request.setContratoCcrearpatrDireccion(null);
            step11Request.setContratoCcrearpatrcorreoContratante(null);
            step11Request.setContratoCcrearpatrnumTelContratante(null);
            step11Request.setContratoCcrearpatrnumCelContratante(null);
            step11Request.setContratoCcrearpatrexentoIVA(null);
            step11Request.setContratoCcrearpatrContratoConFirma(null);
            step11Request.setContratoCcrearpatrNumContrato(null);
            step11Request.setContratoCcrearpatrFechaFinVigencia(null);
            step11Request.setContratoCcrearpatrUltimaActualizacion(STDFunctions.systemDateTimeFun());
            step11Request.setContratoCcrearpatrCodTarifa(null);
            instance = (ContratoC) contratoCSrv.crearImplementation(step11Request).getInstance();
        }

        com.keralty.aeenviodoc.viewmodel.contratoc.siu.InsContratoCASolicitudRequest step2Request = new com.keralty.aeenviodoc.viewmodel.contratoc.siu.InsContratoCASolicitudRequest();
        step2Request.setContratoCInsContratoCASolicitudpthisContratoCInstance(instance);
        step2Request.setContratoCInsContratoCASolicitudpevcSolicitudInstance(contratoCTNEWDESDEAEptSolicitud);
        contratoCSrv.insContratoCASolicitudImplementation(step2Request, true);

        /* Action 3: IF (ptSolicitud.Aesolicitudes.Tiposolicitud = "E") */
        boolean ifCondition3 = (contratoCTNEWDESDEAEptSolicitud.getAesolicitudes().getAesolicitudesTiposolicitud()).equals("E");
        if (ifCondition3) {
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROMODDIRDEAERequest step30Request = new com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROMODDIRDEAERequest();
            step30Request.setContratoCTCREAROMODDIRDEAEpthisContratoCInstance(instance);
            step30Request.setContratoCTCREAROMODDIRDEAEpSolicitudAEInstance(contratoCTNEWDESDEAEptSolicitud);
            contratoCSrv.tCREAROMODDIRDEAEImplementation(step30Request);
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWCONTRADESDEAERequest step31Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWCONTRADESDEAERequest();
            step31Request.setPersonaCTNEWCONTRADESDEAEptSolicitudInstance(contratoCTNEWDESDEAEptSolicitud);
            step31Request.setPersonaCTNEWCONTRADESDEAEptContratoInstance(instance);
            personaCSrv.tNEWCONTRADESDEAEImplementation(step31Request);
            /* Action 32: IF (ptSolicitud.observacions <> NULL) */
            boolean ifCondition32 = contratoCTNEWDESDEAEptSolicitud.getSolicitudobservacions() != null;
            if (ifCondition32) {
                com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROBS100Request step320Request = new com.keralty.aeenviodoc.viewmodel.contratoc.siu.TCREAROBS100Request();
                step320Request.setContratoCTCREAROBS100pthisContratoCInstance(instance);
                step320Request.setContratoCTCREAROBS100pObsText(contratoCTNEWDESDEAEptSolicitud.getSolicitudobservacions());
                contratoCSrv.tCREAROBS100Implementation(step320Request);
            }
            com.keralty.aeenviodoc.viewmodel.familiac.siu.TNEWDESDEAERequest step33Request = new com.keralty.aeenviodoc.viewmodel.familiac.siu.TNEWDESDEAERequest();
            step33Request.setFamiliaCTNEWDESDEAEptContratoCInstance(instance);
            step33Request.setFamiliaCTNEWDESDEAEptSolicitudInstance(contratoCTNEWDESDEAEptSolicitud);
            step33Request.setFamiliaCTNEWDESDEAEptNumFamiliaCORE(contratoCTNEWDESDEAEptNumFamiliaCORE);
            familiaCSrv.tNEWDESDEAEImplementation(step33Request);
            com.keralty.aeenviodoc.viewmodel.contratoc.siu.CalcularLiquidacionRequest step34Request = new com.keralty.aeenviodoc.viewmodel.contratoc.siu.CalcularLiquidacionRequest();
            step34Request.setContratoCCalcularLiquidacionpthisContratoCInstance(instance);
            contratoCSrv.calcularLiquidacionImplementation(step34Request);
        } else {
            com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearRequest step35Request = new com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearRequest();
            step35Request.setFamiliaCcrearpagrPersonaCTitularInstance(null);
            step35Request.setFamiliaCcrearpagrContratoCInstance(instance);
            step35Request.setFamiliaCcrearpagrMunicipioCInstance(null);
            step35Request.setFamiliaCcrearpatrTitularConBeneficios(Boolean.FALSE);
            step35Request.setFamiliaCcrearpatrdireccionCorrespondencia(null);
            step35Request.setFamiliaCcrearpatrEstadoTitularFamilia(null);
            step35Request.setFamiliaCcrearpatrFechaFinVigencia(null);
            step35Request.setFamiliaCcrearpatrNumFamilia(null);
            familiaCSrv.crearImplementation(step35Request);
            
        }

        response.setInstance(getByOID(instance.getOid()));

        // Value expressions for outgoing argument 'contratoCTNEWDESDEAEpsContratoC'
        // DEFAULT  : THIS
        response.setContratoCTNEWDESDEAEpsContratoCInstance(instance);


        // Value expressions for outgoing argument 'contratoCTNEWDESDEAEpsNumContrato'
        // CONDITION: NumContrato <> NULL
        // EFFECT   : NumContrato
        if (instance.getContratoCNumContrato() != null) {
            response.setContratoCTNEWDESDEAEpsNumContrato(instance.getContratoCNumContrato());
        }
         else {
            // DEFAULT  : "0"
            response.setContratoCTNEWDESDEAEpsNumContrato("0");
        }


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
        select.getOrderByPart().addItem(Constants.normalizePath(ContratoCConstants.CLASS_NAME, ContratoCConstants.ATTR_FIELD_CONTRATOCID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildContratoCRelatedInstance(relatedInstanceJsonOid);
        }
        if (ContratoCConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_PERSONACCONTRATANTE.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_DIRECCIONESC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4DireccionC(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_OBSERVACIONESC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4ObservacionesC(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_BENEFICIARIOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4BeneficiarioC(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_FAMILIASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4FamiliaC(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_PERIODICIDADC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Periodicidad(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_FORMAPAGOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4FormaPago(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_ESTADOCONTRATOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4EstadoContrato(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_PRODUCTOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Productos(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_PLANC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Planes(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_TARIFAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Tarifa(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_GRUPOASOCIADOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4GrupoAsociado(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_MUNICIPIOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_SUCURSALC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Sucursal(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_ASESORC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Asesor(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildContratoCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4DireccionC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DireccionC(mapper.readValue(relatedInstanceJsonOid, DireccionCOid.class)): DireccionC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4ObservacionesC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ObservacionesC(mapper.readValue(relatedInstanceJsonOid, ObservacionesCOid.class)): ObservacionesC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4BeneficiarioC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new BeneficiarioC(mapper.readValue(relatedInstanceJsonOid, BeneficiarioCOid.class)): BeneficiarioC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4FamiliaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FamiliaC(mapper.readValue(relatedInstanceJsonOid, FamiliaCOid.class)): FamiliaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Periodicidad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Periodicidad(mapper.readValue(relatedInstanceJsonOid, PeriodicidadOid.class)): Periodicidad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4FormaPago(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FormaPago(mapper.readValue(relatedInstanceJsonOid, FormaPagoOid.class)): FormaPago.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4EstadoContrato(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoContrato(mapper.readValue(relatedInstanceJsonOid, EstadoContratoOid.class)): EstadoContrato.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Productos(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Productos(mapper.readValue(relatedInstanceJsonOid, ProductosOid.class)): Productos.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Planes(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Planes(mapper.readValue(relatedInstanceJsonOid, PlanesOid.class)): Planes.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Tarifa(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Tarifa(mapper.readValue(relatedInstanceJsonOid, TarifaOid.class)): Tarifa.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4GrupoAsociado(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new GrupoAsociado(mapper.readValue(relatedInstanceJsonOid, GrupoAsociadoOid.class)): GrupoAsociado.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Sucursal(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Sucursal(mapper.readValue(relatedInstanceJsonOid, SucursalOid.class)): Sucursal.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Asesor(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Asesor(mapper.readValue(relatedInstanceJsonOid, AsesorOid.class)): Asesor.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ContratoCOid oid = new ContratoCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ContratoCConstants.ATTR_FIELD_CONTRATOCID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getContratoCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ContratoCService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
