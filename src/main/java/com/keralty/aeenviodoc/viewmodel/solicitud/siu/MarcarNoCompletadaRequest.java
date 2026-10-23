package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class MarcarNoCompletadaRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudmarcarNoCompletadapthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudmarcarNoCompletadapthisSolicitudInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudmarcarNoCompletadapthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudmarcarNoCompletadapthisSolicitud() {
        return this.solicitudmarcarNoCompletadapthisSolicitud;
    }
    
    public void setSolicitudmarcarNoCompletadapthisSolicitud(SolicitudOid solicitudmarcarNoCompletadapthisSolicitud) {
        this.solicitudmarcarNoCompletadapthisSolicitud = solicitudmarcarNoCompletadapthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudmarcarNoCompletadapthisSolicitudInstance() {
        return this.solicitudmarcarNoCompletadapthisSolicitudInstance;
    }
    
    public void setSolicitudmarcarNoCompletadapthisSolicitudInstance(Solicitud solicitudmarcarNoCompletadapthisSolicitud) {
        this.solicitudmarcarNoCompletadapthisSolicitudInstance = solicitudmarcarNoCompletadapthisSolicitud;
        this.solicitudmarcarNoCompletadapthisSolicitud = solicitudmarcarNoCompletadapthisSolicitud != null ? solicitudmarcarNoCompletadapthisSolicitud.getOid() : null;
    }

    private void checkSolicitudmarcarNoCompletadapthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudmarcarNoCompletadapthisSolicitudInstance() == null || this.getSolicitudmarcarNoCompletadapthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_MARCARNOCOMPLETADA, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_MARCARNOCOMPLETADA_SOLICITUDMARCARNOCOMPLETADAPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_MARCARNOCOMPLETADA, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_MARCARNOCOMPLETADA_SOLICITUDMARCARNOCOMPLETADAPTHISSOLICITUD);
        }
    }
}
