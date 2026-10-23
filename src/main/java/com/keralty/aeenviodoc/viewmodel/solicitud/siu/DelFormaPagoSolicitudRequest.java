package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.FormaPago;
import com.keralty.aeenviodoc.persistence.oid.FormaPagoOid;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class DelFormaPagoSolicitudRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudDelFormaPagoSolicitudpthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudDelFormaPagoSolicitudpthisSolicitudInstance;

    @JsonProperty("p_evcformapago_oid")
    private FormaPagoOid solicitudDelFormaPagoSolicitudpevcFormaPago;
    
    @JsonIgnore
    private FormaPago solicitudDelFormaPagoSolicitudpevcFormaPagoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudDelFormaPagoSolicitudpthisSolicitud();
        checkSolicitudDelFormaPagoSolicitudpevcFormaPago();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudDelFormaPagoSolicitudpthisSolicitud() {
        return this.solicitudDelFormaPagoSolicitudpthisSolicitud;
    }
    
    public void setSolicitudDelFormaPagoSolicitudpthisSolicitud(SolicitudOid solicitudDelFormaPagoSolicitudpthisSolicitud) {
        this.solicitudDelFormaPagoSolicitudpthisSolicitud = solicitudDelFormaPagoSolicitudpthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudDelFormaPagoSolicitudpthisSolicitudInstance() {
        return this.solicitudDelFormaPagoSolicitudpthisSolicitudInstance;
    }
    
    public void setSolicitudDelFormaPagoSolicitudpthisSolicitudInstance(Solicitud solicitudDelFormaPagoSolicitudpthisSolicitud) {
        this.solicitudDelFormaPagoSolicitudpthisSolicitudInstance = solicitudDelFormaPagoSolicitudpthisSolicitud;
        this.solicitudDelFormaPagoSolicitudpthisSolicitud = solicitudDelFormaPagoSolicitudpthisSolicitud != null ? solicitudDelFormaPagoSolicitudpthisSolicitud.getOid() : null;
    }

    private void checkSolicitudDelFormaPagoSolicitudpthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudDelFormaPagoSolicitudpthisSolicitudInstance() == null || this.getSolicitudDelFormaPagoSolicitudpthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_DELFORMAPAGOSOLICITUD, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_DELFORMAPAGOSOLICITUD_SOLICITUDDELFORMAPAGOSOLICITUDPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_DELFORMAPAGOSOLICITUD, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_DELFORMAPAGOSOLICITUD_SOLICITUDDELFORMAPAGOSOLICITUDPTHISSOLICITUD);
        }
    }

    @JsonIgnore
    public FormaPagoOid getSolicitudDelFormaPagoSolicitudpevcFormaPago() {
        return this.solicitudDelFormaPagoSolicitudpevcFormaPago;
    }
    
    public void setSolicitudDelFormaPagoSolicitudpevcFormaPago(FormaPagoOid solicitudDelFormaPagoSolicitudpevcFormaPago) {
        this.solicitudDelFormaPagoSolicitudpevcFormaPago = solicitudDelFormaPagoSolicitudpevcFormaPago;
    }

    @JsonIgnore
    public FormaPago getSolicitudDelFormaPagoSolicitudpevcFormaPagoInstance() {
        return this.solicitudDelFormaPagoSolicitudpevcFormaPagoInstance;
    }
    
    public void setSolicitudDelFormaPagoSolicitudpevcFormaPagoInstance(FormaPago solicitudDelFormaPagoSolicitudpevcFormaPago) {
        this.solicitudDelFormaPagoSolicitudpevcFormaPagoInstance = solicitudDelFormaPagoSolicitudpevcFormaPago;
        this.solicitudDelFormaPagoSolicitudpevcFormaPago = solicitudDelFormaPagoSolicitudpevcFormaPago != null ? solicitudDelFormaPagoSolicitudpevcFormaPago.getOid() : null;
    }

    private void checkSolicitudDelFormaPagoSolicitudpevcFormaPago() throws NotNullArgumentException {
        if(this.getSolicitudDelFormaPagoSolicitudpevcFormaPagoInstance() == null || this.getSolicitudDelFormaPagoSolicitudpevcFormaPagoInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_DELFORMAPAGOSOLICITUD, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_DELFORMAPAGOSOLICITUD_SOLICITUDDELFORMAPAGOSOLICITUDPEVCFORMAPAGO, SolicitudConstants.SERV_ALIAS_DELFORMAPAGOSOLICITUD, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_DELFORMAPAGOSOLICITUD_SOLICITUDDELFORMAPAGOSOLICITUDPEVCFORMAPAGO);
        }
    }
}
