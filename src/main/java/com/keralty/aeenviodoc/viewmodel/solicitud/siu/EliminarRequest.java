package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class EliminarRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudeliminarpthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudeliminarpthisSolicitudInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudeliminarpthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudeliminarpthisSolicitud() {
        return this.solicitudeliminarpthisSolicitud;
    }
    
    public void setSolicitudeliminarpthisSolicitud(SolicitudOid solicitudeliminarpthisSolicitud) {
        this.solicitudeliminarpthisSolicitud = solicitudeliminarpthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudeliminarpthisSolicitudInstance() {
        return this.solicitudeliminarpthisSolicitudInstance;
    }
    
    public void setSolicitudeliminarpthisSolicitudInstance(Solicitud solicitudeliminarpthisSolicitud) {
        this.solicitudeliminarpthisSolicitudInstance = solicitudeliminarpthisSolicitud;
        this.solicitudeliminarpthisSolicitud = solicitudeliminarpthisSolicitud != null ? solicitudeliminarpthisSolicitud.getOid() : null;
    }

    private void checkSolicitudeliminarpthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudeliminarpthisSolicitudInstance() == null || this.getSolicitudeliminarpthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_ELIMINAR, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_ELIMINAR_SOLICITUDELIMINARPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_ELIMINAR, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_ELIMINAR_SOLICITUDELIMINARPTHISSOLICITUD);
        }
    }
}
