package com.keralty.aeenviodoc.api.solicitud;

import com.keralty.aeenviodoc.persistence.AbstractAppEntity;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.service.SolicitudService;
import com.keralty.aeenviodoc.utils.TokenUtilities;
import com.keralty.aeenviodoc.viewmodel.InstanceResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.PopulationRequestViewModel;
import com.keralty.aeenviodoc.viewmodel.PopulationResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.PreloadResponseViewModel;
import com.keralty.aeenviodoc.viewmodel.PreloadViewModel;
import com.keralty.aeenviodoc.viewmodel.QueryRequest;
import com.keralty.aeenviodoc.viewmodel.ServiceResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudIIUDifLiq;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudIIUInclusion;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudIIUInfoContraConta;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudIIUInfoContratJUR;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudIIUInfoContratNAT;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudIIUInfoObservacion;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudIIUInfoPago;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudIIUNuevaAfil;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudIIUPreexistencias;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudInfoContratanteID;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudPIUStatusFull;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudPIUStatusFullAse;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.DSSolicitudVacia;
import com.keralty.aeenviodoc.viewmodel.solicitud.ds.ICSolicitud;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUConsultarRadicadoRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUConsultarRadicadoResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEnvioDocRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEnvioDocResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEstatusSolGestorMesaRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEstatusSolGestorMesaResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEstatusSolicitudesAdminRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEstatusSolicitudesAdminResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEstatusSolicitudesAfilRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEstatusSolicitudesAfilResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEstatusSolicitudesAreaMedRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEstatusSolicitudesAreaMedResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEstatusSolicitudesAsesorRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIUEstatusSolicitudesAsesorResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.piu.PIURadicadosObsResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.AnotarObservacionesRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.AsignarNumContratoRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.CalcularURLPasarelaPagosRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.CalcularURLPasarelaPagosResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.DelContratoCASolicitudRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.DummyDevolverErroresWSRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.DummyDevolverErroresWSResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.DummyDiferenciasLiqRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.DummyEstatusSolicitudesRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.DummyGenerarSolicitudRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.DummyPreexistenciasRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.DummyRedirigirRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.EnviarEmailURLPagoRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.EnviarSMSInfoPagoRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.InsContratoCASolicitudRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.InsFormaPagoSolicitudRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.InsPeriodicidadSolicitudRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.MarcarDatosPagoOKRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.MarcarNoCompletadaRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.ModFechaInicioContratacionRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.ModMarcaCarnetRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.SetCodTarifaRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TANULARAUTORequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCAMBIARESTADOADMINRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCAMBIARESTADODOCRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCAMBIARESTADOERRORRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCAMBIARESTADORequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCAMBIARESTANULADORequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCAMBIARESTAPROBMEDICORequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCAMBIARESTDEVAREAMEDRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCAMBIARESTDEVAREAMEDResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCAMBIARESTPENDMEDICORequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCHGTITBENEFCONTRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCREAROMODFIRMACONTINCRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCREAROMODFIRMARequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TCREARSEGUNDAFIRMARequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TENVIARDOCAUTOAUXRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TENVIARDOCRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TENVIARDOCResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TGETPLANTILLAREPORTERequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TGETPLANTILLAREPORTEResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TINDICARCONTRATANTE2Request;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TINDICARTITULAR2Request;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TMARCARCOMPLETADARequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TMARCARCOMPLETADAResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TMODDIRECCIONRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TMODDIRSCONTRATANTERequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TMODDIRSTITULARRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TMODENCABEZADOINCLRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TMODENCABEZADORequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TMODIFICARINFOPAGORequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TNOTIFICARANULACIONRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TNOTIFICARCREARRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TNOTIFICARINCLUSIONRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TPOSTGENERAREXITORequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TREASIGNARUSUARIOSRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TREASIGNARUSUARIOSResponse;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.TSUBIRDOCUMENTOATORequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.ValidarAtributosUsuarioRequest;
import com.keralty.aeenviodoc.viewmodel.solicitud.siu.ValidarAtributosUsuarioResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/SolicitudApi")
@RequestScoped
public class SolicitudApi {

