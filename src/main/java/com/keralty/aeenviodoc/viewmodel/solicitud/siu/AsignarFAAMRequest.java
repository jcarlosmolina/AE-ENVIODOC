package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class AsignarFAAMRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudasignarFAAMpthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudasignarFAAMpthisSolicitudInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudasignarFAAMpthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudasignarFAAMpthisSolicitud() {
        return this.solicitudasignarFAAMpthisSolicitud;
    }
    
    public void setSolicitudasignarFAAMpthisSolicitud(SolicitudOid solicitudasignarFAAMpthisSolicitud) {
        this.solicitudasignarFAAMpthisSolicitud = solicitudasignarFAAMpthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudasignarFAAMpthisSolicitudInstance() {
        return this.solicitudasignarFAAMpthisSolicitudInstance;
    }
    
    public void setSolicitudasignarFAAMpthisSolicitudInstance(Solicitud solicitudasignarFAAMpthisSolicitud) {
        this.solicitudasignarFAAMpthisSolicitudInstance = solicitudasignarFAAMpthisSolicitud;
        this.solicitudasignarFAAMpthisSolicitud = solicitudasignarFAAMpthisSolicitud != null ? solicitudasignarFAAMpthisSolicitud.getOid() : null;
    }

    private void checkSolicitudasignarFAAMpthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudasignarFAAMpthisSolicitudInstance() == null || this.getSolicitudasignarFAAMpthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_ASIGNARFAAM, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_ASIGNARFAAM_SOLICITUDASIGNARFAAMPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_ASIGNARFAAM, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_ASIGNARFAAM_SOLICITUDASIGNARFAAMPTHISSOLICITUD);
        }
    }
}
