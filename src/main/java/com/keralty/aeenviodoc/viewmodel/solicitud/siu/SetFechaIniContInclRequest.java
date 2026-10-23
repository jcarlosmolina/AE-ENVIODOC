package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.SolicitudConstants;
import com.keralty.aeenviodoc.persistence.oid.SolicitudOid;
import com.keralty.aeenviodoc.persistence.Solicitud;
import com.keralty.aeenviodoc.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeenviodoc.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class SetFechaIniContInclRequest {

    @JsonProperty("p_thissolicitud_oid")
    private SolicitudOid solicitudsetFechaIniContInclpthisSolicitud;
    
    @JsonIgnore
    private Solicitud solicitudsetFechaIniContInclpthisSolicitudInstance;

    @JsonProperty("pefecha")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date solicitudsetFechaIniContInclpeFecha;

    public void checkArguments() throws NotNullArgumentException {
        checkSolicitudsetFechaIniContInclpthisSolicitud();
    }

    @JsonIgnore
    public SolicitudOid getSolicitudsetFechaIniContInclpthisSolicitud() {
        return this.solicitudsetFechaIniContInclpthisSolicitud;
    }
    
    public void setSolicitudsetFechaIniContInclpthisSolicitud(SolicitudOid solicitudsetFechaIniContInclpthisSolicitud) {
        this.solicitudsetFechaIniContInclpthisSolicitud = solicitudsetFechaIniContInclpthisSolicitud;
    }

    @JsonIgnore
    public Solicitud getSolicitudsetFechaIniContInclpthisSolicitudInstance() {
        return this.solicitudsetFechaIniContInclpthisSolicitudInstance;
    }
    
    public void setSolicitudsetFechaIniContInclpthisSolicitudInstance(Solicitud solicitudsetFechaIniContInclpthisSolicitud) {
        this.solicitudsetFechaIniContInclpthisSolicitudInstance = solicitudsetFechaIniContInclpthisSolicitud;
        this.solicitudsetFechaIniContInclpthisSolicitud = solicitudsetFechaIniContInclpthisSolicitud != null ? solicitudsetFechaIniContInclpthisSolicitud.getOid() : null;
    }

    private void checkSolicitudsetFechaIniContInclpthisSolicitud() throws NotNullArgumentException {
        if(this.getSolicitudsetFechaIniContInclpthisSolicitudInstance() == null || this.getSolicitudsetFechaIniContInclpthisSolicitudInstance().isNull()) {
            throw new NotNullArgumentException(SolicitudConstants.SERV_ID_SETFECHAINICONTINCL, SolicitudConstants.CLASS_ID, SolicitudConstants.ARG_ID_SETFECHAINICONTINCL_SOLICITUDSETFECHAINICONTINCLPTHISSOLICITUD, SolicitudConstants.SERV_ALIAS_SETFECHAINICONTINCL, SolicitudConstants.CLASS_ALIAS, SolicitudConstants.ARG_ALIAS_SETFECHAINICONTINCL_SOLICITUDSETFECHAINICONTINCLPTHISSOLICITUD);
        }
    }

    @JsonIgnore
    public Date getSolicitudsetFechaIniContInclpeFecha() {
        return this.solicitudsetFechaIniContInclpeFecha;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setSolicitudsetFechaIniContInclpeFecha(Date solicitudsetFechaIniContInclpeFecha) {
        this.solicitudsetFechaIniContInclpeFecha = solicitudsetFechaIniContInclpeFecha;
    }
}
