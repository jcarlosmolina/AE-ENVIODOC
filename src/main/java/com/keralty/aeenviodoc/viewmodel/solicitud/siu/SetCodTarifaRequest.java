package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class SetCodTarifaRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudsetCodTarifapthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudsetCodTarifapthisSolicitudInstance;

    @JsonProperty("pecodtarifa")
    private String solicitudsetCodTarifapeCodTarifa;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudsetCodTarifapthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudsetCodTarifapthisSolicitud() {
        return this.solicitudsetCodTarifapthisSolicitud;
    }
    
    public void setSolicitudsetCodTarifapthisSolicitud(SolicitudOid solicitudsetCodTarifapthisSolicitud) {
        this.solicitudsetCodTarifapthisSolicitud = solicitudsetCodTarifapthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudsetCodTarifapthisSolicitudInstance() {
        return this.solicitudsetCodTarifapthisSolicitudInstance;
    }
    
    public void setSolicitudsetCodTarifapthisSolicitudInstance(Solicitud solicitudsetCodTarifapthisSolicitud) {
        this.solicitudsetCodTarifapthisSolicitudInstance = solicitudsetCodTarifapthisSolicitud;
        this.solicitudsetCodTarifapthisSolicitud = solicitudsetCodTarifapthisSolicitud != null ? solicitudsetCodTarifapthisSolicitud.getOid() : null;
    }

    private void checkSolicitudsetCodTarifapthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudsetCodTarifapthisSolicitudInstance() == null || this.getSolicitudsetCodTarifapthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_SETCODTARIFA, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_SETCODTARIFA_SOLICITUDSETCODTARIFAPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_SETCODTARIFA, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_SETCODTARIFA_SOLICITUDSETCODTARIFAPTHISSOLICITUD);
        }
    }

    @JsonIgnore
    public String getSolicitudsetCodTarifapeCodTarifa() {
        return this.solicitudsetCodTarifapeCodTarifa;
    }
    
    public void setSolicitudsetCodTarifapeCodTarifa(String solicitudsetCodTarifapeCodTarifa) {
        this.solicitudsetCodTarifapeCodTarifa = solicitudsetCodTarifapeCodTarifa;
    }
}
