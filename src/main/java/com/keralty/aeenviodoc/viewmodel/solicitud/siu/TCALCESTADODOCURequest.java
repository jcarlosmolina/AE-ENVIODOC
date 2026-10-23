package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class TCALCESTADODOCURequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudTCALCESTADODOCUpthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudTCALCESTADODOCUpthisSolicitudInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudTCALCESTADODOCUpthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudTCALCESTADODOCUpthisSolicitud() {
        return this.solicitudTCALCESTADODOCUpthisSolicitud;
    }
    
    public void setSolicitudTCALCESTADODOCUpthisSolicitud(SolicitudOid solicitudTCALCESTADODOCUpthisSolicitud) {
        this.solicitudTCALCESTADODOCUpthisSolicitud = solicitudTCALCESTADODOCUpthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudTCALCESTADODOCUpthisSolicitudInstance() {
        return this.solicitudTCALCESTADODOCUpthisSolicitudInstance;
    }
    
    public void setSolicitudTCALCESTADODOCUpthisSolicitudInstance(Solicitud solicitudTCALCESTADODOCUpthisSolicitud) {
        this.solicitudTCALCESTADODOCUpthisSolicitudInstance = solicitudTCALCESTADODOCUpthisSolicitud;
        this.solicitudTCALCESTADODOCUpthisSolicitud = solicitudTCALCESTADODOCUpthisSolicitud != null ? solicitudTCALCESTADODOCUpthisSolicitud.getOid() : null;
    }

    private void checkSolicitudTCALCESTADODOCUpthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudTCALCESTADODOCUpthisSolicitudInstance() == null || this.getSolicitudTCALCESTADODOCUpthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_TCALCESTADODOCU, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_TCALCESTADODOCU_SOLICITUDTCALCESTADODOCUPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_TCALCESTADODOCU, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_TCALCESTADODOCU_SOLICITUDTCALCESTADODOCUPTHISSOLICITUD);
        }
    }
}