    SolicitudService service;

    public SolicitudApi(SolicitudService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{aesolicitudesidsolicitud}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public Solicitud getInstance(Long aesolicitudesidsolicitud) {
    	return service.getById(aesolicitudesidsolicitud);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public List<Solicitud> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public List<PreloadViewModel> getSolicitudPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public Solicitud getByOid(SolicitudOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public Solicitud getByOIDWithHV(SolicitudOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetSolicitudDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public InstanceResponseViewModel getSolicitudDynamic(QueryRequest request) {
        SolicitudOid oid = new SolicitudOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getSolicitudDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PreloadResponseViewModel getSolicitudWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PopulationResponseViewModel getSolicitudPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/InsFormaPagoSolicitud")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudInsFormaPagoSolicitud(InsFormaPagoSolicitudRequest request) {
        return service.insFormaPagoSolicitud(request);
    }
    

    @POST
    @Path(value = "/InsPeriodicidadSolicitud")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudInsPeriodicidadSolicitud(InsPeriodicidadSolicitudRequest request) {
        return service.insPeriodicidadSolicitud(request);
    }
    

    @POST
    @Path(value = "/marcarDatosPagoOK")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudMarcarDatosPagoOK(MarcarDatosPagoOKRequest request) {
        return service.marcarDatosPagoOK(request);
    }
    

    @POST
    @Path(value = "/anotarObservaciones")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudAnotarObservaciones(AnotarObservacionesRequest request) {
        return service.anotarObservaciones(request);
    }
    

    @POST
    @Path(value = "/anotarObservacionesCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudAnotarObservacionesCheckPreconditions(AnotarObservacionesRequest request) {

        return service.anotarObservacionesCheckPreconditions(request);
    }

    @POST
    @Path(value = "/dummy_Preexistencias")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudDummyPreexistencias(DummyPreexistenciasRequest request) {
        return service.dummyPreexistencias(request);
    }
    

    @POST
    @Path(value = "/dummyPreexistenciasCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudDummyPreexistenciasCheckPreconditions(DummyPreexistenciasRequest request) {

        return service.dummyPreexistenciasCheckPreconditions(request);
    }

    @POST
    @Path(value = "/dummy_DiferenciasLiq")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudDummyDiferenciasLiq(DummyDiferenciasLiqRequest request) {
        return service.dummyDiferenciasLiq(request);
    }
    

    @POST
    @Path(value = "/dummyDiferenciasLiqCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudDummyDiferenciasLiqCheckPreconditions(DummyDiferenciasLiqRequest request) {

        return service.dummyDiferenciasLiqCheckPreconditions(request);
    }

    @POST
    @Path(value = "/dummy_Redirigir")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","MesaDeApoyo"})
    public ServiceResponse solicitudDummyRedirigir(DummyRedirigirRequest request) {
        return service.dummyRedirigir(request);
    }
    

    @POST
    @Path(value = "/validarAtributosUsuario")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ValidarAtributosUsuarioResponse solicitudValidarAtributosUsuario(ValidarAtributosUsuarioRequest request) {
        return service.validarAtributosUsuario(request);
    }
    

    @POST
    @Path(value = "/validarAtributosUsuarioCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ValidarAtributosUsuarioResponse solicitudValidarAtributosUsuarioCheckPreconditions(ValidarAtributosUsuarioRequest request) {

        return service.validarAtributosUsuarioCheckPreconditions(request);
    }

    @POST
    @Path(value = "/calcularURLPasarelaPagos")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public CalcularURLPasarelaPagosResponse solicitudCalcularURLPasarelaPagos(CalcularURLPasarelaPagosRequest request) {
        return service.calcularURLPasarelaPagos(request);
    }
    

    @POST
    @Path(value = "/enviarEmailURLPago")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudEnviarEmailURLPago(EnviarEmailURLPagoRequest request) {
        return service.enviarEmailURLPago(request);
    }
    

    @POST
    @Path(value = "/InsContratoCASolicitud")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudInsContratoCASolicitud(InsContratoCASolicitudRequest request) {
        return service.insContratoCASolicitud(request);
    }
    

    @POST
    @Path(value = "/DelContratoCASolicitud")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudDelContratoCASolicitud(DelContratoCASolicitudRequest request) {
        return service.delContratoCASolicitud(request);
    }
    

    @POST
    @Path(value = "/setCodTarifa")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudSetCodTarifa(SetCodTarifaRequest request) {
        return service.setCodTarifa(request);
    }
    

    @POST
    @Path(value = "/dummyDevolverErroresWS")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DummyDevolverErroresWSResponse solicitudDummyDevolverErroresWS(DummyDevolverErroresWSRequest request) {
        return service.dummyDevolverErroresWS(request);
    }
    

    @POST
    @Path(value = "/marcarNoCompletada")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudMarcarNoCompletada(MarcarNoCompletadaRequest request) {
        return service.marcarNoCompletada(request);
    }
    

    @POST
    @Path(value = "/modFechaInicioContratacion")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudModFechaInicioContratacion(ModFechaInicioContratacionRequest request) {
        return service.modFechaInicioContratacion(request);
    }
    

    @POST
    @Path(value = "/asignarNumContrato")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudAsignarNumContrato(AsignarNumContratoRequest request) {
        return service.asignarNumContrato(request);
    }
    

    @POST
    @Path(value = "/dummy_GenerarSolicitud")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","MesaDeApoyo"})
    public ServiceResponse solicitudDummyGenerarSolicitud(DummyGenerarSolicitudRequest request) {
        return service.dummyGenerarSolicitud(request);
    }
    

    @POST
    @Path(value = "/enviarSMSInfoPago")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudEnviarSMSInfoPago(EnviarSMSInfoPagoRequest request) {
        return service.enviarSMSInfoPago(request);
    }
    

    @POST
    @Path(value = "/dummy_EstatusSolicitudes")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","MesaDeApoyo"})
    public ServiceResponse solicitudDummyEstatusSolicitudes(DummyEstatusSolicitudesRequest request) {
        return service.dummyEstatusSolicitudes(request);
    }
    

    @POST
    @Path(value = "/modMarcaCarnet")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudModMarcaCarnet(ModMarcaCarnetRequest request) {
        return service.modMarcaCarnet(request);
    }
    

    @POST
    @Path(value = "/modMarcaCarnetCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudModMarcaCarnetCheckPreconditions(ModMarcaCarnetRequest request) {

        return service.modMarcaCarnetCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TMODDIRECCION")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODDIRECCION(TMODDIRECCIONRequest request) {
        return service.tMODDIRECCION(request);
    }
    

    @POST
    @Path(value = "/TMODDIRECCIONCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODDIRECCIONCheckPreconditions(TMODDIRECCIONRequest request) {

        return service.tMODDIRECCIONCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TMODIFICARINFOPAGO")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODIFICARINFOPAGO(TMODIFICARINFOPAGORequest request) {
        return service.tMODIFICARINFOPAGO(request);
    }
    

    @POST
    @Path(value = "/TMODIFICARINFOPAGOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODIFICARINFOPAGOCheckPreconditions(TMODIFICARINFOPAGORequest request) {

        return service.tMODIFICARINFOPAGOCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TCREAROMODFIRMA")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTCREAROMODFIRMA(TCREAROMODFIRMARequest request) {
        return service.tCREAROMODFIRMA(request);
    }
    

    @POST
    @Path(value = "/TCREARSEGUNDAFIRMA")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTCREARSEGUNDAFIRMA(TCREARSEGUNDAFIRMARequest request) {
        return service.tCREARSEGUNDAFIRMA(request);
    }
    

    @POST
    @Path(value = "/TCREARSEGUNDAFIRMACheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTCREARSEGUNDAFIRMACheckPreconditions(TCREARSEGUNDAFIRMARequest request) {

        return service.tCREARSEGUNDAFIRMACheckPreconditions(request);
    }

    @POST
    @Path(value = "/TENVIARDOC")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public TENVIARDOCResponse solicitudTENVIARDOC(TENVIARDOCRequest request) {
        return service.tENVIARDOC(request);
    }
    

    @POST
    @Path(value = "/TNOTIFICARANULACION")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudTNOTIFICARANULACION(TNOTIFICARANULACIONRequest request) {
        return service.tNOTIFICARANULACION(request);
    }
    

    @POST
    @Path(value = "/TNOTIFICARCREAR")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudTNOTIFICARCREAR(TNOTIFICARCREARRequest request) {
        return service.tNOTIFICARCREAR(request);
    }
    

    @POST
    @Path(value = "/TNOTIFICARINCLUSION")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudTNOTIFICARINCLUSION(TNOTIFICARINCLUSIONRequest request) {
        return service.tNOTIFICARINCLUSION(request);
    }
    

    @POST
    @Path(value = "/TINDICARCONTRATANTE2")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTINDICARCONTRATANTE2(TINDICARCONTRATANTE2Request request) {
        return service.tINDICARCONTRATANTE2(request);
    }
    

    @POST
    @Path(value = "/TINDICARCONTRATANTE2CheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTINDICARCONTRATANTE2CheckPreconditions(TINDICARCONTRATANTE2Request request) {

        return service.tINDICARCONTRATANTE2CheckPreconditions(request);
    }

    @POST
    @Path(value = "/TINDICARTITULAR2")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTINDICARTITULAR2(TINDICARTITULAR2Request request) {
        return service.tINDICARTITULAR2(request);
    }
    

    @POST
    @Path(value = "/TINDICARTITULAR2CheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTINDICARTITULAR2CheckPreconditions(TINDICARTITULAR2Request request) {

        return service.tINDICARTITULAR2CheckPreconditions(request);
    }

    @POST
    @Path(value = "/TCHGTITBENEFCONT")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTCHGTITBENEFCONT(TCHGTITBENEFCONTRequest request) {
        return service.tCHGTITBENEFCONT(request);
    }
    

    @POST
    @Path(value = "/TCHGTITBENEFCONTCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTCHGTITBENEFCONTCheckPreconditions(TCHGTITBENEFCONTRequest request) {

        return service.tCHGTITBENEFCONTCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TMARCARCOMPLETADA")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public TMARCARCOMPLETADAResponse solicitudTMARCARCOMPLETADA(TMARCARCOMPLETADARequest request) {
        return service.tMARCARCOMPLETADA(request);
    }
    

    @POST
    @Path(value = "/TMARCARCOMPLETADACheckPreconditions")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public TMARCARCOMPLETADAResponse solicitudTMARCARCOMPLETADACheckPreconditions(TMARCARCOMPLETADARequest request) {

        return service.tMARCARCOMPLETADACheckPreconditions(request);
    }

    @POST
    @Path(value = "/TCAMBIARESTADO")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudTCAMBIARESTADO(TCAMBIARESTADORequest request) {
        return service.tCAMBIARESTADO(request);
    }
    

    @POST
    @Path(value = "/TCAMBIARESTADOERROR")
    @RolesAllowed({"Admin","SuperAdmin","AreaMedica","MesaDeApoyo"})
    public ServiceResponse solicitudTCAMBIARESTADOERROR(TCAMBIARESTADOERRORRequest request) {
        return service.tCAMBIARESTADOERROR(request);
    }
    

    @POST
    @Path(value = "/TCAMBIARESTADOERRORCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","AreaMedica","MesaDeApoyo"})
    public ServiceResponse solicitudTCAMBIARESTADOERRORCheckPreconditions(TCAMBIARESTADOERRORRequest request) {

        return service.tCAMBIARESTADOERRORCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TCAMBIARESTDEVAREAMED")
    @RolesAllowed({"Admin","SuperAdmin","AreaMedica","Afiliaciones","MesaDeApoyo"})
    public TCAMBIARESTDEVAREAMEDResponse solicitudTCAMBIARESTDEVAREAMED(TCAMBIARESTDEVAREAMEDRequest request) {
        return service.tCAMBIARESTDEVAREAMED(request);
    }
    

    @POST
    @Path(value = "/TCAMBIARESTDEVAREAMEDCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","AreaMedica","Afiliaciones","MesaDeApoyo"})
    public TCAMBIARESTDEVAREAMEDResponse solicitudTCAMBIARESTDEVAREAMEDCheckPreconditions(TCAMBIARESTDEVAREAMEDRequest request) {

        return service.tCAMBIARESTDEVAREAMEDCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TCAMBIARESTPENDMEDICO")
    @RolesAllowed({"Admin","SuperAdmin","AreaMedica","Afiliaciones","MesaDeApoyo"})
    public ServiceResponse solicitudTCAMBIARESTPENDMEDICO(TCAMBIARESTPENDMEDICORequest request) {
        return service.tCAMBIARESTPENDMEDICO(request);
    }
    

    @POST
    @Path(value = "/TCAMBIARESTPENDMEDICOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","AreaMedica","Afiliaciones","MesaDeApoyo"})
    public ServiceResponse solicitudTCAMBIARESTPENDMEDICOCheckPreconditions(TCAMBIARESTPENDMEDICORequest request) {

        return service.tCAMBIARESTPENDMEDICOCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TMODENCABEZADO")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODENCABEZADO(TMODENCABEZADORequest request) {
        return service.tMODENCABEZADO(request);
    }
    

    @POST
    @Path(value = "/TMODENCABEZADOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODENCABEZADOCheckPreconditions(TMODENCABEZADORequest request) {

        return service.tMODENCABEZADOCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TCAMBIARESTANULADO")
    @RolesAllowed({"Admin","SuperAdmin","Afiliaciones","MesaDeApoyo"})
    public ServiceResponse solicitudTCAMBIARESTANULADO(TCAMBIARESTANULADORequest request) {
        return service.tCAMBIARESTANULADO(request);
    }
    

    @POST
    @Path(value = "/TCAMBIARESTANULADOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Afiliaciones","MesaDeApoyo"})
    public ServiceResponse solicitudTCAMBIARESTANULADOCheckPreconditions(TCAMBIARESTANULADORequest request) {

        return service.tCAMBIARESTANULADOCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TANULARAUTO")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse solicitudTANULARAUTO(TANULARAUTORequest request) {
        return service.tANULARAUTO(request);
    }
    

    @POST
    @Path(value = "/TMODDIRSCONTRATANTE")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODDIRSCONTRATANTE(TMODDIRSCONTRATANTERequest request) {
        return service.tMODDIRSCONTRATANTE(request);
    }
    

    @POST
    @Path(value = "/TMODDIRSCONTRATANTECheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODDIRSCONTRATANTECheckPreconditions(TMODDIRSCONTRATANTERequest request) {

        return service.tMODDIRSCONTRATANTECheckPreconditions(request);
    }

    @POST
    @Path(value = "/TMODDIRSTITULAR")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODDIRSTITULAR(TMODDIRSTITULARRequest request) {
        return service.tMODDIRSTITULAR(request);
    }
    

    @POST
    @Path(value = "/TMODDIRSTITULARCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODDIRSTITULARCheckPreconditions(TMODDIRSTITULARRequest request) {

        return service.tMODDIRSTITULARCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TMODENCABEZADOINCL")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODENCABEZADOINCL(TMODENCABEZADOINCLRequest request) {
        return service.tMODENCABEZADOINCL(request);
    }
    

    @POST
    @Path(value = "/TMODENCABEZADOINCLCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTMODENCABEZADOINCLCheckPreconditions(TMODENCABEZADOINCLRequest request) {

        return service.tMODENCABEZADOINCLCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TCAMBIARESTAPROBMEDICO")
    @RolesAllowed({"Admin","SuperAdmin","AreaMedica"})
    public ServiceResponse solicitudTCAMBIARESTAPROBMEDICO(TCAMBIARESTAPROBMEDICORequest request) {
        return service.tCAMBIARESTAPROBMEDICO(request);
    }
    

    @POST
    @Path(value = "/TCAMBIARESTAPROBMEDICOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","AreaMedica"})
    public ServiceResponse solicitudTCAMBIARESTAPROBMEDICOCheckPreconditions(TCAMBIARESTAPROBMEDICORequest request) {

        return service.tCAMBIARESTAPROBMEDICOCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TCAMBIARESTADOADMIN")
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public ServiceResponse solicitudTCAMBIARESTADOADMIN(TCAMBIARESTADOADMINRequest request) {
        return service.tCAMBIARESTADOADMIN(request);
    }
    

    @POST
    @Path(value = "/TCREAROMODFIRMACONTINC")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTCREAROMODFIRMACONTINC(TCREAROMODFIRMACONTINCRequest request) {
        return service.tCREAROMODFIRMACONTINC(request);
    }
    

    @POST
    @Path(value = "/TCREAROMODFIRMACONTINCCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","MesaDeApoyo"})
    public ServiceResponse solicitudTCREAROMODFIRMACONTINCCheckPreconditions(TCREAROMODFIRMACONTINCRequest request) {

        return service.tCREAROMODFIRMACONTINCCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TPOSTGENERAREXITO")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudTPOSTGENERAREXITO(TPOSTGENERAREXITORequest request) {
        return service.tPOSTGENERAREXITO(request);
    }
    

    @POST
    @Path(value = "/TCAMBIARESTADODOC")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse solicitudTCAMBIARESTADODOC(TCAMBIARESTADODOCRequest request) {
        return service.tCAMBIARESTADODOC(request);
    }
    

    @POST
    @Path(value = "/TCAMBIARESTADODOCCheckPreconditions")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse solicitudTCAMBIARESTADODOCCheckPreconditions(TCAMBIARESTADODOCRequest request) {

        return service.tCAMBIARESTADODOCCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TREASIGNARUSUARIOS")
    @RolesAllowed({"Admin","SuperAdmin"})
    public TREASIGNARUSUARIOSResponse solicitudTREASIGNARUSUARIOS(TREASIGNARUSUARIOSRequest request) {
        return service.tREASIGNARUSUARIOS(request);
    }
    

    @POST
    @Path(value = "/TREASIGNARUSUARIOSCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public TREASIGNARUSUARIOSResponse solicitudTREASIGNARUSUARIOSCheckPreconditions(TREASIGNARUSUARIOSRequest request) {

        return service.tREASIGNARUSUARIOSCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TSUBIRDOCUMENTOATO")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","MesaDeApoyo"})
    public ServiceResponse solicitudTSUBIRDOCUMENTOATO(TSUBIRDOCUMENTOATORequest request) {
        return service.tSUBIRDOCUMENTOATO(request);
    }
    

    @POST
    @Path(value = "/TSUBIRDOCUMENTOATOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","MesaDeApoyo"})
    public ServiceResponse solicitudTSUBIRDOCUMENTOATOCheckPreconditions(TSUBIRDOCUMENTOATORequest request) {

        return service.tSUBIRDOCUMENTOATOCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TGETPLANTILLAREPORTE")
    public TGETPLANTILLAREPORTEResponse solicitudTGETPLANTILLAREPORTE(TGETPLANTILLAREPORTERequest request) {
        return service.tGETPLANTILLAREPORTE(request);
    }
    

    @POST
    @Path(value = "/TGETPLANTILLAREPORTECheckPreconditions")
    public TGETPLANTILLAREPORTEResponse solicitudTGETPLANTILLAREPORTECheckPreconditions(TGETPLANTILLAREPORTERequest request) {

        return service.tGETPLANTILLAREPORTECheckPreconditions(request);
    }

    @POST
    @Path(value = "/TENVIARDOCAUTOAUX")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ServiceResponse solicitudTENVIARDOCAUTOAUX(TENVIARDOCAUTOAUXRequest request) {
        return service.tENVIARDOCAUTOAUX(request);
    }
    

    @POST
    @Path(value = "/IIU_NuevaAfiliacion")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUNuevaAfil iiuIIUNuevaAfiliacion(SolicitudOid oid) {
        return service.iIUNuevaAfiliacion(oid);
    }

    @POST
    @Path(value = "/IIU_InfoContratanteJUR")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUInfoContratJUR iiuIIUInfoContratanteJUR(SolicitudOid oid) {
        return service.iIUInfoContratanteJUR(oid);
    }

    @POST
    @Path(value = "/IIU_InfoObservaciones")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUInfoObservacion iiuIIUInfoObservaciones(SolicitudOid oid) {
        return service.iIUInfoObservaciones(oid);
    }

    @POST
    @Path(value = "/IIU_InfoPago")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUInfoPago iiuIIUInfoPago(SolicitudOid oid) {
        return service.iIUInfoPago(oid);
    }

    @POST
    @Path(value = "/IIU_Inclusion")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUInclusion iiuIIUInclusion(SolicitudOid oid) {
        return service.iIUInclusion(oid);
    }

    @POST
    @Path(value = "/IIU_InfoContratanteIDINCL")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudInfoContratanteID iiuIIUInfoContratanteIDINCL(SolicitudOid oid) {
        return service.iIUInfoContratanteIDINCL(oid);
    }

    @POST
    @Path(value = "/IIU_InfoContratanteContactoINC")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUInfoContraConta iiuIIUInfoContratanteContactoINC(SolicitudOid oid) {
        return service.iIUInfoContratanteContactoINC(oid);
    }

    @POST
    @Path(value = "/IIU_SolicitudVacia")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudVacia iiuIIUSolicitudVacia(SolicitudOid oid) {
        return service.iIUSolicitudVacia(oid);
    }

    @POST
    @Path(value = "/IIU_DiferenciasLiquidacion")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUDifLiq iiuIIUDiferenciasLiquidacion(SolicitudOid oid) {
        return service.iIUDiferenciasLiquidacion(oid);
    }

    @POST
    @Path(value = "/IIU_SolicitudPreexistencias")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUPreexistencias iiuIIUSolicitudPreexistencias(SolicitudOid oid) {
        return service.iIUSolicitudPreexistencias(oid);
    }

    @POST
    @Path(value = "/IIU_InfoContratanteEditable")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public ICSolicitud iiuIIUInfoContratanteEditable(SolicitudOid oid) {
        return service.iIUInfoContratanteEditable(oid);
    }

    @POST
    @Path(value = "/IIU_InfoTitularEditable")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public ICSolicitud iiuIIUInfoTitularEditable(SolicitudOid oid) {
        return service.iIUInfoTitularEditable(oid);
    }

    @POST
    @Path(value = "/IIU_InfoContratanteNAT")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUInfoContratNAT iiuIIUInfoContratanteNAT(SolicitudOid oid) {
        return service.iIUInfoContratanteNAT(oid);
    }

    @POST
    @Path(value = "/IIU_InfoContratanteJURCol")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUInfoContratJUR iiuIIUInfoContratanteJURCol(SolicitudOid oid) {
        return service.iIUInfoContratanteJURCol(oid);
    }

    @POST
    @Path(value = "/IIU_InfoContratanteNATCol")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudIIUInfoContratNAT iiuIIUInfoContratanteNATCol(SolicitudOid oid) {
        return service.iIUInfoContratanteNATCol(oid);
    }

    @POST
    @Path(value = "/IIU_SolicitudEstatus_VerMas")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudPIUStatusFull iiuIIUSolicitudEstatusVerMas(SolicitudOid oid) {
        return service.iIUSolicitudEstatusVerMas(oid);
    }

    @POST
    @Path(value = "/IIU_SolicitudEstatus_VerMasAse")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSSolicitudPIUStatusFullAse iiuIIUSolicitudEstatusVerMasAse(SolicitudOid oid) {
        return service.iIUSolicitudEstatusVerMasAse(oid);
    }

    @POST
    @Path(value = "/PIU_Radicados_Obs")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIURadicadosObsResponse piuPIURadicadosObs(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIURadicadosObsResponse response;
        try
        {
            response = new PIURadicadosObsResponse(service.piuPIURadicadosObs(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIURadicadosObs(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIURadicadosObsResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_ConsultarRadicado")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIUConsultarRadicadoResponse piuPIUConsultarRadicado(PIUConsultarRadicadoRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUConsultarRadicadoResponse response;
        try
        {
            response = new PIUConsultarRadicadoResponse(service.piuPIUConsultarRadicado(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUConsultarRadicado(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUConsultarRadicadoResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_EnvioDoc")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIUEnvioDocResponse piuPIUEnvioDoc(PIUEnvioDocRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUEnvioDocResponse response;
        try
        {
            response = new PIUEnvioDocResponse(service.piuPIUEnvioDoc(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUEnvioDoc(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUEnvioDocResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_EstatusSolicitudesAdmin")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIUEstatusSolicitudesAdminResponse piuPIUEstatusSolicitudesAdmin(PIUEstatusSolicitudesAdminRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUEstatusSolicitudesAdminResponse response;
        try
        {
            response = new PIUEstatusSolicitudesAdminResponse(service.piuPIUEstatusSolicitudesAdmin(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUEstatusSolicitudesAdmin(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUEstatusSolicitudesAdminResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_EstatusSolicitudes_Afil")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIUEstatusSolicitudesAfilResponse piuPIUEstatusSolicitudesAfil(PIUEstatusSolicitudesAfilRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUEstatusSolicitudesAfilResponse response;
        try
        {
            response = new PIUEstatusSolicitudesAfilResponse(service.piuPIUEstatusSolicitudesAfil(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUEstatusSolicitudesAfil(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUEstatusSolicitudesAfilResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_EstatusSolicitudes_AreaMed")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIUEstatusSolicitudesAreaMedResponse piuPIUEstatusSolicitudesAreaMed(PIUEstatusSolicitudesAreaMedRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUEstatusSolicitudesAreaMedResponse response;
        try
        {
            response = new PIUEstatusSolicitudesAreaMedResponse(service.piuPIUEstatusSolicitudesAreaMed(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUEstatusSolicitudesAreaMed(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUEstatusSolicitudesAreaMedResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_EstatusSolicitudes_Asesor")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIUEstatusSolicitudesAsesorResponse piuPIUEstatusSolicitudesAsesor(PIUEstatusSolicitudesAsesorRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUEstatusSolicitudesAsesorResponse response;
        try
        {
            response = new PIUEstatusSolicitudesAsesorResponse(service.piuPIUEstatusSolicitudesAsesor(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUEstatusSolicitudesAsesor(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUEstatusSolicitudesAsesorResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_EstatusSol_GestorMesa")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIUEstatusSolGestorMesaResponse piuPIUEstatusSolGestorMesa(PIUEstatusSolGestorMesaRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUEstatusSolGestorMesaResponse response;
        try
        {
            response = new PIUEstatusSolGestorMesaResponse(service.piuPIUEstatusSolGestorMesa(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUEstatusSolGestorMesa(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUEstatusSolGestorMesaResponse();
            response.setException(e);
        }
        return response;
    }


    @GET
    @Path("buildRelatedInstance/{relatedInstanceJsonOid}/{relatedInstancePath}")
    @Operation(hidden = true)
    public AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePath) {
        return service.buildRelatedInstance(relatedInstanceJsonOid, relatedInstancePath);
    }
}
