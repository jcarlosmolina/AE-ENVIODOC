package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;

public class SetProrrateoRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudsetProrrateopthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudsetProrrateopthisSolicitudInstance;

    @JsonProperty("peprorrateo")
    private Double solicitudsetProrrateopeProrrateo;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudsetProrrateopthisSolicitud();
        checkSolicitudsetProrrateopeProrrateo();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudsetProrrateopthisSolicitud() {
        return this.solicitudsetProrrateopthisSolicitud;
    }
    
    public void setSolicitudsetProrrateopthisSolicitud(SolicitudOid solicitudsetProrrateopthisSolicitud) {
        this.solicitudsetProrrateopthisSolicitud = solicitudsetProrrateopthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudsetProrrateopthisSolicitudInstance() {
        return this.solicitudsetProrrateopthisSolicitudInstance;
    }
    
    public void setSolicitudsetProrrateopthisSolicitudInstance(Solicitud solicitudsetProrrateopthisSolicitud) {
        this.solicitudsetProrrateopthisSolicitudInstance = solicitudsetProrrateopthisSolicitud;
        this.solicitudsetProrrateopthisSolicitud = solicitudsetProrrateopthisSolicitud != null ? solicitudsetProrrateopthisSolicitud.getOid() : null;
    }

    private void checkSolicitudsetProrrateopthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudsetProrrateopthisSolicitudInstance() == null || this.getSolicitudsetProrrateopthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_SETPRORRATEO, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_SETPRORRATEO_SOLICITUDSETPRORRATEOPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_SETPRORRATEO, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_SETPRORRATEO_SOLICITUDSETPRORRATEOPTHISSOLICITUD);
        }
    }

    @JsonIgnore
    public Double getSolicitudsetProrrateopeProrrateo() {
        return this.solicitudsetProrrateopeProrrateo;
    }
    
    public void setSolicitudsetProrrateopeProrrateo(Double solicitudsetProrrateopeProrrateo) {
        this.solicitudsetProrrateopeProrrateo = solicitudsetProrrateopeProrrateo;
    }

    private void checkSolicitudsetProrrateopeProrrateo() throws NotNullArgumentException {
        if(this.getSolicitudsetProrrateopeProrrateo() == null) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_SETPRORRATEO, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_SETPRORRATEO_SOLICITUDSETPRORRATEOPEPRORRATEO, SolicitudConstants.SERV_ALIAS_SETPRORRATEO, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_SETPRORRATEO_SOLICITUDSETPRORRATEOPEPRORRATEO);
        }
    }
}
