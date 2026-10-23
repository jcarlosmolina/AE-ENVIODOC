package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class DummyRedirigirRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicituddummyRedirigirpthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicituddummyRedirigirpthisSolicitudInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicituddummyRedirigirpthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicituddummyRedirigirpthisSolicitud() {
        return this.solicituddummyRedirigirpthisSolicitud;
    }
    
    public void setSolicituddummyRedirigirpthisSolicitud(SolicitudOid solicituddummyRedirigirpthisSolicitud) {
        this.solicituddummyRedirigirpthisSolicitud = solicituddummyRedirigirpthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicituddummyRedirigirpthisSolicitudInstance() {
        return this.solicituddummyRedirigirpthisSolicitudInstance;
    }
    
    public void setSolicituddummyRedirigirpthisSolicitudInstance(Solicitud solicituddummyRedirigirpthisSolicitud) {
        this.solicituddummyRedirigirpthisSolicitudInstance = solicituddummyRedirigirpthisSolicitud;
        this.solicituddummyRedirigirpthisSolicitud = solicituddummyRedirigirpthisSolicitud != null ? solicituddummyRedirigirpthisSolicitud.getOid() : null;
    }

    private void checkSolicituddummyRedirigirpthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicituddummyRedirigirpthisSolicitudInstance() == null || this.getSolicituddummyRedirigirpthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_DUMMYREDIRIGIR, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_DUMMYREDIRIGIR_SOLICITUDDUMMYREDIRIGIRPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_DUMMYREDIRIGIR, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_DUMMYREDIRIGIR_SOLICITUDDUMMYREDIRIGIRPTHISSOLICITUD);
        }
    }
}
