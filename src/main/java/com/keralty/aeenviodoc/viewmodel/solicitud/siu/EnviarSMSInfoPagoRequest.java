package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class EnviarSMSInfoPagoRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudenviarSMSInfoPagopthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudenviarSMSInfoPagopthisSolicitudInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudenviarSMSInfoPagopthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudenviarSMSInfoPagopthisSolicitud() {
        return this.solicitudenviarSMSInfoPagopthisSolicitud;
    }
    
    public void setSolicitudenviarSMSInfoPagopthisSolicitud(SolicitudOid solicitudenviarSMSInfoPagopthisSolicitud) {
        this.solicitudenviarSMSInfoPagopthisSolicitud = solicitudenviarSMSInfoPagopthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudenviarSMSInfoPagopthisSolicitudInstance() {
        return this.solicitudenviarSMSInfoPagopthisSolicitudInstance;
    }
    
    public void setSolicitudenviarSMSInfoPagopthisSolicitudInstance(Solicitud solicitudenviarSMSInfoPagopthisSolicitud) {
        this.solicitudenviarSMSInfoPagopthisSolicitudInstance = solicitudenviarSMSInfoPagopthisSolicitud;
        this.solicitudenviarSMSInfoPagopthisSolicitud = solicitudenviarSMSInfoPagopthisSolicitud != null ? solicitudenviarSMSInfoPagopthisSolicitud.getOid() : null;
    }

    private void checkSolicitudenviarSMSInfoPagopthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudenviarSMSInfoPagopthisSolicitudInstance() == null || this.getSolicitudenviarSMSInfoPagopthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_ENVIARSMSINFOPAGO, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_ENVIARSMSINFOPAGO_SOLICITUDENVIARSMSINFOPAGOPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_ENVIARSMSINFOPAGO, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_ENVIARSMSINFOPAGO_SOLICITUDENVIARSMSINFOPAGOPTHISSOLICITUD);
        }
    }
}
