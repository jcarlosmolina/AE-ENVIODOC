package com.keralty.aeenviodoc.viewmodel.beneficiarioc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.BeneficiarioCConstants;
import com.keralty.aeenviodoc.persistence.Companyia;
import com.keralty.aeenviodoc.persistence.ContratoC;
import com.keralty.aeenviodoc.persistence.Eps;
import com.keralty.aeenviodoc.persistence.EstadoBeneficiario;
import com.keralty.aeenviodoc.persistence.FamiliaC;
import com.keralty.aeenviodoc.persistence.oid.CompanyiaOid;
import com.keralty.aeenviodoc.persistence.oid.ContratoCOid;
import com.keralty.aeenviodoc.persistence.oid.EpsOid;
import com.keralty.aeenviodoc.persistence.oid.EstadoBeneficiarioOid;
import com.keralty.aeenviodoc.persistence.oid.FamiliaCOid;
import com.keralty.aeenviodoc.persistence.oid.ParentescoOid;
import com.keralty.aeenviodoc.persistence.oid.PersonaCOid;
import com.keralty.aeenviodoc.persistence.Parentesco;
import com.keralty.aeenviodoc.persistence.PersonaC;
import com.keralty.aeenviodoc.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeenviodoc.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class CrearRequest {

    @JsonProperty("p_agrepsc_oid")
    private EpsOid beneficiarioCcrearpagrEPSC;
    
    @JsonIgnore
    private Eps beneficiarioCcrearpagrEPSCInstance;

    @JsonProperty("p_agrcompanyiampc_oid")
    private CompanyiaOid beneficiarioCcrearpagrCompanyiaMPC;
    
    @JsonIgnore
    private Companyia beneficiarioCcrearpagrCompanyiaMPCInstance;

    @JsonProperty("p_agrparentescoc_oid")
    private ParentescoOid beneficiarioCcrearpagrParentescoC;
    
    @JsonIgnore
    private Parentesco beneficiarioCcrearpagrParentescoCInstance;

    @JsonProperty("p_agrestadobeneficiarioc_oid")
    private EstadoBeneficiarioOid beneficiarioCcrearpagrEstadoBeneficiarioC;
    
    @JsonIgnore
    private EstadoBeneficiario beneficiarioCcrearpagrEstadoBeneficiarioCInstance;

    @JsonProperty("p_agrcontratoc_oid")
    private ContratoCOid beneficiarioCcrearpagrContratoC;
    
    @JsonIgnore
    private ContratoC beneficiarioCcrearpagrContratoCInstance;

    @JsonProperty("p_agrfamiliasc_oid")
    private FamiliaCOid beneficiarioCcrearpagrFamiliasC;
    
    @JsonIgnore
    private FamiliaC beneficiarioCcrearpagrFamiliasCInstance;

    @JsonProperty("p_agrpersonac_oid")
    private PersonaCOid beneficiarioCcrearpagrPersonaC;
    
    @JsonIgnore
    private PersonaC beneficiarioCcrearpagrPersonaCInstance;

    @JsonProperty("p_atrcodigotipousuario")
    private String beneficiarioCcrearpatrCodigoTipoUsuario;

    @JsonProperty("p_atrindicadorbbgestacion")
    private Boolean beneficiarioCcrearpatrIndicadorBBGestacion;

    @JsonProperty("p_atrcodusuariobancorep")
    private String beneficiarioCcrearpatrCodUsuarioBancoRep;

    @JsonProperty("p_atrfechaantiguedad")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date beneficiarioCcrearpatrFechaAntiguedad;

    @JsonProperty("p_atrfechaantiguedadrealosi")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date beneficiarioCcrearpatrFechaAntiguedadRealOSI;

    @JsonProperty("p_atrrequiereexamenmedico")
    private Boolean beneficiarioCcrearpatrRequiereExamenMedico;

    @JsonProperty("p_atrestadomedico")
    private Long beneficiarioCcrearpatrEstadoMedico;

    @JsonProperty("p_atrvalorcuotainscripcion")
    private Double beneficiarioCcrearpatrValorCuotaInscripcion;

    @JsonProperty("p_atrtienepreexistencias")
    private Boolean beneficiarioCcrearpatrTienePreexistencias;

    @JsonProperty("p_atrfechainiciovigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date beneficiarioCcrearpatrFechaInicioVigencia;

    @JsonProperty("p_atrfechafinvigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date beneficiarioCcrearpatrFechaFinVigencia;

    @JsonProperty("p_atrnumusuario")
    private Long beneficiarioCcrearpatrNumUsuario;

    public void checkArguments() throws NotNullArgumentException {
        checkBeneficiarioCcrearpagrParentescoC();
        checkBeneficiarioCcrearpagrContratoC();
        checkBeneficiarioCcrearpagrFamiliasC();
    }

    @JsonIgnore
    public EpsOid getBeneficiarioCcrearpagrEPSC() {
        return this.beneficiarioCcrearpagrEPSC;
    }
    
    public void setBeneficiarioCcrearpagrEPSC(EpsOid beneficiarioCcrearpagrEPSC) {
        this.beneficiarioCcrearpagrEPSC = beneficiarioCcrearpagrEPSC;
    }

    @JsonIgnore
    public Eps getBeneficiarioCcrearpagrEPSCInstance() {
        return this.beneficiarioCcrearpagrEPSCInstance;
    }
    
    public void setBeneficiarioCcrearpagrEPSCInstance(Eps beneficiarioCcrearpagrEPSC) {
        this.beneficiarioCcrearpagrEPSCInstance = beneficiarioCcrearpagrEPSC;
        this.beneficiarioCcrearpagrEPSC = beneficiarioCcrearpagrEPSC != null ? beneficiarioCcrearpagrEPSC.getOid() : null;
    }

    @JsonIgnore
    public CompanyiaOid getBeneficiarioCcrearpagrCompanyiaMPC() {
        return this.beneficiarioCcrearpagrCompanyiaMPC;
    }
    
    public void setBeneficiarioCcrearpagrCompanyiaMPC(CompanyiaOid beneficiarioCcrearpagrCompanyiaMPC) {
        this.beneficiarioCcrearpagrCompanyiaMPC = beneficiarioCcrearpagrCompanyiaMPC;
    }

    @JsonIgnore
    public Companyia getBeneficiarioCcrearpagrCompanyiaMPCInstance() {
        return this.beneficiarioCcrearpagrCompanyiaMPCInstance;
    }
    
    public void setBeneficiarioCcrearpagrCompanyiaMPCInstance(Companyia beneficiarioCcrearpagrCompanyiaMPC) {
        this.beneficiarioCcrearpagrCompanyiaMPCInstance = beneficiarioCcrearpagrCompanyiaMPC;
        this.beneficiarioCcrearpagrCompanyiaMPC = beneficiarioCcrearpagrCompanyiaMPC != null ? beneficiarioCcrearpagrCompanyiaMPC.getOid() : null;
    }

    @JsonIgnore
    public ParentescoOid getBeneficiarioCcrearpagrParentescoC() {
        return this.beneficiarioCcrearpagrParentescoC;
    }
    
    public void setBeneficiarioCcrearpagrParentescoC(ParentescoOid beneficiarioCcrearpagrParentescoC) {
        this.beneficiarioCcrearpagrParentescoC = beneficiarioCcrearpagrParentescoC;
    }

    @JsonIgnore
    public Parentesco getBeneficiarioCcrearpagrParentescoCInstance() {
        return this.beneficiarioCcrearpagrParentescoCInstance;
    }
    
    public void setBeneficiarioCcrearpagrParentescoCInstance(Parentesco beneficiarioCcrearpagrParentescoC) {
        this.beneficiarioCcrearpagrParentescoCInstance = beneficiarioCcrearpagrParentescoC;
        this.beneficiarioCcrearpagrParentescoC = beneficiarioCcrearpagrParentescoC != null ? beneficiarioCcrearpagrParentescoC.getOid() : null;
    }

    private void checkBeneficiarioCcrearpagrParentescoC() throws NotNullArgumentException {
        if(this.getBeneficiarioCcrearpagrParentescoCInstance() == null || this.getBeneficiarioCcrearpagrParentescoCInstance().isNull()) {
            throw new NotNullArgumentException(BeneficiarioCConstants.SERV_ID_CREAR, BeneficiarioCConstants.CLASS_ID, BeneficiarioCConstants.ARG_ID_CREAR_BENEFICIARIOCCREARPAGRPARENTESCOC, BeneficiarioCConstants.SERV_ALIAS_CREAR, BeneficiarioCConstants.CLASS_ALIAS, BeneficiarioCConstants.ARG_ALIAS_CREAR_BENEFICIARIOCCREARPAGRPARENTESCOC);
        }
    }

    @JsonIgnore
    public EstadoBeneficiarioOid getBeneficiarioCcrearpagrEstadoBeneficiarioC() {
        return this.beneficiarioCcrearpagrEstadoBeneficiarioC;
    }
    
    public void setBeneficiarioCcrearpagrEstadoBeneficiarioC(EstadoBeneficiarioOid beneficiarioCcrearpagrEstadoBeneficiarioC) {
        this.beneficiarioCcrearpagrEstadoBeneficiarioC = beneficiarioCcrearpagrEstadoBeneficiarioC;
    }

    @JsonIgnore
    public EstadoBeneficiario getBeneficiarioCcrearpagrEstadoBeneficiarioCInstance() {
        return this.beneficiarioCcrearpagrEstadoBeneficiarioCInstance;
    }
    
    public void setBeneficiarioCcrearpagrEstadoBeneficiarioCInstance(EstadoBeneficiario beneficiarioCcrearpagrEstadoBeneficiarioC) {
        this.beneficiarioCcrearpagrEstadoBeneficiarioCInstance = beneficiarioCcrearpagrEstadoBeneficiarioC;
        this.beneficiarioCcrearpagrEstadoBeneficiarioC = beneficiarioCcrearpagrEstadoBeneficiarioC != null ? beneficiarioCcrearpagrEstadoBeneficiarioC.getOid() : null;
    }

    @JsonIgnore
    public ContratoCOid getBeneficiarioCcrearpagrContratoC() {
        return this.beneficiarioCcrearpagrContratoC;
    }
    
    public void setBeneficiarioCcrearpagrContratoC(ContratoCOid beneficiarioCcrearpagrContratoC) {
        this.beneficiarioCcrearpagrContratoC = beneficiarioCcrearpagrContratoC;
    }

    @JsonIgnore
    public ContratoC getBeneficiarioCcrearpagrContratoCInstance() {
        return this.beneficiarioCcrearpagrContratoCInstance;
    }
    
    public void setBeneficiarioCcrearpagrContratoCInstance(ContratoC beneficiarioCcrearpagrContratoC) {
        this.beneficiarioCcrearpagrContratoCInstance = beneficiarioCcrearpagrContratoC;
        this.beneficiarioCcrearpagrContratoC = beneficiarioCcrearpagrContratoC != null ? beneficiarioCcrearpagrContratoC.getOid() : null;
    }

    private void checkBeneficiarioCcrearpagrContratoC() throws NotNullArgumentException {
        if(this.getBeneficiarioCcrearpagrContratoCInstance() == null || this.getBeneficiarioCcrearpagrContratoCInstance().isNull()) {
            throw new NotNullArgumentException(BeneficiarioCConstants.SERV_ID_CREAR, BeneficiarioCConstants.CLASS_ID, BeneficiarioCConstants.ARG_ID_CREAR_BENEFICIARIOCCREARPAGRCONTRATOC, BeneficiarioCConstants.SERV_ALIAS_CREAR, BeneficiarioCConstants.CLASS_ALIAS, BeneficiarioCConstants.ARG_ALIAS_CREAR_BENEFICIARIOCCREARPAGRCONTRATOC);
        }
    }

    @JsonIgnore
    public FamiliaCOid getBeneficiarioCcrearpagrFamiliasC() {
        return this.beneficiarioCcrearpagrFamiliasC;
    }
    
    public void setBeneficiarioCcrearpagrFamiliasC(FamiliaCOid beneficiarioCcrearpagrFamiliasC) {
        this.beneficiarioCcrearpagrFamiliasC = beneficiarioCcrearpagrFamiliasC;
    }

    @JsonIgnore
    public FamiliaC getBeneficiarioCcrearpagrFamiliasCInstance() {
        return this.beneficiarioCcrearpagrFamiliasCInstance;
    }
    
    public void setBeneficiarioCcrearpagrFamiliasCInstance(FamiliaC beneficiarioCcrearpagrFamiliasC) {
        this.beneficiarioCcrearpagrFamiliasCInstance = beneficiarioCcrearpagrFamiliasC;
        this.beneficiarioCcrearpagrFamiliasC = beneficiarioCcrearpagrFamiliasC != null ? beneficiarioCcrearpagrFamiliasC.getOid() : null;
    }

    private void checkBeneficiarioCcrearpagrFamiliasC() throws NotNullArgumentException {
        if(this.getBeneficiarioCcrearpagrFamiliasCInstance() == null || this.getBeneficiarioCcrearpagrFamiliasCInstance().isNull()) {
            throw new NotNullArgumentException(BeneficiarioCConstants.SERV_ID_CREAR, BeneficiarioCConstants.CLASS_ID, BeneficiarioCConstants.ARG_ID_CREAR_BENEFICIARIOCCREARPAGRFAMILIASC, BeneficiarioCConstants.SERV_ALIAS_CREAR, BeneficiarioCConstants.CLASS_ALIAS, BeneficiarioCConstants.ARG_ALIAS_CREAR_BENEFICIARIOCCREARPAGRFAMILIASC);
        }
    }

    @JsonIgnore
    public PersonaCOid getBeneficiarioCcrearpagrPersonaC() {
        return this.beneficiarioCcrearpagrPersonaC;
    }
    
    public void setBeneficiarioCcrearpagrPersonaC(PersonaCOid beneficiarioCcrearpagrPersonaC) {
        this.beneficiarioCcrearpagrPersonaC = beneficiarioCcrearpagrPersonaC;
    }

    @JsonIgnore
    public PersonaC getBeneficiarioCcrearpagrPersonaCInstance() {
        return this.beneficiarioCcrearpagrPersonaCInstance;
    }
    
    public void setBeneficiarioCcrearpagrPersonaCInstance(PersonaC beneficiarioCcrearpagrPersonaC) {
        this.beneficiarioCcrearpagrPersonaCInstance = beneficiarioCcrearpagrPersonaC;
        this.beneficiarioCcrearpagrPersonaC = beneficiarioCcrearpagrPersonaC != null ? beneficiarioCcrearpagrPersonaC.getOid() : null;
    }

    @JsonIgnore
    public String getBeneficiarioCcrearpatrCodigoTipoUsuario() {
        return this.beneficiarioCcrearpatrCodigoTipoUsuario;
    }
    
    public void setBeneficiarioCcrearpatrCodigoTipoUsuario(String beneficiarioCcrearpatrCodigoTipoUsuario) {
        this.beneficiarioCcrearpatrCodigoTipoUsuario = beneficiarioCcrearpatrCodigoTipoUsuario;
    }

    @JsonIgnore
    public Boolean getBeneficiarioCcrearpatrIndicadorBBGestacion() {
        return this.beneficiarioCcrearpatrIndicadorBBGestacion;
    }
    
    public void setBeneficiarioCcrearpatrIndicadorBBGestacion(Boolean beneficiarioCcrearpatrIndicadorBBGestacion) {
        this.beneficiarioCcrearpatrIndicadorBBGestacion = beneficiarioCcrearpatrIndicadorBBGestacion;
    }

    @JsonIgnore
    public String getBeneficiarioCcrearpatrCodUsuarioBancoRep() {
        return this.beneficiarioCcrearpatrCodUsuarioBancoRep;
    }
    
    public void setBeneficiarioCcrearpatrCodUsuarioBancoRep(String beneficiarioCcrearpatrCodUsuarioBancoRep) {
        this.beneficiarioCcrearpatrCodUsuarioBancoRep = beneficiarioCcrearpatrCodUsuarioBancoRep;
    }

    @JsonIgnore
    public Date getBeneficiarioCcrearpatrFechaAntiguedad() {
        return this.beneficiarioCcrearpatrFechaAntiguedad;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setBeneficiarioCcrearpatrFechaAntiguedad(Date beneficiarioCcrearpatrFechaAntiguedad) {
        this.beneficiarioCcrearpatrFechaAntiguedad = beneficiarioCcrearpatrFechaAntiguedad;
    }

    @JsonIgnore
    public Date getBeneficiarioCcrearpatrFechaAntiguedadRealOSI() {
        return this.beneficiarioCcrearpatrFechaAntiguedadRealOSI;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setBeneficiarioCcrearpatrFechaAntiguedadRealOSI(Date beneficiarioCcrearpatrFechaAntiguedadRealOSI) {
        this.beneficiarioCcrearpatrFechaAntiguedadRealOSI = beneficiarioCcrearpatrFechaAntiguedadRealOSI;
    }

    @JsonIgnore
    public Boolean getBeneficiarioCcrearpatrRequiereExamenMedico() {
        return this.beneficiarioCcrearpatrRequiereExamenMedico;
    }
    
    public void setBeneficiarioCcrearpatrRequiereExamenMedico(Boolean beneficiarioCcrearpatrRequiereExamenMedico) {
        this.beneficiarioCcrearpatrRequiereExamenMedico = beneficiarioCcrearpatrRequiereExamenMedico;
    }

    @JsonIgnore
    public Long getBeneficiarioCcrearpatrEstadoMedico() {
        return this.beneficiarioCcrearpatrEstadoMedico;
    }
    
    public void setBeneficiarioCcrearpatrEstadoMedico(Long beneficiarioCcrearpatrEstadoMedico) {
        this.beneficiarioCcrearpatrEstadoMedico = beneficiarioCcrearpatrEstadoMedico;
    }

    @JsonIgnore
    public Double getBeneficiarioCcrearpatrValorCuotaInscripcion() {
        return this.beneficiarioCcrearpatrValorCuotaInscripcion;
    }
    
    public void setBeneficiarioCcrearpatrValorCuotaInscripcion(Double beneficiarioCcrearpatrValorCuotaInscripcion) {
        this.beneficiarioCcrearpatrValorCuotaInscripcion = beneficiarioCcrearpatrValorCuotaInscripcion;
    }

    @JsonIgnore
    public Boolean getBeneficiarioCcrearpatrTienePreexistencias() {
        return this.beneficiarioCcrearpatrTienePreexistencias;
    }
    
    public void setBeneficiarioCcrearpatrTienePreexistencias(Boolean beneficiarioCcrearpatrTienePreexistencias) {
        this.beneficiarioCcrearpatrTienePreexistencias = beneficiarioCcrearpatrTienePreexistencias;
    }

    @JsonIgnore
    public Date getBeneficiarioCcrearpatrFechaInicioVigencia() {
        return this.beneficiarioCcrearpatrFechaInicioVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setBeneficiarioCcrearpatrFechaInicioVigencia(Date beneficiarioCcrearpatrFechaInicioVigencia) {
        this.beneficiarioCcrearpatrFechaInicioVigencia = beneficiarioCcrearpatrFechaInicioVigencia;
    }

    @JsonIgnore
    public Date getBeneficiarioCcrearpatrFechaFinVigencia() {
        return this.beneficiarioCcrearpatrFechaFinVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setBeneficiarioCcrearpatrFechaFinVigencia(Date beneficiarioCcrearpatrFechaFinVigencia) {
        this.beneficiarioCcrearpatrFechaFinVigencia = beneficiarioCcrearpatrFechaFinVigencia;
    }

    @JsonIgnore
    public Long getBeneficiarioCcrearpatrNumUsuario() {
        return this.beneficiarioCcrearpatrNumUsuario;
    }
    
    public void setBeneficiarioCcrearpatrNumUsuario(Long beneficiarioCcrearpatrNumUsuario) {
        this.beneficiarioCcrearpatrNumUsuario = beneficiarioCcrearpatrNumUsuario;
    }
}
