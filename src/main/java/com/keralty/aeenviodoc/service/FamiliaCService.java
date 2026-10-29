package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.ContratoCConstants;
import com.keralty.aeenviodoc.global.FamiliaCConstants;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.oid.BeneficiarioCOid;
import com.keralty.aeenviodoc.persistence.oid.ContratoCOid;
import com.keralty.aeenviodoc.persistence.oid.FamiliaCOid;
import com.keralty.aeenviodoc.persistence.oid.MunicipioOid;
import com.keralty.aeenviodoc.persistence.oid.PersonaCOid;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.Usuario;
import com.keralty.aeenviodoc.repository.FamiliaCJpaRepository;
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
public class FamiliaCService extends AbstractService {

    FamiliaCJpaRepository repository;

    FamiliaCService familiaCSrv;

    PersonaCService personaCSrv;

    ContratoCService contratoCSrv;

    MunicipioService municipioSrv;

    SolicitudService solicitudSrv;

    DireccionService direccionSrv;

    ContratanteService contratanteSrv;

    PersonaService personaSrv;

    UsuarioService usuarioSrv;

    BeneficiarioCService beneficiarioCSrv;

    @Inject
    public FamiliaCService(
            FamiliaCService familiaCSrv,
            PersonaCService personaCSrv,
            ContratoCService contratoCSrv,
            MunicipioService municipioSrv,
            SolicitudService solicitudSrv,
            DireccionService direccionSrv,
            ContratanteService contratanteSrv,
            PersonaService personaSrv,
            UsuarioService usuarioSrv,
            BeneficiarioCService beneficiarioCSrv,
            FamiliaCJpaRepository repository) {
        this.familiaCSrv = familiaCSrv;
        this.personaCSrv = personaCSrv;
        this.contratoCSrv = contratoCSrv;
        this.municipioSrv = municipioSrv;
        this.solicitudSrv = solicitudSrv;
        this.direccionSrv = direccionSrv;
        this.contratanteSrv = contratanteSrv;
        this.personaSrv = personaSrv;
        this.usuarioSrv = usuarioSrv;
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, FamiliaCConstants.CLASS_NAME);
    }

    private List<FamiliaC> solveFamiliaCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, FamiliaCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveFamiliaCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, FamiliaCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class FamiliaC
     *
     * @return Population of class FamiliaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaC> get() {
        List<FamiliaC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class FamiliaC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class FamiliaC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaC> get(QueryRequest queryRequest) {
        return solveFamiliaCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveFamiliaCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveFamiliaCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveFamiliaCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of FamiliaC by ID
     *
     * @param contratoCContratoCID Identification field contratoCContratoCID
     *
     * @param familiaCID Identification field familiaCID
     *
     * @return Instance of FamiliaC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public FamiliaC getById(Long contratoCContratoCID, Long familiaCID) {
        return getByOID(new FamiliaCOid(contratoCContratoCID, familiaCID));
    }
    
    /*
     * Returns an instance of FamiliaC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of FamiliaC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public FamiliaC getByOID(FamiliaCOid oid) {
        Optional<FamiliaC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of FamiliaC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of FamiliaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public FamiliaC getByOIDWithHV(FamiliaCOid oid) {
        Optional<FamiliaC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<FamiliaC> getInstanceByOid(FamiliaCOid oid) {
        return oid != null && oid.getContratoCContratoCID() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of FamiliaC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of FamiliaC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public FamiliaC getByJsonOid(String jsonOid) {
        FamiliaCOid oid = new FamiliaCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of FamiliaC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of FamiliaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveFamiliaCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of FamiliaC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of FamiliaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of FamiliaC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of FamiliaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getFamiliaCDynamic(FamiliaCOid oid, String displaySetItems) {
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
    public String getSuppInfo(FamiliaCOid oid, String dsName) {
        FamiliaC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(FamiliaC instance, String dsName) {
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
    public com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearResponse crear(
            com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearRequest request) {
        com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearResponse response = new com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearResponse();
        try {
            response = familiaCSrv.crearTxn(request);
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
    public com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearResponse crearTxn(
            com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearRequest request) throws ModelException {
        request.setFamiliaCcrearpagrPersonaCTitularInstance(personaCSrv.getByOIDWithHV(request.getFamiliaCcrearpagrPersonaCTitular()));
        request.setFamiliaCcrearpagrContratoCInstance(contratoCSrv.getByOIDWithHV(request.getFamiliaCcrearpagrContratoC()));
        request.setFamiliaCcrearpagrMunicipioCInstance(municipioSrv.getByOIDWithHV(request.getFamiliaCcrearpagrMunicipioC()));
        return familiaCSrv.crearImplementation(request);
    }

    /*
     * Implements service crear
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearResponse crearImplementation(
            com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearRequest request) throws ModelException {
        com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearResponse response = new com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearResponse();
        ContratoC familiaCcrearpagrContratoC = request.getFamiliaCcrearpagrContratoCInstance();
        Municipio familiaCcrearpagrMunicipioC = request.getFamiliaCcrearpagrMunicipioCInstance();
        Boolean familiaCcrearpatrTitularConBeneficios = request.getFamiliaCcrearpatrTitularConBeneficios();
        String familiaCcrearpatrdireccionCorrespondencia = request.getFamiliaCcrearpatrdireccionCorrespondencia();
        String familiaCcrearpatrEstadoTitularFamilia = request.getFamiliaCcrearpatrEstadoTitularFamilia();
        Date familiaCcrearpatrFechaFinVigencia = request.getFamiliaCcrearpatrFechaFinVigencia();
        String familiaCcrearpatrNumFamilia = request.getFamiliaCcrearpatrNumFamilia();

        request.checkArguments();
        FamiliaC instance = new FamiliaC();

        instance.add2ContratoC(familiaCcrearpagrContratoC);

        if (familiaCcrearpagrMunicipioC != null && !familiaCcrearpagrMunicipioC.isNull()) {
            instance.add2MunicipioC(familiaCcrearpagrMunicipioC);
        }

        instance.setFamiliaCID(Long.valueOf(0));
        instance.setFamiliaCID(repository.nextFamiliaCID(instance.getContratoCContratoCID()));
        instance.setFamiliaCTitularConBeneficios(familiaCcrearpatrTitularConBeneficios);
        instance.setFamiliaCdireccionCorrespondencia(familiaCcrearpatrdireccionCorrespondencia);
        instance.setFamiliaCEstadoTitularFamilia(familiaCcrearpatrEstadoTitularFamilia);
        instance.setFamiliaCFechaFinVigencia(familiaCcrearpatrFechaFinVigencia);
        instance.setFamiliaCNumFamilia(familiaCcrearpatrNumFamilia);

        instance.setOid(instance.getOid());

        response.setInstance(repository.save(instance));

        // Value expressions for outgoing argument 'familiaCcrearpsFamiliaCreada'
        // DEFAULT  : THIS
        response.setFamiliaCcrearpsFamiliaCreadaInstance(instance);


        return response;
    }

    // Service: InsPersonaCTitular

    /*
     * Entry point for service InsPersonaCTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPersonaCTitular(
            com.keralty.aeenviodoc.viewmodel.familiac.siu.InsPersonaCTitularRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = familiaCSrv.insPersonaCTitularTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPersonaCTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPersonaCTitularTxn(
            com.keralty.aeenviodoc.viewmodel.familiac.siu.InsPersonaCTitularRequest request) throws ModelException {
        request.setFamiliaCInsPersonaCTitularpthisFamiliaCInstance(familiaCSrv.getByOIDWithHV(request.getFamiliaCInsPersonaCTitularpthisFamiliaC()));
        request.setFamiliaCInsPersonaCTitularpevcPersonaCInstance(personaCSrv.getByOIDWithHV(request.getFamiliaCInsPersonaCTitularpevcPersonaC()));
        return familiaCSrv.insPersonaCTitularImplementation(request, true);
    }

    /*
     * Implements service InsPersonaCTitular
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPersonaCTitularImplementation(
            com.keralty.aeenviodoc.viewmodel.familiac.siu.InsPersonaCTitularRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        FamiliaC familiaCInsPersonaCTitularpthisFamiliaC = request.getFamiliaCInsPersonaCTitularpthisFamiliaCInstance();
        PersonaC familiaCInsPersonaCTitularpevcPersonaC = request.getFamiliaCInsPersonaCTitularpevcPersonaCInstance();

        request.checkArguments();
        FamiliaC instance = familiaCInsPersonaCTitularpthisFamiliaC;

        instance.add2PersonaCTitular(familiaCInsPersonaCTitularpevcPersonaC);
        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.personac.siu.InsPersonaCTitularRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.personac.siu.InsPersonaCTitularRequest();
            relatedRequest.setPersonaCInsPersonaCTitularpthisPersonaCInstance(familiaCInsPersonaCTitularpevcPersonaC);
            relatedRequest.setPersonaCInsPersonaCTitularpevcFamiliaCInstance(familiaCInsPersonaCTitularpthisFamiliaC);
            personaCSrv.insPersonaCTitularImplementation(relatedRequest, false);
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
            com.keralty.aeenviodoc.viewmodel.familiac.siu.TNEWDESDEAERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = familiaCSrv.tNEWDESDEAETxn(request);
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
            com.keralty.aeenviodoc.viewmodel.familiac.siu.TNEWDESDEAERequest request) throws ModelException, SystemException {
        request.setFamiliaCTNEWDESDEAEptContratoCInstance(contratoCSrv.getByOIDWithHV(request.getFamiliaCTNEWDESDEAEptContratoC()));
        request.setFamiliaCTNEWDESDEAEptSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getFamiliaCTNEWDESDEAEptSolicitud()));
        return familiaCSrv.tNEWDESDEAEImplementation(request);
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
            com.keralty.aeenviodoc.viewmodel.familiac.siu.TNEWDESDEAERequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        String familiaCTNEWDESDEAEptNumFamiliaCORE = request.getFamiliaCTNEWDESDEAEptNumFamiliaCORE();
        ContratoC familiaCTNEWDESDEAEptContratoC = request.getFamiliaCTNEWDESDEAEptContratoCInstance();
        Solicitud familiaCTNEWDESDEAEptSolicitud = request.getFamiliaCTNEWDESDEAEptSolicitudInstance();

        request.checkArguments();
        FamiliaC instance;

        /* Action 1: IF (ptNumFamiliaCORE <> NULL) */
        boolean ifCondition1 = familiaCTNEWDESDEAEptNumFamiliaCORE != null;
        if (ifCondition1) {
            com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearRequest step10Request = new com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearRequest();
            step10Request.setFamiliaCcrearpagrPersonaCTitularInstance(null);
            step10Request.setFamiliaCcrearpagrContratoCInstance(familiaCTNEWDESDEAEptContratoC);
            step10Request.setFamiliaCcrearpagrMunicipioCInstance(familiaCTNEWDESDEAEptSolicitud.getDireccion().getMunicipio());
            step10Request.setFamiliaCcrearpatrTitularConBeneficios(familiaCTNEWDESDEAEptSolicitud.getContratante().getContratanteTitularConBeneficios());
            step10Request.setFamiliaCcrearpatrdireccionCorrespondencia(familiaCTNEWDESDEAEptSolicitud.getDireccion().getDireccionDireccionCompleta());
            step10Request.setFamiliaCcrearpatrEstadoTitularFamilia(null);
            step10Request.setFamiliaCcrearpatrFechaFinVigencia(null);
            step10Request.setFamiliaCcrearpatrNumFamilia(familiaCTNEWDESDEAEptNumFamiliaCORE);
            instance = (FamiliaC) familiaCSrv.crearImplementation(step10Request).getInstance();
            
        } else {
            com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearRequest step11Request = new com.keralty.aeenviodoc.viewmodel.familiac.siu.CrearRequest();
            step11Request.setFamiliaCcrearpagrPersonaCTitularInstance(null);
            step11Request.setFamiliaCcrearpagrContratoCInstance(familiaCTNEWDESDEAEptContratoC);
            step11Request.setFamiliaCcrearpagrMunicipioCInstance(familiaCTNEWDESDEAEptSolicitud.getDireccion().getMunicipio());
            step11Request.setFamiliaCcrearpatrTitularConBeneficios(familiaCTNEWDESDEAEptSolicitud.getContratante().getContratanteTitularConBeneficios());
            step11Request.setFamiliaCcrearpatrdireccionCorrespondencia(familiaCTNEWDESDEAEptSolicitud.getDireccion().getDireccionDireccionCompleta());
            step11Request.setFamiliaCcrearpatrEstadoTitularFamilia(null);
            step11Request.setFamiliaCcrearpatrFechaFinVigencia(null);
            step11Request.setFamiliaCcrearpatrNumFamilia("1");
            instance = (FamiliaC) familiaCSrv.crearImplementation(step11Request).getInstance();
            
        }

        /* Action 2: IF (ptSolicitud.ContratantePersona = ptSolicitud.TitularPersona) */
        boolean ifCondition2 = Utilities.isEqual(familiaCTNEWDESDEAEptSolicitud.getContratantePersona(), familiaCTNEWDESDEAEptSolicitud.getTitularPersona());
        if (ifCondition2) {
            com.keralty.aeenviodoc.viewmodel.familiac.siu.InsPersonaCTitularRequest step20Request = new com.keralty.aeenviodoc.viewmodel.familiac.siu.InsPersonaCTitularRequest();
            step20Request.setFamiliaCInsPersonaCTitularpthisFamiliaCInstance(instance);
            step20Request.setFamiliaCInsPersonaCTitularpevcPersonaCInstance(instance.getContratoC().getPersonaCContratante());
            familiaCSrv.insPersonaCTitularImplementation(step20Request, true);
        } else {
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWTITULARDESDEAERequest step21Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWTITULARDESDEAERequest();
            step21Request.setPersonaCTNEWTITULARDESDEAEptSolicitudInstance(familiaCTNEWDESDEAEptSolicitud);
            step21Request.setPersonaCTNEWTITULARDESDEAEptFamiliaCInstance(instance);
            personaCSrv.tNEWTITULARDESDEAEImplementation(step21Request);
        }

        for(Usuario step3Entity : repository.assocOperator003(familiaCTNEWDESDEAEptSolicitud)) {
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.TNEWDESDEAERequest step3Request = new com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.TNEWDESDEAERequest();
            step3Request.setBeneficiarioCTNEWDESDEAEptContratoInstance(familiaCTNEWDESDEAEptContratoC);
            step3Request.setBeneficiarioCTNEWDESDEAEptFamiliaInstance(instance);
            step3Request.setBeneficiarioCTNEWDESDEAEptUsuarioInstance(step3Entity);
            step3Request.setBeneficiarioCTNEWDESDEAEptSolicitudInstance(familiaCTNEWDESDEAEptSolicitud);
            beneficiarioCSrv.tNEWDESDEAEImplementation(step3Request);
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCFamiliaCNumero(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(FamiliaCConstants.CLASS_NAME, FamiliaCConstants.ROLE_NAME_CONTRATOC + "." + ContratoCConstants.ATTR_FIELD_CONTRATOCID, false), true);
        select.getOrderByPart().addItem(Constants.normalizePath(FamiliaCConstants.CLASS_NAME, FamiliaCConstants.ATTR_FIELD_FAMILIACID, false), true);
    }


    private void applyOCOCFamiliaCNumero(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_FamiliaCNumero".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(FamiliaCConstants.CLASS_NAME, FamiliaCConstants.ATTR_NAME_FAMILIACNUMFAMILIA, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildFamiliaCRelatedInstance(relatedInstanceJsonOid);
        }
        if (FamiliaCConstants.ROLE_NAME_PERSONACTITULAR.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaCConstants.ROLE_NAME_BENEFICIARIOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaCRelatedInstance4BeneficiarioC(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaCConstants.ROLE_NAME_CONTRATOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaCRelatedInstance4ContratoC(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaCConstants.ROLE_NAME_MUNICIPIOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaCRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildFamiliaCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new FamiliaC(mapper.readValue(relatedInstanceJsonOid, FamiliaCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFamiliaCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFamiliaCRelatedInstance4BeneficiarioC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new BeneficiarioC(mapper.readValue(relatedInstanceJsonOid, BeneficiarioCOid.class)): BeneficiarioC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFamiliaCRelatedInstance4ContratoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class)): ContratoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildFamiliaCRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            FamiliaCOid oid = new FamiliaCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FamiliaCConstants.ROLE_NAME_CONTRATOC + "." + ContratoCConstants.ATTR_FIELD_CONTRATOCID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getContratoCContratoCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(FamiliaCConstants.ATTR_FIELD_FAMILIACID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getFamiliaCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(FamiliaCService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
