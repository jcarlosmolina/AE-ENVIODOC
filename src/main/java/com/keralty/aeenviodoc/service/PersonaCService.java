package com.keralty.aeenviodoc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeenviodoc.exception.ModelException;
import com.keralty.aeenviodoc.exception.SystemException;
import com.keralty.aeenviodoc.global.Constants;
import com.keralty.aeenviodoc.global.PersonaCConstants;
import com.keralty.aeenviodoc.global.STDFunctions;
import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.BeneficiarioC;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.CuestionarioP;
import com.keralty.aeenviodoc.persistence.Departamento;
import com.keralty.aeenviodoc.persistence.Direccion;
import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.persistence.EstadoCivil;
import com.keralty.aeenviodoc.persistence.EstadoPersona;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.Municipio;
import com.keralty.aeenviodoc.persistence.oid.BeneficiarioCOid;
import com.keralty.aeenviodoc.persistence.oid.ContratoCOid;
import com.keralty.aeenviodoc.persistence.oid.CuestionarioPOid;
import com.keralty.aeenviodoc.persistence.oid.DepartamentoOid;
import com.keralty.aeenviodoc.persistence.oid.DireccionCOid;
import com.keralty.aeenviodoc.persistence.oid.DirSucursalCOid;
import com.keralty.aeenviodoc.persistence.oid.EstadoCivilOid;
import com.keralty.aeenviodoc.persistence.oid.EstadoPersonaOid;
import com.keralty.aeenviodoc.persistence.oid.FamiliaCOid;
import com.keralty.aeenviodoc.persistence.oid.MunicipioOid;
import com.keralty.aeenviodoc.persistence.oid.PersonaCOid;
import com.keralty.aeenviodoc.persistence.oid.PreexistenciaCOid;
import com.keralty.aeenviodoc.persistence.oid.PregCuesMedCOid;
import com.keralty.aeenviodoc.persistence.oid.ProteccionDatosCOid;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.oid.TipoEntidadCOid;
import com.keralty.aeenviodoc.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeenviodoc.persistence.oid.TipoInstitucionCOid;
import com.keralty.aeenviodoc.persistence.oid.UsuarioOid;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.persistence.Preexistencia;
import com.keralty.aeenviodoc.persistence.PreexistenciaC;
import com.keralty.aeenviodoc.persistence.PregCuesMedC;
import com.keralty.aeenviodoc.persistence.PreguntaP;
import com.keralty.aeenviodoc.persistence.ProteccionDatosC;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.persistence.TipoEntidadC;
import com.keralty.aeenviodoc.persistence.TipoIdentificacion;
import com.keralty.aeenviodoc.persistence.TipoInstitucionC;
import com.keralty.aeenviodoc.persistence.Usuario;
import com.keralty.aeenviodoc.repository.PersonaCJpaRepository;
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
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class PersonaCService extends AbstractService {

    PersonaCJpaRepository repository;

    PersonaCService personaCSrv;

    TipoInstitucionCService tipoInstitucionCSrv;

    TipoEntidadCService tipoEntidadCSrv;

    EstadoCivilService estadoCivilSrv;

    EstadoPersonaService estadoPersonaSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    MunicipioService municipioSrv;

    DepartamentoService departamentoSrv;

    ContratoCService contratoCSrv;

    FamiliaCService familiaCSrv;

    BeneficiarioCService beneficiarioCSrv;

    CuestionarioPService cuestionarioPSrv;

    PregCuesMedCService pregCuesMedCSrv;

    SeccionPService seccionPSrv;

    PreguntaPService preguntaPSrv;

    SolicitudService solicitudSrv;

    PersonaService personaSrv;

    DireccionService direccionSrv;

    DireccionCService direccionCSrv;

    TipoDireccionService tipoDireccionSrv;

    PaisService paisSrv;

    DirSucursalCService dirSucursalCSrv;

    ProteccionDatosService proteccionDatosSrv;

    ProteccionDatosCService proteccionDatosCSrv;

    ProductosService productosSrv;

    AesolicitudesService aesolicitudesSrv;

    UsuarioService usuarioSrv;

    PreexistenciaService preexistenciaSrv;

    PreexistenciaCService preexistenciaCSrv;

    @Inject
    public PersonaCService(
            PersonaCService personaCSrv,
            TipoInstitucionCService tipoInstitucionCSrv,
            TipoEntidadCService tipoEntidadCSrv,
            EstadoCivilService estadoCivilSrv,
            EstadoPersonaService estadoPersonaSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            MunicipioService municipioSrv,
            DepartamentoService departamentoSrv,
            ContratoCService contratoCSrv,
            FamiliaCService familiaCSrv,
            BeneficiarioCService beneficiarioCSrv,
            CuestionarioPService cuestionarioPSrv,
            PregCuesMedCService pregCuesMedCSrv,
            SeccionPService seccionPSrv,
            PreguntaPService preguntaPSrv,
            SolicitudService solicitudSrv,
            PersonaService personaSrv,
            DireccionService direccionSrv,
            DireccionCService direccionCSrv,
            TipoDireccionService tipoDireccionSrv,
            PaisService paisSrv,
            DirSucursalCService dirSucursalCSrv,
            ProteccionDatosService proteccionDatosSrv,
            ProteccionDatosCService proteccionDatosCSrv,
            ProductosService productosSrv,
            AesolicitudesService aesolicitudesSrv,
            UsuarioService usuarioSrv,
            PreexistenciaService preexistenciaSrv,
            PreexistenciaCService preexistenciaCSrv,
            PersonaCJpaRepository repository) {
        this.personaCSrv = personaCSrv;
        this.tipoInstitucionCSrv = tipoInstitucionCSrv;
        this.tipoEntidadCSrv = tipoEntidadCSrv;
        this.estadoCivilSrv = estadoCivilSrv;
        this.estadoPersonaSrv = estadoPersonaSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.municipioSrv = municipioSrv;
        this.departamentoSrv = departamentoSrv;
        this.contratoCSrv = contratoCSrv;
        this.familiaCSrv = familiaCSrv;
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.cuestionarioPSrv = cuestionarioPSrv;
        this.pregCuesMedCSrv = pregCuesMedCSrv;
        this.seccionPSrv = seccionPSrv;
        this.preguntaPSrv = preguntaPSrv;
        this.solicitudSrv = solicitudSrv;
        this.personaSrv = personaSrv;
        this.direccionSrv = direccionSrv;
        this.direccionCSrv = direccionCSrv;
        this.tipoDireccionSrv = tipoDireccionSrv;
        this.paisSrv = paisSrv;
        this.dirSucursalCSrv = dirSucursalCSrv;
        this.proteccionDatosSrv = proteccionDatosSrv;
        this.proteccionDatosCSrv = proteccionDatosCSrv;
        this.productosSrv = productosSrv;
        this.aesolicitudesSrv = aesolicitudesSrv;
        this.usuarioSrv = usuarioSrv;
        this.preexistenciaSrv = preexistenciaSrv;
        this.preexistenciaCSrv = preexistenciaCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, PersonaCConstants.CLASS_NAME);
    }

    private List<PersonaC> solvePersonaCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, PersonaCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solvePersonaCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, PersonaCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class PersonaC
     *
     * @return Population of class PersonaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PersonaC> get() {
        List<PersonaC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class PersonaC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class PersonaC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PersonaC> get(QueryRequest queryRequest) {
        return solvePersonaCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solvePersonaCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solvePersonaCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solvePersonaCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of PersonaC by ID
     *
     * @param personaCID Identification field personaCID
     *
     * @return Instance of PersonaC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public PersonaC getById(Long personaCID) {
        return getByOID(new PersonaCOid(personaCID));
    }
    
    /*
     * Returns an instance of PersonaC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of PersonaC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public PersonaC getByOID(PersonaCOid oid) {
        Optional<PersonaC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of PersonaC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of PersonaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PersonaC getByOIDWithHV(PersonaCOid oid) {
        Optional<PersonaC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<PersonaC> getInstanceByOid(PersonaCOid oid) {
        return oid != null && oid.getPersonaCID() != null
               ? repository.findById(oid.getPersonaCID())
               : Optional.empty();
    }

    /*
     * Returns an instance of PersonaC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of PersonaC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public PersonaC getByJsonOid(String jsonOid) {
        PersonaCOid oid = new PersonaCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of PersonaC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of PersonaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PersonaC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solvePersonaCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of PersonaC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of PersonaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PersonaC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of PersonaC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of PersonaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getPersonaCDynamic(PersonaCOid oid, String displaySetItems) {
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
    public String getSuppInfo(PersonaCOid oid, String dsName) {
        PersonaC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(PersonaC instance, String dsName) {
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
            com.keralty.aeenviodoc.viewmodel.personac.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.crearTxn(request);
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
            com.keralty.aeenviodoc.viewmodel.personac.siu.CrearRequest request) throws ModelException {
        request.setPersonaCcrearpagrTipoInstitucionCInstance(tipoInstitucionCSrv.getByOIDWithHV(request.getPersonaCcrearpagrTipoInstitucionC()));
        request.setPersonaCcrearpagrTipoEntidadCInstance(tipoEntidadCSrv.getByOIDWithHV(request.getPersonaCcrearpagrTipoEntidadC()));
        request.setPersonaCcrearpagrEstadoCivilCInstance(estadoCivilSrv.getByOIDWithHV(request.getPersonaCcrearpagrEstadoCivilC()));
        request.setPersonaCcrearpagrEstadoPersonaCInstance(estadoPersonaSrv.getByOIDWithHV(request.getPersonaCcrearpagrEstadoPersonaC()));
        request.setPersonaCcrearpagrTipoIdentificacionCInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getPersonaCcrearpagrTipoIdentificacionC()));
        request.setPersonaCcrearpagrMunicipioCNacInstance(municipioSrv.getByOIDWithHV(request.getPersonaCcrearpagrMunicipioCNac()));
        request.setPersonaCcrearpagrDepartamentoCNacInstance(departamentoSrv.getByOIDWithHV(request.getPersonaCcrearpagrDepartamentoCNac()));
        request.setPersonaCcrearpagrContratosCInstance(contratoCSrv.getByOIDWithHV(request.getPersonaCcrearpagrContratosC()));
        request.setPersonaCcrearpagrFamiliasCInstance(familiaCSrv.getByOIDWithHV(request.getPersonaCcrearpagrFamiliasC()));
        request.setPersonaCcrearpagrBeneficiariosCInstance(beneficiarioCSrv.getByOIDWithHV(request.getPersonaCcrearpagrBeneficiariosC()));
        return personaCSrv.crearImplementation(request);
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
            com.keralty.aeenviodoc.viewmodel.personac.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoInstitucionC personaCcrearpagrTipoInstitucionC = request.getPersonaCcrearpagrTipoInstitucionCInstance();
        TipoEntidadC personaCcrearpagrTipoEntidadC = request.getPersonaCcrearpagrTipoEntidadCInstance();
        EstadoCivil personaCcrearpagrEstadoCivilC = request.getPersonaCcrearpagrEstadoCivilCInstance();
        EstadoPersona personaCcrearpagrEstadoPersonaC = request.getPersonaCcrearpagrEstadoPersonaCInstance();
        TipoIdentificacion personaCcrearpagrTipoIdentificacionC = request.getPersonaCcrearpagrTipoIdentificacionCInstance();
        Municipio personaCcrearpagrMunicipioCNac = request.getPersonaCcrearpagrMunicipioCNacInstance();
        Departamento personaCcrearpagrDepartamentoCNac = request.getPersonaCcrearpagrDepartamentoCNacInstance();
        ContratoC personaCcrearpagrContratosC = request.getPersonaCcrearpagrContratosCInstance();
        FamiliaC personaCcrearpagrFamiliasC = request.getPersonaCcrearpagrFamiliasCInstance();
        BeneficiarioC personaCcrearpagrBeneficiariosC = request.getPersonaCcrearpagrBeneficiariosCInstance();
        String personaCcrearpatrNumIdentificacion = request.getPersonaCcrearpatrNumIdentificacion();
        String personaCcrearpatrMail = request.getPersonaCcrearpatrMail();
        String personaCcrearpatrPrimerNombre = request.getPersonaCcrearpatrPrimerNombre();
        String personaCcrearpatrSegundoNombre = request.getPersonaCcrearpatrSegundoNombre();
        String personaCcrearpatrPrimerApellido = request.getPersonaCcrearpatrPrimerApellido();
        String personaCcrearpatrSegundoApellido = request.getPersonaCcrearpatrSegundoApellido();
        Date personaCcrearpatrFechaNacimiento = request.getPersonaCcrearpatrFechaNacimiento();
        String personaCcrearpatrGenero = request.getPersonaCcrearpatrGenero();
        Boolean personaCcrearpatrIndicadorExtranjero = request.getPersonaCcrearpatrIndicadorExtranjero();
        String personaCcrearpatrRazonSocial = request.getPersonaCcrearpatrRazonSocial();
        Boolean personaCcrearpatrTieneEPSSanitas = request.getPersonaCcrearpatrTieneEPSSanitas();
        String personaCcrearpatrRechazadoAfiliacion = request.getPersonaCcrearpatrRechazadoAfiliacion();
        String personaCcrearpatrNombreCompleto = request.getPersonaCcrearpatrNombreCompleto();
        Long personaCcrearpatrCodigo = request.getPersonaCcrearpatrCodigo();
        Timestamp personaCcrearpatrUltimaActualizacion = request.getPersonaCcrearpatrUltimaActualizacion();

        request.checkArguments();
        PersonaC instance = new PersonaC();
        if (personaCcrearpagrTipoInstitucionC != null && !personaCcrearpagrTipoInstitucionC.isNull()) {
            instance.add2TipoInstitucionC(personaCcrearpagrTipoInstitucionC);
        }

        if (personaCcrearpagrTipoEntidadC != null && !personaCcrearpagrTipoEntidadC.isNull()) {
            instance.add2TipoEntidadC(personaCcrearpagrTipoEntidadC);
        }

        if (personaCcrearpagrEstadoCivilC != null && !personaCcrearpagrEstadoCivilC.isNull()) {
            instance.add2EstadoCivilC(personaCcrearpagrEstadoCivilC);
        }

        if (personaCcrearpagrEstadoPersonaC != null && !personaCcrearpagrEstadoPersonaC.isNull()) {
            instance.add2EstadoPersonaC(personaCcrearpagrEstadoPersonaC);
        }

        instance.add2TipoIdentificacionC(personaCcrearpagrTipoIdentificacionC);

        if (personaCcrearpagrMunicipioCNac != null && !personaCcrearpagrMunicipioCNac.isNull()) {
            instance.add2MunicipioCNac(personaCcrearpagrMunicipioCNac);
        }

        if (personaCcrearpagrDepartamentoCNac != null && !personaCcrearpagrDepartamentoCNac.isNull()) {
            instance.add2DepartamentoCNac(personaCcrearpagrDepartamentoCNac);
        }

        if (personaCcrearpagrContratosC != null && !personaCcrearpagrContratosC.isNull()) {
            personaCcrearpagrContratosC.add2PersonaCContratante(instance);
            instance.add2ContratosC(personaCcrearpagrContratosC);
        }

        if (personaCcrearpagrFamiliasC != null && !personaCcrearpagrFamiliasC.isNull()) {
            personaCcrearpagrFamiliasC.add2PersonaCTitular(instance);
            instance.add2FamiliasC(personaCcrearpagrFamiliasC);
        }

        if (personaCcrearpagrBeneficiariosC != null && !personaCcrearpagrBeneficiariosC.isNull()) {
            personaCcrearpagrBeneficiariosC.add2PersonaC(instance);
            instance.add2BeneficiariosC(personaCcrearpagrBeneficiariosC);
        }

        instance.setPersonaCID(Long.valueOf(0));
        instance.setPersonaCNumIdentificacion(personaCcrearpatrNumIdentificacion);
        instance.setPersonaCMail(personaCcrearpatrMail);
        instance.setPersonaCPrimerNombre(personaCcrearpatrPrimerNombre);
        instance.setPersonaCSegundoNombre(personaCcrearpatrSegundoNombre);
        instance.setPersonaCPrimerApellido(personaCcrearpatrPrimerApellido);
        instance.setPersonaCSegundoApellido(personaCcrearpatrSegundoApellido);
        instance.setPersonaCFechaNacimiento(personaCcrearpatrFechaNacimiento);
        instance.setPersonaCGenero(personaCcrearpatrGenero);
        instance.setPersonaCIndicadorExtranjero(personaCcrearpatrIndicadorExtranjero);
        instance.setPersonaCRazonSocial(personaCcrearpatrRazonSocial);
        instance.setPersonaCTieneEPSSanitas(personaCcrearpatrTieneEPSSanitas);
        instance.setPersonaCRechazadoAfiliacion(personaCcrearpatrRechazadoAfiliacion);
        instance.setPersonaCNombreCompleto(personaCcrearpatrNombreCompleto);
        instance.setPersonaCCodigo(personaCcrearpatrCodigo);
        instance.setPersonaCUltimaActualizacion(personaCcrearpatrUltimaActualizacion);

        response.setInstance(repository.save(instance));

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
            com.keralty.aeenviodoc.viewmodel.personac.siu.InsPersonaCTitularRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.insPersonaCTitularTxn(request);
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
            com.keralty.aeenviodoc.viewmodel.personac.siu.InsPersonaCTitularRequest request) throws ModelException {
        request.setPersonaCInsPersonaCTitularpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCInsPersonaCTitularpthisPersonaC()));
        request.setPersonaCInsPersonaCTitularpevcFamiliaCInstance(familiaCSrv.getByOIDWithHV(request.getPersonaCInsPersonaCTitularpevcFamiliaC()));
        return personaCSrv.insPersonaCTitularImplementation(request, true);
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
            com.keralty.aeenviodoc.viewmodel.personac.siu.InsPersonaCTitularRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCInsPersonaCTitularpthisPersonaC = request.getPersonaCInsPersonaCTitularpthisPersonaCInstance();
        FamiliaC personaCInsPersonaCTitularpevcFamiliaC = request.getPersonaCInsPersonaCTitularpevcFamiliaCInstance();

        request.checkArguments();
        PersonaC instance = personaCInsPersonaCTitularpthisPersonaC;

        instance.add2FamiliasC(personaCInsPersonaCTitularpevcFamiliaC);
        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.familiac.siu.InsPersonaCTitularRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.familiac.siu.InsPersonaCTitularRequest();
            relatedRequest.setFamiliaCInsPersonaCTitularpthisFamiliaCInstance(personaCInsPersonaCTitularpevcFamiliaC);
            relatedRequest.setFamiliaCInsPersonaCTitularpevcPersonaCInstance(personaCInsPersonaCTitularpthisPersonaC);
            familiaCSrv.insPersonaCTitularImplementation(relatedRequest, false);
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
            com.keralty.aeenviodoc.viewmodel.personac.siu.InsBeneficiariosCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.insBeneficiariosCTxn(request);
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
            com.keralty.aeenviodoc.viewmodel.personac.siu.InsBeneficiariosCRequest request) throws ModelException {
        request.setPersonaCInsBeneficiariosCpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCInsBeneficiariosCpthisPersonaC()));
        request.setPersonaCInsBeneficiariosCpevcBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getPersonaCInsBeneficiariosCpevcBeneficiarioC()));
        return personaCSrv.insBeneficiariosCImplementation(request, true);
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
            com.keralty.aeenviodoc.viewmodel.personac.siu.InsBeneficiariosCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCInsBeneficiariosCpthisPersonaC = request.getPersonaCInsBeneficiariosCpthisPersonaCInstance();
        BeneficiarioC personaCInsBeneficiariosCpevcBeneficiarioC = request.getPersonaCInsBeneficiariosCpevcBeneficiarioCInstance();

        request.checkArguments();
        PersonaC instance = personaCInsBeneficiariosCpthisPersonaC;

        instance.add2BeneficiariosC(personaCInsBeneficiariosCpevcBeneficiarioC);
        if (isEntryPoint) {
            com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsBeneficiariosCRequest relatedRequest = new com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu.InsBeneficiariosCRequest();
            relatedRequest.setBeneficiarioCInsBeneficiariosCpthisBeneficiarioCInstance(personaCInsBeneficiariosCpevcBeneficiarioC);
            relatedRequest.setBeneficiarioCInsBeneficiariosCpevcPersonaCInstance(personaCInsBeneficiariosCpthisPersonaC);
            beneficiarioCSrv.insBeneficiariosCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: TCREAROCUESDEAE

    /*
     * Entry point for service TCREAROCUESDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREAROCUESDEAE(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCREAROCUESDEAERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.tCREAROCUESDEAETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREAROCUESDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREAROCUESDEAETxn(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCREAROCUESDEAERequest request) throws ModelException, SystemException {
        request.setPersonaCTCREAROCUESDEAEpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCTCREAROCUESDEAEpthisPersonaC()));
        request.setPersonaCTCREAROCUESDEAEpCuestionarioPInstance(cuestionarioPSrv.getByOIDWithHV(request.getPersonaCTCREAROCUESDEAEpCuestionarioP()));
        return personaCSrv.tCREAROCUESDEAEImplementation(request);
    }

    /*
     * Implements service TCREAROCUESDEAE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREAROCUESDEAEImplementation(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCREAROCUESDEAERequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        CuestionarioP personaCTCREAROCUESDEAEpCuestionarioP = request.getPersonaCTCREAROCUESDEAEpCuestionarioPInstance();
        PersonaC personaCTCREAROCUESDEAEpthisPersonaC = request.getPersonaCTCREAROCUESDEAEpthisPersonaCInstance();

        request.checkArguments();
        PersonaC instance = personaCTCREAROCUESDEAEpthisPersonaC;

        /* Action 1: IF (EXIST (PregsCuesMedC) = true) */
        boolean ifCondition1 = repository.assocOperator015(personaCTCREAROCUESDEAEpthisPersonaC).equals(true);
        if (ifCondition1) {
            for(PregCuesMedC step110Entity : repository.assocOperator013(personaCTCREAROCUESDEAEpthisPersonaC)) {
                com.keralty.aeenviodoc.viewmodel.pregcuesmedc.siu.EliminarRequest step10Request = new com.keralty.aeenviodoc.viewmodel.pregcuesmedc.siu.EliminarRequest();
                step10Request.setPregCuesMedCeliminarpthisPregCuesMedCInstance(step110Entity);
                pregCuesMedCSrv.eliminarImplementation(step10Request);
            }
        }

        for(PreguntaP step2Entity : repository.assocOperator014(personaCTCREAROCUESDEAEpthisPersonaC, personaCTCREAROCUESDEAEpCuestionarioP)) {
            com.keralty.aeenviodoc.viewmodel.pregcuesmedc.siu.CrearRequest step2Request = new com.keralty.aeenviodoc.viewmodel.pregcuesmedc.siu.CrearRequest();
            step2Request.setPregCuesMedCcrearpagrPersonaCInstance(personaCTCREAROCUESDEAEpthisPersonaC);
            step2Request.setPregCuesMedCcrearpatrCodPregunta(step2Entity.getPreguntaPNumero());
            step2Request.setPregCuesMedCcrearpatrRptaPregunta(step2Entity.getPreguntaPRespuestaSiNo());
            step2Request.setPregCuesMedCcrearpatrObservacions(step2Entity.getPreguntaPdrvRespuesta());
            pregCuesMedCSrv.crearImplementation(step2Request);
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TNEWCONTRADESDEAE

    /*
     * Entry point for service TNEWCONTRADESDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tNEWCONTRADESDEAE(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWCONTRADESDEAERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.tNEWCONTRADESDEAETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TNEWCONTRADESDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tNEWCONTRADESDEAETxn(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWCONTRADESDEAERequest request) throws ModelException, SystemException {
        request.setPersonaCTNEWCONTRADESDEAEptSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getPersonaCTNEWCONTRADESDEAEptSolicitud()));
        request.setPersonaCTNEWCONTRADESDEAEptContratoInstance(contratoCSrv.getByOIDWithHV(request.getPersonaCTNEWCONTRADESDEAEptContrato()));
        return personaCSrv.tNEWCONTRADESDEAEImplementation(request);
    }

    /*
     * Implements service TNEWCONTRADESDEAE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tNEWCONTRADESDEAEImplementation(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWCONTRADESDEAERequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Solicitud personaCTNEWCONTRADESDEAEptSolicitud = request.getPersonaCTNEWCONTRADESDEAEptSolicitudInstance();
        ContratoC personaCTNEWCONTRADESDEAEptContrato = request.getPersonaCTNEWCONTRADESDEAEptContratoInstance();

        request.checkArguments();
        PersonaC instance;

        com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWRequest step1Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWRequest();
        step1Request.setPersonaCTNEWptpagrTipoInstitucionCInstance(null);
        step1Request.setPersonaCTNEWptpagrTipoEntidadCInstance(null);
        step1Request.setPersonaCTNEWptpagrEstadoCivilCInstance(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getEstadoCivil());
        step1Request.setPersonaCTNEWptpagrEstadoPersonaCInstance(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getEstadoPersona());
        step1Request.setPersonaCTNEWptpagrTipoIdentificacionCInstance(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getTipoIdentificacion());
        step1Request.setPersonaCTNEWptpagrMunicipioCNacInstance(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getMunicipioNacimiento());
        step1Request.setPersonaCTNEWptpagrDepartamentoCNacInstance(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getDepartamentoNacimiento());
        step1Request.setPersonaCTNEWptpagrContratosCInstance(personaCTNEWCONTRADESDEAEptContrato);
        step1Request.setPersonaCTNEWptpagrFamiliasCInstance(null);
        step1Request.setPersonaCTNEWptpagrBeneficiariosCInstance(null);
        step1Request.setPersonaCTNEWptpatrNumIdentificacion(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaNumIdentificacion());
        step1Request.setPersonaCTNEWptpatrMail(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaCorreoElectronico());
        step1Request.setPersonaCTNEWptpatrPrimerNombre(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaPrimerNombre());
        step1Request.setPersonaCTNEWptpatrSegundoNombre(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaSegundoNombre());
        step1Request.setPersonaCTNEWptpatrPrimerApellido(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaPrimerApellido());
        step1Request.setPersonaCTNEWptpatrSegundoApellido(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaSegundoApellido());
        step1Request.setPersonaCTNEWptpatrFechaNacimiento(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaFechaNacimiento());
        step1Request.setPersonaCTNEWptpatrGenero(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaGenero());
        step1Request.setPersonaCTNEWptpatrIndicadorExtranjero(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaIndicadorExtranjero());
        step1Request.setPersonaCTNEWptpatrRazonSocial(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaRazonSocial());
        step1Request.setPersonaCTNEWptpatrTieneEPSSanitas(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaTieneEPSSanitas());
        step1Request.setPersonaCTNEWptpatrRechazadoAfiliacion(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaRechazadoAfiliacion());
        step1Request.setPersonaCTNEWptpatrNombreCompleto(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonadrvNombreCompleto());
        step1Request.setPersonaCTNEWptpatrCodigo(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaCodigo());
        instance = (PersonaC) personaCSrv.tNEWImplementation(step1Request).getInstance();

        /* Action 2: IF (ptSolicitud.ContratantePersona.TipoIdentificacion.NIT = false) */
        boolean ifCondition2 = personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getTipoIdentificacion().getTipoIdentificacionNIT().equals(false);
        if (ifCondition2) {
            for(Direccion step220Entity : repository.assocOperator022(personaCTNEWCONTRADESDEAEptSolicitud)) {
                com.keralty.aeenviodoc.viewmodel.direccionc.siu.TCREARRequest step20Request = new com.keralty.aeenviodoc.viewmodel.direccionc.siu.TCREARRequest();
                step20Request.setDireccionCTCREARpagrDepartamentoCInstance(step220Entity.getDepartamento());
                step20Request.setDireccionCTCREARpagrMunicipioCInstance(step220Entity.getMunicipio());
                step20Request.setDireccionCTCREARpagrTipoDireccionCInstance(step220Entity.getTipoDireccion());
                step20Request.setDireccionCTCREARpagrPaisCInstance(step220Entity.getPais());
                step20Request.setDireccionCTCREARpagrPersonaCInstance(instance);
                step20Request.setDireccionCTCREARpatrDireccionCompleta(step220Entity.getDireccionDireccionCompleta());
                step20Request.setDireccionCTCREARpatrLocalidad(null);
                step20Request.setDireccionCTCREARpTelefonoFijo(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaTelefonoFijo());
                step20Request.setDireccionCTCREARpTelefonoCelular(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaTelefonoCelular());
                step20Request.setDireccionCTCREARpagrContratoCInstance(null);
                direccionCSrv.tCREARImplementation(step20Request);
            }
        } else {
            for(Direccion step221Entity : repository.assocOperator023(personaCTNEWCONTRADESDEAEptSolicitud)) {
                com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.TCREARRequest step21Request = new com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.TCREARRequest();
                step21Request.setDirSucursalCTCREARpagrMunicipioCInstance(step221Entity.getMunicipio());
                step21Request.setDirSucursalCTCREARpagrDepartamentoCInstance(step221Entity.getDepartamento());
                step21Request.setDirSucursalCTCREARpagrPaisCInstance(step221Entity.getPais());
                step21Request.setDirSucursalCTCREARpagrPersonaCInstance(instance);
                step21Request.setDirSucursalCTCREARpagrRegionalCInstance(null);
                step21Request.setDirSucursalCTCREARpatrCodLegalSucursal("1");
                step21Request.setDirSucursalCTCREARpatrNombre(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaRazonSocial());
                step21Request.setDirSucursalCTCREARpatrEsPrincipal(Boolean.TRUE);
                step21Request.setDirSucursalCTCREARpatrActiva(Boolean.TRUE);
                step21Request.setDirSucursalCTCREARpatrDireccion(step221Entity.getDireccionDireccionCompleta());
                step21Request.setDirSucursalCTCREARpatrMail(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaCorreoElectronico());
                step21Request.setDirSucursalCTCREARpTelefonoFijo(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaTelefonoFijo());
                step21Request.setDirSucursalCTCREARpTelefonoCelular(personaCTNEWCONTRADESDEAEptSolicitud.getContratantePersona().getPersonaTelefonoCelular());
                dirSucursalCSrv.tCREARImplementation(step21Request);
            }
        }

        /* Action 3: IF (EXIST (ptSolicitud.ProteccionDatos) = true) */
        boolean ifCondition3 = repository.assocOperator028(personaCTNEWCONTRADESDEAEptSolicitud).equals(true);
        if (ifCondition3) {
            com.keralty.aeenviodoc.viewmodel.protecciondatosc.siu.CrearRequest step30Request = new com.keralty.aeenviodoc.viewmodel.protecciondatosc.siu.CrearRequest();
            step30Request.setProteccionDatosCcrearpagrProductoCInstance(personaCTNEWCONTRADESDEAEptSolicitud.getAesolicitudes().getProductoT());
            step30Request.setProteccionDatosCcrearpagrPersonaCInstance(instance);
            step30Request.setProteccionDatosCcrearpatrCodRol(personaCTNEWCONTRADESDEAEptSolicitud.getProteccionDatos().getProteccionDatosRol());
            step30Request.setProteccionDatosCcrearpatrInformacionSaludSMS(personaCTNEWCONTRADESDEAEptSolicitud.getProteccionDatos().getProteccionDatosSaludSMS());
            step30Request.setProteccionDatosCcrearpatrInformacionSaludEmail(personaCTNEWCONTRADESDEAEptSolicitud.getProteccionDatos().getProteccionDatosSaludEmail());
            step30Request.setProteccionDatosCcrearpatrIinformacionSaludCorreo(personaCTNEWCONTRADESDEAEptSolicitud.getProteccionDatos().getProteccionDatosSaludCorreo());
            step30Request.setProteccionDatosCcrearpatrInformacionPublicidadSMS(personaCTNEWCONTRADESDEAEptSolicitud.getProteccionDatos().getProteccionDatosPublicidadSMS());
            step30Request.setProteccionDatosCcrearpatrInformacionPublicidadEmai(personaCTNEWCONTRADESDEAEptSolicitud.getProteccionDatos().getProteccionDatosPublicidadEmail());
            step30Request.setProteccionDatosCcrearpatrInformacionPublicidadCorr(personaCTNEWCONTRADESDEAEptSolicitud.getProteccionDatos().getProteccionDatosPublicidadCorreo());
            step30Request.setProteccionDatosCcrearpatrInformacionTerceros(personaCTNEWCONTRADESDEAEptSolicitud.getProteccionDatos().getProteccionDatosInfoTerceros());
            step30Request.setProteccionDatosCcrearpatrRol(null);
            step30Request.setProteccionDatosCcrearpatrCodigo(Long.valueOf(1));
            proteccionDatosCSrv.crearImplementation(step30Request);
        }

        for(Usuario step4Entity : repository.assocOperator025(personaCTNEWCONTRADESDEAEptSolicitud)) {
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCREAROCUESDEAERequest step4Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TCREAROCUESDEAERequest();
            step4Request.setPersonaCTCREAROCUESDEAEpthisPersonaCInstance(instance);
            step4Request.setPersonaCTCREAROCUESDEAEpCuestionarioPInstance(step4Entity.getCuestionarioP());
            personaCSrv.tCREAROCUESDEAEImplementation(step4Request);
        }

        for(Usuario step5Entity : repository.assocOperator027(personaCTNEWCONTRADESDEAEptSolicitud)) {
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCOPIARPREEXDEUSURequest step5Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TCOPIARPREEXDEUSURequest();
            step5Request.setPersonaCTCOPIARPREEXDEUSUpthisPersonaCInstance(instance);
            step5Request.setPersonaCTCOPIARPREEXDEUSUpUsuarioInstance(step5Entity);
            personaCSrv.tCOPIARPREEXDEUSUImplementation(step5Request);
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TNEWTITULARDESDEAE

    /*
     * Entry point for service TNEWTITULARDESDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tNEWTITULARDESDEAE(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWTITULARDESDEAERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.tNEWTITULARDESDEAETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TNEWTITULARDESDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tNEWTITULARDESDEAETxn(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWTITULARDESDEAERequest request) throws ModelException, SystemException {
        request.setPersonaCTNEWTITULARDESDEAEptSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getPersonaCTNEWTITULARDESDEAEptSolicitud()));
        request.setPersonaCTNEWTITULARDESDEAEptFamiliaCInstance(familiaCSrv.getByOIDWithHV(request.getPersonaCTNEWTITULARDESDEAEptFamiliaC()));
        return personaCSrv.tNEWTITULARDESDEAEImplementation(request);
    }

    /*
     * Implements service TNEWTITULARDESDEAE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tNEWTITULARDESDEAEImplementation(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWTITULARDESDEAERequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Solicitud personaCTNEWTITULARDESDEAEptSolicitud = request.getPersonaCTNEWTITULARDESDEAEptSolicitudInstance();
        FamiliaC personaCTNEWTITULARDESDEAEptFamiliaC = request.getPersonaCTNEWTITULARDESDEAEptFamiliaCInstance();

        request.checkArguments();
        PersonaC instance;

        com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWRequest step1Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWRequest();
        step1Request.setPersonaCTNEWptpagrTipoInstitucionCInstance(null);
        step1Request.setPersonaCTNEWptpagrTipoEntidadCInstance(null);
        step1Request.setPersonaCTNEWptpagrEstadoCivilCInstance(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getEstadoCivil());
        step1Request.setPersonaCTNEWptpagrEstadoPersonaCInstance(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getEstadoPersona());
        step1Request.setPersonaCTNEWptpagrTipoIdentificacionCInstance(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getTipoIdentificacion());
        step1Request.setPersonaCTNEWptpagrMunicipioCNacInstance(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getMunicipioNacimiento());
        step1Request.setPersonaCTNEWptpagrDepartamentoCNacInstance(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getDepartamentoNacimiento());
        step1Request.setPersonaCTNEWptpagrContratosCInstance(null);
        step1Request.setPersonaCTNEWptpagrFamiliasCInstance(personaCTNEWTITULARDESDEAEptFamiliaC);
        step1Request.setPersonaCTNEWptpagrBeneficiariosCInstance(null);
        step1Request.setPersonaCTNEWptpatrNumIdentificacion(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaNumIdentificacion());
        step1Request.setPersonaCTNEWptpatrMail(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaCorreoElectronico());
        step1Request.setPersonaCTNEWptpatrPrimerNombre(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaPrimerNombre());
        step1Request.setPersonaCTNEWptpatrSegundoNombre(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaSegundoNombre());
        step1Request.setPersonaCTNEWptpatrPrimerApellido(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaPrimerApellido());
        step1Request.setPersonaCTNEWptpatrSegundoApellido(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaSegundoApellido());
        step1Request.setPersonaCTNEWptpatrFechaNacimiento(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaFechaNacimiento());
        step1Request.setPersonaCTNEWptpatrGenero(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaGenero());
        step1Request.setPersonaCTNEWptpatrIndicadorExtranjero(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaIndicadorExtranjero());
        step1Request.setPersonaCTNEWptpatrRazonSocial(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaRazonSocial());
        step1Request.setPersonaCTNEWptpatrTieneEPSSanitas(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaTieneEPSSanitas());
        step1Request.setPersonaCTNEWptpatrRechazadoAfiliacion(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaRechazadoAfiliacion());
        step1Request.setPersonaCTNEWptpatrNombreCompleto(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonadrvNombreCompleto());
        step1Request.setPersonaCTNEWptpatrCodigo(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaCodigo());
        instance = (PersonaC) personaCSrv.tNEWImplementation(step1Request).getInstance();

        /* Action 2: IF (ptSolicitud.TitularPersona.TipoIdentificacion.NIT = false) */
        boolean ifCondition2 = personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getTipoIdentificacion().getTipoIdentificacionNIT().equals(false);
        if (ifCondition2) {
            for(Direccion step220Entity : repository.assocOperator029(personaCTNEWTITULARDESDEAEptSolicitud)) {
                com.keralty.aeenviodoc.viewmodel.direccionc.siu.TCREARRequest step20Request = new com.keralty.aeenviodoc.viewmodel.direccionc.siu.TCREARRequest();
                step20Request.setDireccionCTCREARpagrDepartamentoCInstance(step220Entity.getDepartamento());
                step20Request.setDireccionCTCREARpagrMunicipioCInstance(step220Entity.getMunicipio());
                step20Request.setDireccionCTCREARpagrTipoDireccionCInstance(step220Entity.getTipoDireccion());
                step20Request.setDireccionCTCREARpagrPaisCInstance(step220Entity.getPais());
                step20Request.setDireccionCTCREARpagrPersonaCInstance(instance);
                step20Request.setDireccionCTCREARpatrDireccionCompleta(step220Entity.getDireccionDireccionCompleta());
                step20Request.setDireccionCTCREARpatrLocalidad(null);
                step20Request.setDireccionCTCREARpTelefonoFijo(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaTelefonoFijo());
                step20Request.setDireccionCTCREARpTelefonoCelular(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaTelefonoCelular());
                step20Request.setDireccionCTCREARpagrContratoCInstance(null);
                direccionCSrv.tCREARImplementation(step20Request);
            }
        } else {
            for(Direccion step221Entity : repository.assocOperator030(personaCTNEWTITULARDESDEAEptSolicitud)) {
                com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.TCREARRequest step21Request = new com.keralty.aeenviodoc.viewmodel.dirsucursalc.siu.TCREARRequest();
                step21Request.setDirSucursalCTCREARpagrMunicipioCInstance(step221Entity.getMunicipio());
                step21Request.setDirSucursalCTCREARpagrDepartamentoCInstance(step221Entity.getDepartamento());
                step21Request.setDirSucursalCTCREARpagrPaisCInstance(step221Entity.getPais());
                step21Request.setDirSucursalCTCREARpagrPersonaCInstance(instance);
                step21Request.setDirSucursalCTCREARpagrRegionalCInstance(null);
                step21Request.setDirSucursalCTCREARpatrCodLegalSucursal("1");
                step21Request.setDirSucursalCTCREARpatrNombre(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaRazonSocial());
                step21Request.setDirSucursalCTCREARpatrEsPrincipal(Boolean.TRUE);
                step21Request.setDirSucursalCTCREARpatrActiva(Boolean.TRUE);
                step21Request.setDirSucursalCTCREARpatrDireccion(step221Entity.getDireccionDireccionCompleta());
                step21Request.setDirSucursalCTCREARpatrMail(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaCorreoElectronico());
                step21Request.setDirSucursalCTCREARpTelefonoFijo(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaTelefonoFijo());
                step21Request.setDirSucursalCTCREARpTelefonoCelular(personaCTNEWTITULARDESDEAEptSolicitud.getTitularPersona().getPersonaTelefonoCelular());
                dirSucursalCSrv.tCREARImplementation(step21Request);
            }
        }

        /* Action 3: IF (EXIST (ptSolicitud.ProteccionDatos) = true) */
        boolean ifCondition3 = repository.assocOperator035(personaCTNEWTITULARDESDEAEptSolicitud).equals(true);
        if (ifCondition3) {
            com.keralty.aeenviodoc.viewmodel.protecciondatosc.siu.CrearRequest step30Request = new com.keralty.aeenviodoc.viewmodel.protecciondatosc.siu.CrearRequest();
            step30Request.setProteccionDatosCcrearpagrProductoCInstance(personaCTNEWTITULARDESDEAEptSolicitud.getAesolicitudes().getProductoT());
            step30Request.setProteccionDatosCcrearpagrPersonaCInstance(instance);
            step30Request.setProteccionDatosCcrearpatrCodRol(personaCTNEWTITULARDESDEAEptSolicitud.getProteccionDatos().getProteccionDatosRol());
            step30Request.setProteccionDatosCcrearpatrInformacionSaludSMS(personaCTNEWTITULARDESDEAEptSolicitud.getProteccionDatos().getProteccionDatosSaludSMS());
            step30Request.setProteccionDatosCcrearpatrInformacionSaludEmail(personaCTNEWTITULARDESDEAEptSolicitud.getProteccionDatos().getProteccionDatosSaludEmail());
            step30Request.setProteccionDatosCcrearpatrIinformacionSaludCorreo(personaCTNEWTITULARDESDEAEptSolicitud.getProteccionDatos().getProteccionDatosSaludCorreo());
            step30Request.setProteccionDatosCcrearpatrInformacionPublicidadSMS(personaCTNEWTITULARDESDEAEptSolicitud.getProteccionDatos().getProteccionDatosPublicidadSMS());
            step30Request.setProteccionDatosCcrearpatrInformacionPublicidadEmai(personaCTNEWTITULARDESDEAEptSolicitud.getProteccionDatos().getProteccionDatosPublicidadEmail());
            step30Request.setProteccionDatosCcrearpatrInformacionPublicidadCorr(personaCTNEWTITULARDESDEAEptSolicitud.getProteccionDatos().getProteccionDatosPublicidadCorreo());
            step30Request.setProteccionDatosCcrearpatrInformacionTerceros(personaCTNEWTITULARDESDEAEptSolicitud.getProteccionDatos().getProteccionDatosInfoTerceros());
            step30Request.setProteccionDatosCcrearpatrRol(null);
            step30Request.setProteccionDatosCcrearpatrCodigo(Long.valueOf(1));
            proteccionDatosCSrv.crearImplementation(step30Request);
        }

        for(Usuario step4Entity : repository.assocOperator032(personaCTNEWTITULARDESDEAEptSolicitud)) {
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCREAROCUESDEAERequest step4Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TCREAROCUESDEAERequest();
            step4Request.setPersonaCTCREAROCUESDEAEpthisPersonaCInstance(instance);
            step4Request.setPersonaCTCREAROCUESDEAEpCuestionarioPInstance(step4Entity.getCuestionarioP());
            personaCSrv.tCREAROCUESDEAEImplementation(step4Request);
        }

        for(Usuario step5Entity : repository.assocOperator034(personaCTNEWTITULARDESDEAEptSolicitud)) {
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCOPIARPREEXDEUSURequest step5Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TCOPIARPREEXDEUSURequest();
            step5Request.setPersonaCTCOPIARPREEXDEUSUpthisPersonaCInstance(instance);
            step5Request.setPersonaCTCOPIARPREEXDEUSUpUsuarioInstance(step5Entity);
            personaCSrv.tCOPIARPREEXDEUSUImplementation(step5Request);
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TNEWBENEFDESDEAE

    /*
     * Entry point for service TNEWBENEFDESDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tNEWBENEFDESDEAE(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWBENEFDESDEAERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.tNEWBENEFDESDEAETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TNEWBENEFDESDEAE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tNEWBENEFDESDEAETxn(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWBENEFDESDEAERequest request) throws ModelException, SystemException {
        request.setPersonaCTNEWBENEFDESDEAEptBeneficiarioInstance(beneficiarioCSrv.getByOIDWithHV(request.getPersonaCTNEWBENEFDESDEAEptBeneficiario()));
        request.setPersonaCTNEWBENEFDESDEAEptUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getPersonaCTNEWBENEFDESDEAEptUsuario()));
        return personaCSrv.tNEWBENEFDESDEAEImplementation(request);
    }

    /*
     * Implements service TNEWBENEFDESDEAE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tNEWBENEFDESDEAEImplementation(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWBENEFDESDEAERequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Usuario personaCTNEWBENEFDESDEAEptUsuario = request.getPersonaCTNEWBENEFDESDEAEptUsuarioInstance();
        BeneficiarioC personaCTNEWBENEFDESDEAEptBeneficiario = request.getPersonaCTNEWBENEFDESDEAEptBeneficiarioInstance();

        request.checkArguments();
        PersonaC instance;

        com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWRequest step1Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWRequest();
        step1Request.setPersonaCTNEWptpagrTipoInstitucionCInstance(null);
        step1Request.setPersonaCTNEWptpagrTipoEntidadCInstance(null);
        step1Request.setPersonaCTNEWptpagrEstadoCivilCInstance(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getEstadoCivil());
        step1Request.setPersonaCTNEWptpagrEstadoPersonaCInstance(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getEstadoPersona());
        step1Request.setPersonaCTNEWptpagrTipoIdentificacionCInstance(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getTipoIdentificacion());
        step1Request.setPersonaCTNEWptpagrMunicipioCNacInstance(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getMunicipioNacimiento());
        step1Request.setPersonaCTNEWptpagrDepartamentoCNacInstance(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getDepartamentoNacimiento());
        step1Request.setPersonaCTNEWptpagrContratosCInstance(null);
        step1Request.setPersonaCTNEWptpagrFamiliasCInstance(null);
        step1Request.setPersonaCTNEWptpagrBeneficiariosCInstance(personaCTNEWBENEFDESDEAEptBeneficiario);
        step1Request.setPersonaCTNEWptpatrNumIdentificacion(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaNumIdentificacion());
        step1Request.setPersonaCTNEWptpatrMail(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaCorreoElectronico());
        step1Request.setPersonaCTNEWptpatrPrimerNombre(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaPrimerNombre());
        step1Request.setPersonaCTNEWptpatrSegundoNombre(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaSegundoNombre());
        step1Request.setPersonaCTNEWptpatrPrimerApellido(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaPrimerApellido());
        step1Request.setPersonaCTNEWptpatrSegundoApellido(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaSegundoApellido());
        step1Request.setPersonaCTNEWptpatrFechaNacimiento(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaFechaNacimiento());
        step1Request.setPersonaCTNEWptpatrGenero(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaGenero());
        step1Request.setPersonaCTNEWptpatrIndicadorExtranjero(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaIndicadorExtranjero());
        step1Request.setPersonaCTNEWptpatrRazonSocial(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaRazonSocial());
        step1Request.setPersonaCTNEWptpatrTieneEPSSanitas(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaTieneEPSSanitas());
        step1Request.setPersonaCTNEWptpatrRechazadoAfiliacion(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaRechazadoAfiliacion());
        step1Request.setPersonaCTNEWptpatrNombreCompleto(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonadrvNombreCompleto());
        step1Request.setPersonaCTNEWptpatrCodigo(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaCodigo());
        instance = (PersonaC) personaCSrv.tNEWImplementation(step1Request).getInstance();

        for(Direccion step2Entity : repository.assocOperator036(personaCTNEWBENEFDESDEAEptUsuario)) {
            com.keralty.aeenviodoc.viewmodel.direccionc.siu.TCREARRequest step2Request = new com.keralty.aeenviodoc.viewmodel.direccionc.siu.TCREARRequest();
            step2Request.setDireccionCTCREARpagrDepartamentoCInstance(step2Entity.getDepartamento());
            step2Request.setDireccionCTCREARpagrMunicipioCInstance(step2Entity.getMunicipio());
            step2Request.setDireccionCTCREARpagrTipoDireccionCInstance(step2Entity.getTipoDireccion());
            step2Request.setDireccionCTCREARpagrPaisCInstance(step2Entity.getPais());
            step2Request.setDireccionCTCREARpagrPersonaCInstance(instance);
            step2Request.setDireccionCTCREARpatrDireccionCompleta(step2Entity.getDireccionDireccionCompleta());
            step2Request.setDireccionCTCREARpatrLocalidad(null);
            step2Request.setDireccionCTCREARpTelefonoFijo(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaTelefonoFijo());
            step2Request.setDireccionCTCREARpTelefonoCelular(personaCTNEWBENEFDESDEAEptUsuario.getUsuarioPersona().getPersonaTelefonoCelular());
            step2Request.setDireccionCTCREARpagrContratoCInstance(null);
            direccionCSrv.tCREARImplementation(step2Request);
        }

        /* Action 3: IF (EXIST (ptUsuario.Solicitud.ProteccionDatos) = true) */
        boolean ifCondition3 = repository.assocOperator039(personaCTNEWBENEFDESDEAEptUsuario).equals(true);
        if (ifCondition3) {
            com.keralty.aeenviodoc.viewmodel.protecciondatosc.siu.CrearRequest step30Request = new com.keralty.aeenviodoc.viewmodel.protecciondatosc.siu.CrearRequest();
            step30Request.setProteccionDatosCcrearpagrProductoCInstance(personaCTNEWBENEFDESDEAEptUsuario.getSolicitud().getAesolicitudes().getProductoT());
            step30Request.setProteccionDatosCcrearpagrPersonaCInstance(instance);
            step30Request.setProteccionDatosCcrearpatrCodRol(personaCTNEWBENEFDESDEAEptUsuario.getSolicitud().getProteccionDatos().getProteccionDatosRol());
            step30Request.setProteccionDatosCcrearpatrInformacionSaludSMS(personaCTNEWBENEFDESDEAEptUsuario.getSolicitud().getProteccionDatos().getProteccionDatosSaludSMS());
            step30Request.setProteccionDatosCcrearpatrInformacionSaludEmail(personaCTNEWBENEFDESDEAEptUsuario.getSolicitud().getProteccionDatos().getProteccionDatosSaludEmail());
            step30Request.setProteccionDatosCcrearpatrIinformacionSaludCorreo(personaCTNEWBENEFDESDEAEptUsuario.getSolicitud().getProteccionDatos().getProteccionDatosSaludCorreo());
            step30Request.setProteccionDatosCcrearpatrInformacionPublicidadSMS(personaCTNEWBENEFDESDEAEptUsuario.getSolicitud().getProteccionDatos().getProteccionDatosPublicidadSMS());
            step30Request.setProteccionDatosCcrearpatrInformacionPublicidadEmai(personaCTNEWBENEFDESDEAEptUsuario.getSolicitud().getProteccionDatos().getProteccionDatosPublicidadEmail());
            step30Request.setProteccionDatosCcrearpatrInformacionPublicidadCorr(personaCTNEWBENEFDESDEAEptUsuario.getSolicitud().getProteccionDatos().getProteccionDatosPublicidadCorreo());
            step30Request.setProteccionDatosCcrearpatrInformacionTerceros(personaCTNEWBENEFDESDEAEptUsuario.getSolicitud().getProteccionDatos().getProteccionDatosInfoTerceros());
            step30Request.setProteccionDatosCcrearpatrRol(null);
            step30Request.setProteccionDatosCcrearpatrCodigo(Long.valueOf(1));
            proteccionDatosCSrv.crearImplementation(step30Request);
        }

        for(CuestionarioP step4Entity : repository.assocOperator038(personaCTNEWBENEFDESDEAEptUsuario)) {
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCREAROCUESDEAERequest step4Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.TCREAROCUESDEAERequest();
            step4Request.setPersonaCTCREAROCUESDEAEpthisPersonaCInstance(instance);
            step4Request.setPersonaCTCREAROCUESDEAEpCuestionarioPInstance(step4Entity);
            personaCSrv.tCREAROCUESDEAEImplementation(step4Request);
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TNEW

    /*
     * Entry point for service TNEW
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tNEW(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.tNEWTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TNEW
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tNEWTxn(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWRequest request) throws ModelException {
        request.setPersonaCTNEWptpagrTipoInstitucionCInstance(tipoInstitucionCSrv.getByOIDWithHV(request.getPersonaCTNEWptpagrTipoInstitucionC()));
        request.setPersonaCTNEWptpagrTipoEntidadCInstance(tipoEntidadCSrv.getByOIDWithHV(request.getPersonaCTNEWptpagrTipoEntidadC()));
        request.setPersonaCTNEWptpagrEstadoCivilCInstance(estadoCivilSrv.getByOIDWithHV(request.getPersonaCTNEWptpagrEstadoCivilC()));
        request.setPersonaCTNEWptpagrEstadoPersonaCInstance(estadoPersonaSrv.getByOIDWithHV(request.getPersonaCTNEWptpagrEstadoPersonaC()));
        request.setPersonaCTNEWptpagrTipoIdentificacionCInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getPersonaCTNEWptpagrTipoIdentificacionC()));
        request.setPersonaCTNEWptpagrMunicipioCNacInstance(municipioSrv.getByOIDWithHV(request.getPersonaCTNEWptpagrMunicipioCNac()));
        request.setPersonaCTNEWptpagrDepartamentoCNacInstance(departamentoSrv.getByOIDWithHV(request.getPersonaCTNEWptpagrDepartamentoCNac()));
        request.setPersonaCTNEWptpagrContratosCInstance(contratoCSrv.getByOIDWithHV(request.getPersonaCTNEWptpagrContratosC()));
        request.setPersonaCTNEWptpagrFamiliasCInstance(familiaCSrv.getByOIDWithHV(request.getPersonaCTNEWptpagrFamiliasC()));
        request.setPersonaCTNEWptpagrBeneficiariosCInstance(beneficiarioCSrv.getByOIDWithHV(request.getPersonaCTNEWptpagrBeneficiariosC()));
        return personaCSrv.tNEWImplementation(request);
    }

    /*
     * Implements service TNEW
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tNEWImplementation(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TNEWRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoInstitucionC personaCTNEWptpagrTipoInstitucionC = request.getPersonaCTNEWptpagrTipoInstitucionCInstance();
        TipoEntidadC personaCTNEWptpagrTipoEntidadC = request.getPersonaCTNEWptpagrTipoEntidadCInstance();
        EstadoCivil personaCTNEWptpagrEstadoCivilC = request.getPersonaCTNEWptpagrEstadoCivilCInstance();
        EstadoPersona personaCTNEWptpagrEstadoPersonaC = request.getPersonaCTNEWptpagrEstadoPersonaCInstance();
        TipoIdentificacion personaCTNEWptpagrTipoIdentificacionC = request.getPersonaCTNEWptpagrTipoIdentificacionCInstance();
        Municipio personaCTNEWptpagrMunicipioCNac = request.getPersonaCTNEWptpagrMunicipioCNacInstance();
        Departamento personaCTNEWptpagrDepartamentoCNac = request.getPersonaCTNEWptpagrDepartamentoCNacInstance();
        ContratoC personaCTNEWptpagrContratosC = request.getPersonaCTNEWptpagrContratosCInstance();
        FamiliaC personaCTNEWptpagrFamiliasC = request.getPersonaCTNEWptpagrFamiliasCInstance();
        BeneficiarioC personaCTNEWptpagrBeneficiariosC = request.getPersonaCTNEWptpagrBeneficiariosCInstance();
        String personaCTNEWptpatrNumIdentificacion = request.getPersonaCTNEWptpatrNumIdentificacion();
        String personaCTNEWptpatrMail = request.getPersonaCTNEWptpatrMail();
        String personaCTNEWptpatrPrimerNombre = request.getPersonaCTNEWptpatrPrimerNombre();
        String personaCTNEWptpatrSegundoNombre = request.getPersonaCTNEWptpatrSegundoNombre();
        String personaCTNEWptpatrPrimerApellido = request.getPersonaCTNEWptpatrPrimerApellido();
        String personaCTNEWptpatrSegundoApellido = request.getPersonaCTNEWptpatrSegundoApellido();
        Date personaCTNEWptpatrFechaNacimiento = request.getPersonaCTNEWptpatrFechaNacimiento();
        String personaCTNEWptpatrGenero = request.getPersonaCTNEWptpatrGenero();
        Boolean personaCTNEWptpatrIndicadorExtranjero = request.getPersonaCTNEWptpatrIndicadorExtranjero();
        String personaCTNEWptpatrRazonSocial = request.getPersonaCTNEWptpatrRazonSocial();
        Boolean personaCTNEWptpatrTieneEPSSanitas = request.getPersonaCTNEWptpatrTieneEPSSanitas();
        String personaCTNEWptpatrRechazadoAfiliacion = request.getPersonaCTNEWptpatrRechazadoAfiliacion();
        String personaCTNEWptpatrNombreCompleto = request.getPersonaCTNEWptpatrNombreCompleto();
        Long personaCTNEWptpatrCodigo = request.getPersonaCTNEWptpatrCodigo();

        request.checkArguments();
        PersonaC instance;

        com.keralty.aeenviodoc.viewmodel.personac.siu.CrearRequest step1Request = new com.keralty.aeenviodoc.viewmodel.personac.siu.CrearRequest();
        step1Request.setPersonaCcrearpagrTipoInstitucionCInstance(personaCTNEWptpagrTipoInstitucionC);
        step1Request.setPersonaCcrearpagrTipoEntidadCInstance(personaCTNEWptpagrTipoEntidadC);
        step1Request.setPersonaCcrearpagrEstadoCivilCInstance(personaCTNEWptpagrEstadoCivilC);
        step1Request.setPersonaCcrearpagrEstadoPersonaCInstance(personaCTNEWptpagrEstadoPersonaC);
        step1Request.setPersonaCcrearpagrTipoIdentificacionCInstance(personaCTNEWptpagrTipoIdentificacionC);
        step1Request.setPersonaCcrearpagrMunicipioCNacInstance(personaCTNEWptpagrMunicipioCNac);
        step1Request.setPersonaCcrearpagrDepartamentoCNacInstance(personaCTNEWptpagrDepartamentoCNac);
        step1Request.setPersonaCcrearpagrContratosCInstance(personaCTNEWptpagrContratosC);
        step1Request.setPersonaCcrearpagrFamiliasCInstance(personaCTNEWptpagrFamiliasC);
        step1Request.setPersonaCcrearpagrBeneficiariosCInstance(personaCTNEWptpagrBeneficiariosC);
        step1Request.setPersonaCcrearpatrNumIdentificacion(personaCTNEWptpatrNumIdentificacion);
        step1Request.setPersonaCcrearpatrMail(personaCTNEWptpatrMail);
        step1Request.setPersonaCcrearpatrPrimerNombre(personaCTNEWptpatrPrimerNombre);
        step1Request.setPersonaCcrearpatrSegundoNombre(personaCTNEWptpatrSegundoNombre);
        step1Request.setPersonaCcrearpatrPrimerApellido(personaCTNEWptpatrPrimerApellido);
        step1Request.setPersonaCcrearpatrSegundoApellido(personaCTNEWptpatrSegundoApellido);
        step1Request.setPersonaCcrearpatrFechaNacimiento(personaCTNEWptpatrFechaNacimiento);
        step1Request.setPersonaCcrearpatrGenero(personaCTNEWptpatrGenero);
        step1Request.setPersonaCcrearpatrIndicadorExtranjero(personaCTNEWptpatrIndicadorExtranjero);
        step1Request.setPersonaCcrearpatrRazonSocial(personaCTNEWptpatrRazonSocial);
        step1Request.setPersonaCcrearpatrTieneEPSSanitas(personaCTNEWptpatrTieneEPSSanitas);
        step1Request.setPersonaCcrearpatrRechazadoAfiliacion(personaCTNEWptpatrRechazadoAfiliacion);
        step1Request.setPersonaCcrearpatrNombreCompleto(personaCTNEWptpatrNombreCompleto);
        step1Request.setPersonaCcrearpatrCodigo(personaCTNEWptpatrCodigo);
        step1Request.setPersonaCcrearpatrUltimaActualizacion(STDFunctions.systemDateTimeFun());
        instance = (PersonaC) personaCSrv.crearImplementation(step1Request).getInstance();

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TCOPIARPREEXDEUSU

    /*
     * Entry point for service TCOPIARPREEXDEUSU
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCOPIARPREEXDEUSU(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCOPIARPREEXDEUSURequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.tCOPIARPREEXDEUSUTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCOPIARPREEXDEUSU
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCOPIARPREEXDEUSUTxn(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCOPIARPREEXDEUSURequest request) throws ModelException, SystemException {
        request.setPersonaCTCOPIARPREEXDEUSUpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCTCOPIARPREEXDEUSUpthisPersonaC()));
        request.setPersonaCTCOPIARPREEXDEUSUpUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getPersonaCTCOPIARPREEXDEUSUpUsuario()));
        return personaCSrv.tCOPIARPREEXDEUSUImplementation(request);
    }

    /*
     * Implements service TCOPIARPREEXDEUSU
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCOPIARPREEXDEUSUImplementation(
            com.keralty.aeenviodoc.viewmodel.personac.siu.TCOPIARPREEXDEUSURequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Usuario personaCTCOPIARPREEXDEUSUpUsuario = request.getPersonaCTCOPIARPREEXDEUSUpUsuarioInstance();
        PersonaC personaCTCOPIARPREEXDEUSUpthisPersonaC = request.getPersonaCTCOPIARPREEXDEUSUpthisPersonaCInstance();

        request.checkArguments();
        PersonaC instance = personaCTCOPIARPREEXDEUSUpthisPersonaC;

        for(Preexistencia step1Entity : repository.assocOperator040(personaCTCOPIARPREEXDEUSUpthisPersonaC, personaCTCOPIARPREEXDEUSUpUsuario)) {
            com.keralty.aeenviodoc.viewmodel.preexistenciac.siu.CrearRequest step1Request = new com.keralty.aeenviodoc.viewmodel.preexistenciac.siu.CrearRequest();
            step1Request.setPreexistenciaCcrearpagrPersonaCInstance(personaCTCOPIARPREEXDEUSUpthisPersonaC);
            step1Request.setPreexistenciaCcrearpatrDescripcion(step1Entity.getPreexistenciaDescripcion());
            step1Request.setPreexistenciaCcrearpatrFecInicio(step1Entity.getPreexistenciaFecInicio());
            step1Request.setPreexistenciaCcrearpatrFecFin(step1Entity.getPreexistenciaFecFin());
            step1Request.setPreexistenciaCcrearpatrObservacion(step1Entity.getPreexistenciaObservacion());
            preexistenciaCSrv.crearImplementation(step1Request);
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
        select.getOrderByPart().addItem(Constants.normalizePath(PersonaCConstants.CLASS_NAME, PersonaCConstants.ATTR_FIELD_PERSONACID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildPersonaCRelatedInstance(relatedInstanceJsonOid);
        }
        if (PersonaCConstants.ROLE_NAME_DIRECCIONESC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4DireccionC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_PROTECCIONESDATOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4ProteccionDatosC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_PREGSCUESMEDC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4PregCuesMedC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_DIRSUCURSALESC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4DirSucursalC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_PREEXISTENCIASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4PreexistenciaC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_TIPOINSTITUCIONC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4TipoInstitucionC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_TIPOENTIDADC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4TipoEntidadC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_ESTADOCIVILC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4EstadoCivil(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_ESTADOPERSONAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4EstadoPersona(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_TIPOIDENTIFICACIONC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_MUNICIPIOCNAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_DEPARTAMENTOCNAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4Departamento(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_CONTRATOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4ContratoC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_FAMILIASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4FamiliaC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_BENEFICIARIOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4BeneficiarioC(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildPersonaCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4DireccionC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DireccionC(mapper.readValue(relatedInstanceJsonOid, DireccionCOid.class)): DireccionC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4ProteccionDatosC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ProteccionDatosC(mapper.readValue(relatedInstanceJsonOid, ProteccionDatosCOid.class)): ProteccionDatosC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4PregCuesMedC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PregCuesMedC(mapper.readValue(relatedInstanceJsonOid, PregCuesMedCOid.class)): PregCuesMedC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4DirSucursalC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DirSucursalC(mapper.readValue(relatedInstanceJsonOid, DirSucursalCOid.class)): DirSucursalC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4PreexistenciaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PreexistenciaC(mapper.readValue(relatedInstanceJsonOid, PreexistenciaCOid.class)): PreexistenciaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4TipoInstitucionC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoInstitucionC(mapper.readValue(relatedInstanceJsonOid, TipoInstitucionCOid.class)): TipoInstitucionC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4TipoEntidadC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoEntidadC(mapper.readValue(relatedInstanceJsonOid, TipoEntidadCOid.class)): TipoEntidadC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4EstadoCivil(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoCivil(mapper.readValue(relatedInstanceJsonOid, EstadoCivilOid.class)): EstadoCivil.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4EstadoPersona(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoPersona(mapper.readValue(relatedInstanceJsonOid, EstadoPersonaOid.class)): EstadoPersona.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4Departamento(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Departamento(mapper.readValue(relatedInstanceJsonOid, DepartamentoOid.class)): Departamento.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4ContratoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class)): ContratoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4FamiliaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FamiliaC(mapper.readValue(relatedInstanceJsonOid, FamiliaCOid.class)): FamiliaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4BeneficiarioC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new BeneficiarioC(mapper.readValue(relatedInstanceJsonOid, BeneficiarioCOid.class)): BeneficiarioC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            PersonaCOid oid = new PersonaCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PersonaCConstants.ATTR_FIELD_PERSONACID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getPersonaCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(PersonaCService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
