package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class DummyDevolverIntStringRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicituddummyDevolverIntStringpthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicituddummyDevolverIntStringpthisSolicitudInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicituddummyDevolverIntStringpthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicituddummyDevolverIntStringpthisSolicitud() {
        return this.solicituddummyDevolverIntStringpthisSolicitud;
    }
    
    public void setSolicituddummyDevolverIntStringpthisSolicitud(SolicitudOid solicituddummyDevolverIntStringpthisSolicitud) {
        this.solicituddummyDevolverIntStringpthisSolicitud = solicituddummyDevolverIntStringpthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicituddummyDevolverIntStringpthisSolicitudInstance() {
        return this.solicituddummyDevolverIntStringpthisSolicitudInstance;
    }
    
    public void setSolicituddummyDevolverIntStringpthisSolicitudInstance(Solicitud solicituddummyDevolverIntStringpthisSolicitud) {
        this.solicituddummyDevolverIntStringpthisSolicitudInstance = solicituddummyDevolverIntStringpthisSolicitud;
        this.solicituddummyDevolverIntStringpthisSolicitud = solicituddummyDevolverIntStringpthisSolicitud != null ? solicituddummyDevolverIntStringpthisSolicitud.getOid() : null;
    }

    private void checkSolicituddummyDevolverIntStringpthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicituddummyDevolverIntStringpthisSolicitudInstance() == null || this.getSolicituddummyDevolverIntStringpthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_DUMMYDEVOLVERINTSTRING, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_DUMMYDEVOLVERINTSTRING_SOLICITUDDUMMYDEVOLVERINTSTRINGPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_DUMMYDEVOLVERINTSTRING, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_DUMMYDEVOLVERINTSTRING_SOLICITUDDUMMYDEVOLVERINTSTRINGPTHISSOLICITUD);
        }
    }
}
