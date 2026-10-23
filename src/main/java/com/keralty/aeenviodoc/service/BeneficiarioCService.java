package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.global.BeneficiarioCConstants;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.ContratoCConstants;
import com.keralty.aeenviodoc.global.STDFunctions;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.AnexoC;
import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.Companyia;
import com.keralty.aeenviodoc.persistence.ConceptoC;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Eps;
import com.keralty.aeenviodoc.persistence.EstadoBeneficiario;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.oid.AnexoCOid;
import com.keralty.aeenviodoc.persistence.oid.BeneficiarioCOid;
import com.keralty.aeenviodoc.persistence.oid.CompanyiaOid;
import com.keralty.aeenviodoc.persistence.oid.ConceptoCOid;
import com.keralty.aeenviodoc.persistence.oid.ContratoCOid;
import com.keralty.aeenviodoc.persistence.oid.EpsOid;
import com.keralty.aeenviodoc.persistence.oid.EstadoBeneficiarioOid;
import com.keralty.aeenviodoc.persistence.oid.FamiliaCOid;
import com.keralty.aeenviodoc.persistence.oid.ParentescoOid;
import com.keralty.aeenviodoc.persistence.oid.PersonaCOid;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.oid.UsuarioOid;
import com.keralty.aeenviodoc.persistence.Parentesco;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.Usuario;
import com.keralty.aeenviodoc.repository.BeneficiarioCJpaRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class BeneficiarioCService extends AbstractService {

    BeneficiarioCJpaRepository repository;

    BeneficiarioCService beneficiarioCSrv;

    EpsService epsSrv;

    CompanyiaService companyiaSrv;

    ParentescoService parentescoSrv;

    EstadoBeneficiarioService estadoBeneficiarioSrv;

    ContratoCService contratoCSrv;

    FamiliaCService familiaCSrv;

    PersonaCService personaCSrv;

    UsuarioService usuarioSrv;

    SolicitudService solicitudSrv;

    PersonaService personaSrv;

    AnexoMatService anexoMatSrv;

    AnexoCService anexoCSrv;

    PeriodicidadService periodicidadSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    InstitucionMatNeoService institucionMatNeoSrv;

    TarifaService tarifaSrv;

    ProductosService productosSrv;

    AnexoNeoService anexoNeoSrv;

    ConceptoCService conceptoCSrv;

    @Inject
    public BeneficiarioCService(
            BeneficiarioCService beneficiarioCSrv,
            EpsService epsSrv,
            CompanyiaService companyiaSrv,
            ParentescoService parentescoSrv,
            EstadoBeneficiarioService estadoBeneficiarioSrv,
            ContratoCService contratoCSrv,
            FamiliaCService familiaCSrv,
            PersonaCService personaCSrv,
            UsuarioService usuarioSrv,
            SolicitudService solicitudSrv,
            PersonaService personaSrv,
            AnexoMatService anexoMatSrv,
            AnexoCService anexoCSrv,
            PeriodicidadService periodicidadSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            InstitucionMatNeoService institucionMatNeoSrv,
            TarifaService tarifaSrv,
            ProductosService productosSrv,
            AnexoNeoService anexoNeoSrv,
            ConceptoCService conceptoCSrv,
            BeneficiarioCJpaRepository repository) {
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.epsSrv = epsSrv;
        this.companyiaSrv = companyiaSrv;
        this.parentescoSrv = parentescoSrv;
        this.estadoBeneficiarioSrv = estadoBeneficiarioSrv;
        this.contratoCSrv = contratoCSrv;
        this.familiaCSrv = familiaCSrv;
        this.personaCSrv = personaCSrv;
        this.usuarioSrv = usuarioSrv;
        this.solicitudSrv = solicitudSrv;
        this.personaSrv = personaSrv;
        this.anexoMatSrv = anexoMatSrv;
        this.anexoCSrv = anexoCSrv;
        this.periodicidadSrv = periodicidadSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.institucionMatNeoSrv = institucionMatNeoSrv;
        this.tarifaSrv = tarifaSrv;
        this.productosSrv = productosSrv;
        this.anexoNeoSrv = anexoNeoSrv;
        this.conceptoCSrv = conceptoCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, BeneficiarioCConstants.CLASS_NAME);
    }

    private List<BeneficiarioC> solveBeneficiarioCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, BeneficiarioCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveBeneficiarioCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, BeneficiarioCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class BeneficiarioC
     *
     * @return Population of class BeneficiarioC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BeneficiarioC> get() {
        List<BeneficiarioC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class BeneficiarioC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class BeneficiarioC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BeneficiarioC> get(QueryRequest queryRequest) {
        return solveBeneficiarioCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveBeneficiarioCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveBeneficiarioCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveBeneficiarioCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of BeneficiarioC by ID
     *
     * @param contratoCContratoCID Identification field contratoCContratoCID
     *
     * @param beneficiarioCID Identification field beneficiarioCID
     *
     * @return Instance of BeneficiarioC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public BeneficiarioC getById(Long contratoCContratoCID, Long beneficiarioCID) {
        return getByOID(new BeneficiarioCOid(contratoCContratoCID, beneficiarioCID));
    }
    
    /*
     * Returns an instance of BeneficiarioC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of BeneficiarioC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public BeneficiarioC getByOID(BeneficiarioCOid oid) {
        Optional<BeneficiarioC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of BeneficiarioC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of BeneficiarioC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public BeneficiarioC getByOIDWithHV(BeneficiarioCOid oid) {
        Optional<BeneficiarioC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<BeneficiarioC> getInstanceByOid(BeneficiarioCOid oid) {
        return oid != null && oid.getContratoCContratoCID() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of BeneficiarioC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of BeneficiarioC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public BeneficiarioC getByJsonOid(String jsonOid) {
        BeneficiarioCOid oid = new BeneficiarioCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of BeneficiarioC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of BeneficiarioC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BeneficiarioC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveBeneficiarioCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of BeneficiarioC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of BeneficiarioC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BeneficiarioC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of BeneficiarioC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of BeneficiarioC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getBeneficiarioCDynamic(BeneficiarioCOid oid, String displaySetItems) {
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
    public String getSuppInfo(BeneficiarioCOid oid, String dsName) {
        BeneficiarioC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(BeneficiarioC instance, String dsName) {
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
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.crearTxn(request);
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
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.CrearRequest request) throws ModelException {
        request.setBeneficiarioCcrearpagrEPSCInstance(epsSrv.getByOIDWithHV(request.getBeneficiarioCcrearpagrEPSC()));
        request.setBeneficiarioCcrearpagrCompanyiaMPCInstance(companyiaSrv.getByOIDWithHV(request.getBeneficiarioCcrearpagrCompanyiaMPC()));
        request.setBeneficiarioCcrearpagrParentescoCInstance(parentescoSrv.getByOIDWithHV(request.getBeneficiarioCcrearpagrParentescoC()));
        request.setBeneficiarioCcrearpagrEstadoBeneficiarioCInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getBeneficiarioCcrearpagrEstadoBeneficiarioC()));
        request.setBeneficiarioCcrearpagrContratoCInstance(contratoCSrv.getByOIDWithHV(request.getBeneficiarioCcrearpagrContratoC()));
        request.setBeneficiarioCcrearpagrFamiliasCInstance(familiaCSrv.getByOIDWithHV(request.getBeneficiarioCcrearpagrFamiliasC()));
        request.setBeneficiarioCcrearpagrPersonaCInstance(personaCSrv.getByOIDWithHV(request.getBeneficiarioCcrearpagrPersonaC()));
        return beneficiarioCSrv.crearImplementation(request);
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
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Eps beneficiarioCcrearpagrEPSC = request.getBeneficiarioCcrearpagrEPSCInstance();
        Companyia beneficiarioCcrearpagrCompanyiaMPC = request.getBeneficiarioCcrearpagrCompanyiaMPCInstance();
        Parentesco beneficiarioCcrearpagrParentescoC = request.getBeneficiarioCcrearpagrParentescoCInstance();
        EstadoBeneficiario beneficiarioCcrearpagrEstadoBeneficiarioC = request.getBeneficiarioCcrearpagrEstadoBeneficiarioCInstance();
        ContratoC beneficiarioCcrearpagrContratoC = request.getBeneficiarioCcrearpagrContratoCInstance();
        FamiliaC beneficiarioCcrearpagrFamiliasC = request.getBeneficiarioCcrearpagrFamiliasCInstance();
        String beneficiarioCcrearpatrCodigoTipoUsuario = request.getBeneficiarioCcrearpatrCodigoTipoUsuario();
        Boolean beneficiarioCcrearpatrIndicadorBBGestacion = request.getBeneficiarioCcrearpatrIndicadorBBGestacion();
        String beneficiarioCcrearpatrCodUsuarioBancoRep = request.getBeneficiarioCcrearpatrCodUsuarioBancoRep();
        Date beneficiarioCcrearpatrFechaAntiguedad = request.getBeneficiarioCcrearpatrFechaAntiguedad();
        Date beneficiarioCcrearpatrFechaAntiguedadRealOSI = request.getBeneficiarioCcrearpatrFechaAntiguedadRealOSI();
        Boolean beneficiarioCcrearpatrRequiereExamenMedico = request.getBeneficiarioCcrearpatrRequiereExamenMedico();
        Long beneficiarioCcrearpatrEstadoMedico = request.getBeneficiarioCcrearpatrEstadoMedico();
        Double beneficiarioCcrearpatrValorCuotaInscripcion = request.getBeneficiarioCcrearpatrValorCuotaInscripcion();
        Boolean beneficiarioCcrearpatrTienePreexistencias = request.getBeneficiarioCcrearpatrTienePreexistencias();
        Date beneficiarioCcrearpatrFechaInicioVigencia = request.getBeneficiarioCcrearpatrFechaInicioVigencia();
        Date beneficiarioCcrearpatrFechaFinVigencia = request.getBeneficiarioCcrearpatrFechaFinVigencia();
        Long beneficiarioCcrearpatrNumUsuario = request.getBeneficiarioCcrearpatrNumUsuario();

        request.checkArguments();
        BeneficiarioC instance = new BeneficiarioC();
        if (beneficiarioCcrearpagrEPSC != null && !beneficiarioCcrearpagrEPSC.isNull()) {
            instance.add2EPSC(beneficiarioCcrearpagrEPSC);
        }

        if (beneficiarioCcrearpagrCompanyiaMPC != null && !beneficiarioCcrearpagrCompanyiaMPC.isNull()) {
            instance.add2CompanyiaMPC(beneficiarioCcrearpagrCompanyiaMPC);
        }

        instance.add2ParentescoC(beneficiarioCcrearpagrParentescoC);

        if (beneficiarioCcrearpagrEstadoBeneficiarioC != null && !beneficiarioCcrearpagrEstadoBeneficiarioC.isNull()) {
            instance.add2EstadoBeneficiarioC(beneficiarioCcrearpagrEstadoBeneficiarioC);
        }

        instance.add2ContratoC(beneficiarioCcrearpagrContratoC);

        instance.add2FamiliasC(beneficiarioCcrearpagrFamiliasC);


        instance.setBeneficiarioCID(Long.valueOf(0));
        instance.setBeneficiarioCID(repository.nextBeneficiarioCID(instance.getContratoCContratoCID()));
        instance.setBeneficiarioCCodigoTipoUsuario(beneficiarioCcrearpatrCodigoTipoUsuario);
        instance.setBeneficiarioCIndicadorBBGestacion(beneficiarioCcrearpatrIndicadorBBGestacion);
        instance.setBeneficiarioCCodUsuarioBancoRep(beneficiarioCcrearpatrCodUsuarioBancoRep);
        instance.setBeneficiarioCFechaAntiguedad(beneficiarioCcrearpatrFechaAntiguedad);
        instance.setBeneficiarioCFechaAntiguedadRealOSI(beneficiarioCcrearpatrFechaAntiguedadRealOSI);
        instance.setBeneficiarioCRequiereExamenMedico(beneficiarioCcrearpatrRequiereExamenMedico);
        instance.setBeneficiarioCEstadoMedico(beneficiarioCcrearpatrEstadoMedico);
        instance.setBeneficiarioCValorCuotaInscripcion(beneficiarioCcrearpatrValorCuotaInscripcion);
        instance.setBeneficiarioCTienePreexistencias(beneficiarioCcrearpatrTienePreexistencias);
        instance.setBeneficiarioCFechaInicioVigencia(beneficiarioCcrearpatrFechaInicioVigencia);
        instance.setBeneficiarioCFechaFinVigencia(beneficiarioCcrearpatrFechaFinVigencia);
        instance.setBeneficiarioCNumUsuario(beneficiarioCcrearpatrNumUsuario);

        instance.setOid(instance.getOid());

        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsEstadoBeneficiarioC

    /*
     * Entry point for service InsEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoBeneficiarioC(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsEstadoBeneficiarioCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.insEstadoBeneficiarioCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoBeneficiarioCTxn(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsEstadoBeneficiarioCRequest request) throws ModelException {
        request.setBeneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getBeneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioC()));
        request.setBeneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getBeneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiario()));
        return beneficiarioCSrv.insEstadoBeneficiarioCImplementation(request, true);
    }

    /*
     * Implements service InsEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoBeneficiarioCImplementation(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsEstadoBeneficiarioCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        BeneficiarioC beneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioC = request.getBeneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioCInstance();
        EstadoBeneficiario beneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiario = request.getBeneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiarioInstance();

        request.checkArguments();
        BeneficiarioC instance = beneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioC;

        instance.add2EstadoBeneficiarioC(beneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiario);
        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCRequest();
            relatedRequest.setEstadoBeneficiarioInsEstadoBeneficiarioCpthisEstadoBeneficiarioInstance(beneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiario);
            relatedRequest.setEstadoBeneficiarioInsEstadoBeneficiarioCpevcBeneficiarioCInstance(beneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioC);
            estadoBeneficiarioSrv.insEstadoBeneficiarioCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsBeneficiariosC

    /*
     * Entry point for service InsBeneficiariosC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insBeneficiariosC(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsBeneficiariosCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.insBeneficiariosCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsBeneficiariosC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insBeneficiariosCTxn(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsBeneficiariosCRequest request) throws ModelException {
        request.setBeneficiarioCInsBeneficiariosCpthisBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getBeneficiarioCInsBeneficiariosCpthisBeneficiarioC()));
        request.setBeneficiarioCInsBeneficiariosCpevcPersonaCInstance(personaCSrv.getByOIDWithHV(request.getBeneficiarioCInsBeneficiariosCpevcPersonaC()));
        return beneficiarioCSrv.insBeneficiariosCImplementation(request, true);
    }

    /*
     * Implements service InsBeneficiariosC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insBeneficiariosCImplementation(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsBeneficiariosCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        BeneficiarioC beneficiarioCInsBeneficiariosCpthisBeneficiarioC = request.getBeneficiarioCInsBeneficiariosCpthisBeneficiarioCInstance();
        PersonaC beneficiarioCInsBeneficiariosCpevcPersonaC = request.getBeneficiarioCInsBeneficiariosCpevcPersonaCInstance();

        request.checkArguments();
        BeneficiarioC instance = beneficiarioCInsBeneficiariosCpthisBeneficiarioC;

        instance.add2PersonaC(beneficiarioCInsBeneficiariosCpevcPersonaC);
        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.personac.siu.InsBeneficiariosCRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.personac.siu.InsBeneficiariosCRequest();
            relatedRequest.setPersonaCInsBeneficiariosCpthisPersonaCInstance(beneficiarioCInsBeneficiariosCpevcPersonaC);
            relatedRequest.setPersonaCInsBeneficiariosCpevcBeneficiarioCInstance(beneficiarioCInsBeneficiariosCpthisBeneficiarioC);
            personaCSrv.insBeneficiariosCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

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
    public ServiceResponse tNEWDESDEAE(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.TNEWDESDEAERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.tNEWDESDEAETxn(request);
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
    public ServiceResponse tNEWDESDEAETxn(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.TNEWDESDEAERequest request) throws ModelException, SystemException {
        request.setBeneficiarioCTNEWDESDEAEptContratoInstance(contratoCSrv.getByOIDWithHV(request.getBeneficiarioCTNEWDESDEAEptContrato()));
        request.setBeneficiarioCTNEWDESDEAEptFamiliaInstance(familiaCSrv.getByOIDWithHV(request.getBeneficiarioCTNEWDESDEAEptFamilia()));
        request.setBeneficiarioCTNEWDESDEAEptUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getBeneficiarioCTNEWDESDEAEptUsuario()));
        request.setBeneficiarioCTNEWDESDEAEptSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getBeneficiarioCTNEWDESDEAEptSolicitud()));
        return beneficiarioCSrv.tNEWDESDEAEImplementation(request);
    }

    /*
     * Implements service TNEWDESDEAE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tNEWDESDEAEImplementation(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.TNEWDESDEAERequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Usuario beneficiarioCTNEWDESDEAEptUsuario = request.getBeneficiarioCTNEWDESDEAEptUsuarioInstance();
        ContratoC beneficiarioCTNEWDESDEAEptContrato = request.getBeneficiarioCTNEWDESDEAEptContratoInstance();
        FamiliaC beneficiarioCTNEWDESDEAEptFamilia = request.getBeneficiarioCTNEWDESDEAEptFamiliaInstance();
        Solicitud beneficiarioCTNEWDESDEAEptSolicitud = request.getBeneficiarioCTNEWDESDEAEptSolicitudInstance();

        request.checkArguments();
        BeneficiarioC instance;

        com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.CrearRequest step1Request = new com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.CrearRequest();
        step1Request.setBeneficiarioCcrearpagrEPSCInstance(beneficiarioCTNEWDESDEAEptUsuario.getEps());
        step1Request.setBeneficiarioCcrearpagrCompanyiaMPCInstance(beneficiarioCTNEWDESDEAEptUsuario.getCompanyia());
        step1Request.setBeneficiarioCcrearpagrParentescoCInstance(beneficiarioCTNEWDESDEAEptUsuario.getParentesco());
        step1Request.setBeneficiarioCcrearpagrEstadoBeneficiarioCInstance(beneficiarioCTNEWDESDEAEptUsuario.getEstadoBeneficiario());
        step1Request.setBeneficiarioCcrearpagrContratoCInstance(beneficiarioCTNEWDESDEAEptContrato);
        step1Request.setBeneficiarioCcrearpagrFamiliasCInstance(beneficiarioCTNEWDESDEAEptFamilia);
        step1Request.setBeneficiarioCcrearpagrPersonaCInstance(null);
        step1Request.setBeneficiarioCcrearpatrCodigoTipoUsuario(STDFunctions.intToStrFun(beneficiarioCTNEWDESDEAEptUsuario.getUsuarioCodigoTipoUsuario()));
        step1Request.setBeneficiarioCcrearpatrIndicadorBBGestacion(beneficiarioCTNEWDESDEAEptUsuario.getUsuarioBebeGestante());
        step1Request.setBeneficiarioCcrearpatrCodUsuarioBancoRep(beneficiarioCTNEWDESDEAEptUsuario.getUsuariocodigoBancoRep());
        step1Request.setBeneficiarioCcrearpatrFechaAntiguedad(beneficiarioCTNEWDESDEAEptUsuario.getUsuarioFechaAntiguedadBeneficiario());
        step1Request.setBeneficiarioCcrearpatrFechaAntiguedadRealOSI(null);
        step1Request.setBeneficiarioCcrearpatrRequiereExamenMedico(null);
        step1Request.setBeneficiarioCcrearpatrEstadoMedico(null);
        step1Request.setBeneficiarioCcrearpatrValorCuotaInscripcion(beneficiarioCTNEWDESDEAEptUsuario.getUsuarioValorCuotaInscripcion());
        step1Request.setBeneficiarioCcrearpatrTienePreexistencias(beneficiarioCTNEWDESDEAEptUsuario.getUsuarioTienePreexistencias());
        step1Request.setBeneficiarioCcrearpatrFechaInicioVigencia(beneficiarioCTNEWDESDEAEptUsuario.getUsuarioFechaInicioVigencia());
        step1Request.setBeneficiarioCcrearpatrFechaFinVigencia(beneficiarioCTNEWDESDEAEptUsuario.getUsuarioFechaFinVigencia());
        step1Request.setBeneficiarioCcrearpatrNumUsuario(beneficiarioCTNEWDESDEAEptUsuario.getUsuarioNumUsuario());
        instance = (BeneficiarioC) beneficiarioCSrv.crearImplementation(step1Request).getInstance();

        /* Action 2: IF (ptSolicitud.ContratantePersona = ptUsuario.UsuarioPersona) */
        boolean ifCondition2 = Utilities.isEqual(beneficiarioCTNEWDESDEAEptSolicitud.getContratantePersona(), beneficiarioCTNEWDESDEAEptUsuario.getUsuarioPersona());
        if (ifCondition2) {
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsBeneficiariosCRequest step20Request = new com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsBeneficiariosCRequest();
            step20Request.setBeneficiarioCInsBeneficiariosCpthisBeneficiarioCInstance(instance);
            step20Request.setBeneficiarioCInsBeneficiariosCpevcPersonaCInstance(instance.getFamiliasC().getContratoC().getPersonaCContratante());
            beneficiarioCSrv.insBeneficiariosCImplementation(step20Request, true);
        } else {
            /* Action 21: IF (ptSolicitud.TitularPersona = ptUsuario.UsuarioPersona) */
            boolean ifCondition21 = Utilities.isEqual(beneficiarioCTNEWDESDEAEptSolicitud.getTitularPersona(), beneficiarioCTNEWDESDEAEptUsuario.getUsuarioPersona());
            if (ifCondition21) {
                com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsBeneficiariosCRequest step210Request = new com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsBeneficiariosCRequest();
                step210Request.setBeneficiarioCInsBeneficiariosCpthisBeneficiarioCInstance(instance);
                step210Request.setBeneficiarioCInsBeneficiariosCpevcPersonaCInstance(instance.getFamiliasC().getPersonaCTitular());
                beneficiarioCSrv.insBeneficiariosCImplementation(step210Request, true);
            } else {
                com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWBENEFDESDEAERequest step211Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWBENEFDESDEAERequest();
                step211Request.setPersonaCTNEWBENEFDESDEAEptBeneficiarioInstance(instance);
                step211Request.setPersonaCTNEWBENEFDESDEAEptUsuarioInstance(beneficiarioCTNEWDESDEAEptUsuario);
                personaCSrv.tNEWBENEFDESDEAEImplementation(step211Request);
            }
        }

        /* Action 3: IF (EXIST (ptUsuario.AnexoMat) = true) */
        boolean ifCondition3 = repository.assocOperator022(beneficiarioCTNEWDESDEAEptUsuario).equals(true);
        if (ifCondition3) {
            com.keralty.aeenviodoc.viewmodel.anexoc.siu.CrearRequest step30Request = new com.keralty.aeenviodoc.viewmodel.anexoc.siu.CrearRequest();
            step30Request.setAnexoCcrearpagrPeriodicidadCInstance(beneficiarioCTNEWDESDEAEptUsuario.getAnexoMat().getPeriodicidad());
            step30Request.setAnexoCcrearpagrBeneficiarioCInstance(instance);
            step30Request.setAnexoCcrearpagrTipoIdentificacionCInstance(beneficiarioCTNEWDESDEAEptUsuario.getAnexoMat().getInstitucionMaternal().getTipoIdentificacion());
            step30Request.setAnexoCcrearpatrCodigoPlanAnexo(STDFunctions.intToStrFun(beneficiarioCTNEWDESDEAEptUsuario.getAnexoMat().getInstitucionMaternal().getTarifa().getProductoT().getProductosCodAnexoMat()));
            step30Request.setAnexoCcrearpatrCodigoTarifa(beneficiarioCTNEWDESDEAEptUsuario.getAnexoMat().getInstitucionMaternal().getTarifa().getTarifaCodigo());
            step30Request.setAnexoCcrearpatrFechaInicioVigencia(beneficiarioCTNEWDESDEAEptUsuario.getSolicitud().getSolicitudFInicioContrato());
            step30Request.setAnexoCcrearpatrFechaUltimaRegla(beneficiarioCTNEWDESDEAEptUsuario.getAnexoMat().getAnexoMatFechaUltRegla());
            step30Request.setAnexoCcrearpatrNumCuotasPago(beneficiarioCTNEWDESDEAEptUsuario.getAnexoMat().getAnexoMatNumCuotas());
            step30Request.setAnexoCcrearpatrNumIdentificacion(beneficiarioCTNEWDESDEAEptUsuario.getAnexoMat().getInstitucionMaternal().getInstitucionMatNeoNumIdentificacion());
            step30Request.setAnexoCcrearpatrCodSucursal(beneficiarioCTNEWDESDEAEptUsuario.getAnexoMat().getInstitucionMaternal().getInstitucionMatNeoCodigo());
            anexoCSrv.crearImplementation(step30Request);
        }

        /* Action 4: IF (EXIST (ptUsuario.AnexoNeo) = true) */
        boolean ifCondition4 = repository.assocOperator023(beneficiarioCTNEWDESDEAEptUsuario).equals(true);
        if (ifCondition4) {
            com.keralty.aeenviodoc.viewmodel.anexoc.siu.CrearRequest step40Request = new com.keralty.aeenviodoc.viewmodel.anexoc.siu.CrearRequest();
            step40Request.setAnexoCcrearpagrPeriodicidadCInstance(beneficiarioCTNEWDESDEAEptUsuario.getAnexoNeo().getPeriodicidad());
            step40Request.setAnexoCcrearpagrBeneficiarioCInstance(instance);
            step40Request.setAnexoCcrearpagrTipoIdentificacionCInstance(beneficiarioCTNEWDESDEAEptUsuario.getAnexoNeo().getInstitucionNeonatal().getTipoIdentificacion());
            step40Request.setAnexoCcrearpatrCodigoPlanAnexo(STDFunctions.intToStrFun(beneficiarioCTNEWDESDEAEptUsuario.getAnexoNeo().getInstitucionNeonatal().getTarifa().getProductoT().getProductosCodAnexoNeo()));
            step40Request.setAnexoCcrearpatrCodigoTarifa(beneficiarioCTNEWDESDEAEptUsuario.getAnexoNeo().getInstitucionNeonatal().getTarifa().getTarifaCodigo());
            step40Request.setAnexoCcrearpatrFechaInicioVigencia(beneficiarioCTNEWDESDEAEptUsuario.getSolicitud().getSolicitudFInicioContrato());
            step40Request.setAnexoCcrearpatrFechaUltimaRegla(beneficiarioCTNEWDESDEAEptUsuario.getAnexoNeo().getAnexoNeoFechaUltRegla());
            step40Request.setAnexoCcrearpatrNumCuotasPago(beneficiarioCTNEWDESDEAEptUsuario.getAnexoNeo().getAnexoNeoNumCuotas());
            step40Request.setAnexoCcrearpatrNumIdentificacion(beneficiarioCTNEWDESDEAEptUsuario.getAnexoNeo().getInstitucionNeonatal().getInstitucionMatNeoNumIdentificacion());
            step40Request.setAnexoCcrearpatrCodSucursal(beneficiarioCTNEWDESDEAEptUsuario.getAnexoNeo().getInstitucionNeonatal().getInstitucionMatNeoCodigo());
            anexoCSrv.crearImplementation(step40Request);
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TASGESTADOSINOTIENE

    /*
     * Entry point for service TASGESTADOSINOTIENE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tASGESTADOSINOTIENE(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.TASGESTADOSINOTIENERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.tASGESTADOSINOTIENETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TASGESTADOSINOTIENE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tASGESTADOSINOTIENETxn(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.TASGESTADOSINOTIENERequest request) throws ModelException, SystemException {
        request.setBeneficiarioCTASGESTADOSINOTIENEpthisBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getBeneficiarioCTASGESTADOSINOTIENEpthisBeneficiarioC()));
        request.setBeneficiarioCTASGESTADOSINOTIENEptEstadoInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getBeneficiarioCTASGESTADOSINOTIENEptEstado()));
        return beneficiarioCSrv.tASGESTADOSINOTIENEImplementation(request);
    }

    /*
     * Implements service TASGESTADOSINOTIENE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tASGESTADOSINOTIENEImplementation(
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.TASGESTADOSINOTIENERequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario beneficiarioCTASGESTADOSINOTIENEptEstado = request.getBeneficiarioCTASGESTADOSINOTIENEptEstadoInstance();
        BeneficiarioC beneficiarioCTASGESTADOSINOTIENEpthisBeneficiarioC = request.getBeneficiarioCTASGESTADOSINOTIENEpthisBeneficiarioCInstance();

        request.checkArguments();
        BeneficiarioC instance = beneficiarioCTASGESTADOSINOTIENEpthisBeneficiarioC;

        /* Action 1: IF (ptEstado <> NULL AND EXIST (EstadoBeneficiarioC) = false) */
        boolean ifCondition1 = (beneficiarioCTASGESTADOSINOTIENEptEstado != null && !beneficiarioCTASGESTADOSINOTIENEptEstado.isNull()) && repository.assocOperator033(beneficiarioCTASGESTADOSINOTIENEpthisBeneficiarioC).equals(false);
        if (ifCondition1) {
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsEstadoBeneficiarioCRequest step10Request = new com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsEstadoBeneficiarioCRequest();
            step10Request.setBeneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioCInstance(beneficiarioCTASGESTADOSINOTIENEpthisBeneficiarioC);
            step10Request.setBeneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiarioInstance(beneficiarioCTASGESTADOSINOTIENEptEstado);
            beneficiarioCSrv.insEstadoBeneficiarioCImplementation(step10Request, true);
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
        select.getOrderByPart().addItem(Constants.normalizePath(BeneficiarioCConstants.CLASS_NAME, BeneficiarioCConstants.ROLE_NAME_CONTRATOC + "." + ContratoCConstants.ATTR_FIELD_CONTRATOCID, false), true);
        select.getOrderByPart().addItem(Constants.normalizePath(BeneficiarioCConstants.CLASS_NAME, BeneficiarioCConstants.ATTR_FIELD_BENEFICIARIOCID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildBeneficiarioCRelatedInstance(relatedInstanceJsonOid);
        }
        if (BeneficiarioCConstants.ROLE_NAME_PERSONAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_ANEXOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4AnexoC(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_CONCEPTOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4ConceptoC(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_CONTRATOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4ContratoC(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_FAMILIASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4FamiliaC(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_EPSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4Eps(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_COMPANYIAMPC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4Companyia(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_PARENTESCOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4Parentesco(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_ESTADOBENEFICIARIOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4EstadoBeneficiario(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildBeneficiarioCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new BeneficiarioC(mapper.readValue(relatedInstanceJsonOid, BeneficiarioCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4AnexoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AnexoC(mapper.readValue(relatedInstanceJsonOid, AnexoCOid.class)): AnexoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4ConceptoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ConceptoC(mapper.readValue(relatedInstanceJsonOid, ConceptoCOid.class)): ConceptoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4ContratoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class)): ContratoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4FamiliaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FamiliaC(mapper.readValue(relatedInstanceJsonOid, FamiliaCOid.class)): FamiliaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4Eps(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Eps(mapper.readValue(relatedInstanceJsonOid, EpsOid.class)): Eps.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4Companyia(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Companyia(mapper.readValue(relatedInstanceJsonOid, CompanyiaOid.class)): Companyia.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4Parentesco(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Parentesco(mapper.readValue(relatedInstanceJsonOid, ParentescoOid.class)): Parentesco.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4EstadoBeneficiario(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoBeneficiario(mapper.readValue(relatedInstanceJsonOid, EstadoBeneficiarioOid.class)): EstadoBeneficiario.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            BeneficiarioCOid oid = new BeneficiarioCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(BeneficiarioCConstants.ROLE_NAME_CONTRATOC + "." + ContratoCConstants.ATTR_FIELD_CONTRATOCID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getContratoCContratoCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(BeneficiarioCConstants.ATTR_FIELD_BENEFICIARIOCID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getBeneficiarioCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(BeneficiarioCService.class).error(e.getMessage());
        }
    }

}
