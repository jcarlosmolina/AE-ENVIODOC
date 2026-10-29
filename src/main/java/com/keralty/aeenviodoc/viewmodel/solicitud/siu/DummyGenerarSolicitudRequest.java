package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class DummyGenerarSolicitudRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicituddummyGenerarSolicitudpthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicituddummyGenerarSolicitudpthisSolicitudInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicituddummyGenerarSolicitudpthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicituddummyGenerarSolicitudpthisSolicitud() {
        return this.solicituddummyGenerarSolicitudpthisSolicitud;
    }
    
    public void setSolicituddummyGenerarSolicitudpthisSolicitud(SolicitudOid solicituddummyGenerarSolicitudpthisSolicitud) {
        this.solicituddummyGenerarSolicitudpthisSolicitud = solicituddummyGenerarSolicitudpthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicituddummyGenerarSolicitudpthisSolicitudInstance() {
        return this.solicituddummyGenerarSolicitudpthisSolicitudInstance;
    }
    
    public void setSolicituddummyGenerarSolicitudpthisSolicitudInstance(Solicitud solicituddummyGenerarSolicitudpthisSolicitud) {
        this.solicituddummyGenerarSolicitudpthisSolicitudInstance = solicituddummyGenerarSolicitudpthisSolicitud;
        this.solicituddummyGenerarSolicitudpthisSolicitud = solicituddummyGenerarSolicitudpthisSolicitud != null ? solicituddummyGenerarSolicitudpthisSolicitud.getOid() : null;
    }

    private void checkSolicituddummyGenerarSolicitudpthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicituddummyGenerarSolicitudpthisSolicitudInstance() == null || this.getSolicituddummyGenerarSolicitudpthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_DUMMYGENERARSOLICITUD, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_DUMMYGENERARSOLICITUD_SOLICITUDDUMMYGENERARSOLICITUDPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_DUMMYGENERARSOLICITUD, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_DUMMYGENERARSOLICITUD_SOLICITUDDUMMYGENERARSOLICITUDPTHISSOLICITUD);
        }
    }
}
