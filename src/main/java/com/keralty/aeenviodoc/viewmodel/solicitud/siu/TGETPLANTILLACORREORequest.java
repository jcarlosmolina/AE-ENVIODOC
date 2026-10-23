package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class TGETPLANTILLACORREORequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudTGETPLANTILLACORREOpthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudTGETPLANTILLACORREOpthisSolicitudInstance;

    @JsonProperty("pttipo")
    private Long solicitudTGETPLANTILLACORREOptTipo;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudTGETPLANTILLACORREOpthisSolicitud();
        checkSolicitudTGETPLANTILLACORREOptTipo();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudTGETPLANTILLACORREOpthisSolicitud() {
        return this.solicitudTGETPLANTILLACORREOpthisSolicitud;
    }
    
    public void setSolicitudTGETPLANTILLACORREOpthisSolicitud(SolicitudOid solicitudTGETPLANTILLACORREOpthisSolicitud) {
        this.solicitudTGETPLANTILLACORREOpthisSolicitud = solicitudTGETPLANTILLACORREOpthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudTGETPLANTILLACORREOpthisSolicitudInstance() {
        return this.solicitudTGETPLANTILLACORREOpthisSolicitudInstance;
    }
    
    public void setSolicitudTGETPLANTILLACORREOpthisSolicitudInstance(Solicitud solicitudTGETPLANTILLACORREOpthisSolicitud) {
        this.solicitudTGETPLANTILLACORREOpthisSolicitudInstance = solicitudTGETPLANTILLACORREOpthisSolicitud;
        this.solicitudTGETPLANTILLACORREOpthisSolicitud = solicitudTGETPLANTILLACORREOpthisSolicitud != null ? solicitudTGETPLANTILLACORREOpthisSolicitud.getOid() : null;
    }

    private void checkSolicitudTGETPLANTILLACORREOpthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudTGETPLANTILLACORREOpthisSolicitudInstance() == null || this.getSolicitudTGETPLANTILLACORREOpthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_TGETPLANTILLACORREO, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_TGETPLANTILLACORREO_SOLICITUDTGETPLANTILLACORREOPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_TGETPLANTILLACORREO, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_TGETPLANTILLACORREO_SOLICITUDTGETPLANTILLACORREOPTHISSOLICITUD);
        }
    }

    @JsonIgnore
    public Long getSolicitudTGETPLANTILLACORREOptTipo() {
        return this.solicitudTGETPLANTILLACORREOptTipo;
    }
    
    public void setSolicitudTGETPLANTILLACORREOptTipo(Long solicitudTGETPLANTILLACORREOptTipo) {
        this.solicitudTGETPLANTILLACORREOptTipo = solicitudTGETPLANTILLACORREOptTipo;
    }

    private void checkSolicitudTGETPLANTILLACORREOptTipo() throws NotNullArgumentException {
        if(this.getSolicitudTGETPLANTILLACORREOptTipo() == null) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_TGETPLANTILLACORREO, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_TGETPLANTILLACORREO_SOLICITUDTGETPLANTILLACORREOPTTIPO, SolicitudConstants.SERV_ALIAS_TGETPLANTILLACORREO, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_TGETPLANTILLACORREO_SOLICITUDTGETPLANTILLACORREOPTTIPO);
        }
    }
}
