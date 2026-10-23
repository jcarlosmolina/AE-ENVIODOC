package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class TQUITARTITULARRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudTQUITARTITULARpthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudTQUITARTITULARpthisSolicitudInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudTQUITARTITULARpthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudTQUITARTITULARpthisSolicitud() {
        return this.solicitudTQUITARTITULARpthisSolicitud;
    }
    
    public void setSolicitudTQUITARTITULARpthisSolicitud(SolicitudOid solicitudTQUITARTITULARpthisSolicitud) {
        this.solicitudTQUITARTITULARpthisSolicitud = solicitudTQUITARTITULARpthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudTQUITARTITULARpthisSolicitudInstance() {
        return this.solicitudTQUITARTITULARpthisSolicitudInstance;
    }
    
    public void setSolicitudTQUITARTITULARpthisSolicitudInstance(Solicitud solicitudTQUITARTITULARpthisSolicitud) {
        this.solicitudTQUITARTITULARpthisSolicitudInstance = solicitudTQUITARTITULARpthisSolicitud;
        this.solicitudTQUITARTITULARpthisSolicitud = solicitudTQUITARTITULARpthisSolicitud != null ? solicitudTQUITARTITULARpthisSolicitud.getOid() : null;
    }

    private void checkSolicitudTQUITARTITULARpthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudTQUITARTITULARpthisSolicitudInstance() == null || this.getSolicitudTQUITARTITULARpthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_TQUITARTITULAR, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_TQUITARTITULAR_SOLICITUDTQUITARTITULARPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_TQUITARTITULAR, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_TQUITARTITULAR_SOLICITUDTQUITARTITULARPTHISSOLICITUD);
        }
    }
}
