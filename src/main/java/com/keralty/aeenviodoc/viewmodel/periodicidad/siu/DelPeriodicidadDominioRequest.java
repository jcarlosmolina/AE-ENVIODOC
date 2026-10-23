package com.keralty.aeenviodoc.viewmodel.periodicidad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.PeriodicidadConstants;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.oid.DominioOid;
import com.keralty.aeenviodoc.persistence.oid.PeriodicidadOid;
import com.keralty.aeenviodoc.persistence.Periodicidad;

public class DelPeriodicidadDominioRequest {

    @JsonProperty("p_thisperiodicidad_oid")
    private PeriodicidadOid periodicidadDelPeriodicidadDominiopthisPeriodicidad;
    
    @JsonIgnore
    private Periodicidad periodicidadDelPeriodicidadDominiopthisPeriodicidadInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid periodicidadDelPeriodicidadDominiopevcDominio;
    
    @JsonIgnore
    private Dominio periodicidadDelPeriodicidadDominiopevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPeriodicidadDelPeriodicidadDominiopthisPeriodicidad();
        checkPeriodicidadDelPeriodicidadDominiopevcDominio();
    }

    @JsonIgnore
    public PeriodicidadOid getPeriodicidadDelPeriodicidadDominiopthisPeriodicidad() {
        return this.periodicidadDelPeriodicidadDominiopthisPeriodicidad;
    }
    
    public void setPeriodicidadDelPeriodicidadDominiopthisPeriodicidad(PeriodicidadOid periodicidadDelPeriodicidadDominiopthisPeriodicidad) {
        this.periodicidadDelPeriodicidadDominiopthisPeriodicidad = periodicidadDelPeriodicidadDominiopthisPeriodicidad;
    }

    @JsonIgnore
    public Periodicidad getPeriodicidadDelPeriodicidadDominiopthisPeriodicidadInstance() {
        return this.periodicidadDelPeriodicidadDominiopthisPeriodicidadInstance;
    }
    
    public void setPeriodicidadDelPeriodicidadDominiopthisPeriodicidadInstance(Periodicidad periodicidadDelPeriodicidadDominiopthisPeriodicidad) {
        this.periodicidadDelPeriodicidadDominiopthisPeriodicidadInstance = periodicidadDelPeriodicidadDominiopthisPeriodicidad;
        this.periodicidadDelPeriodicidadDominiopthisPeriodicidad = periodicidadDelPeriodicidadDominiopthisPeriodicidad != null ? periodicidadDelPeriodicidadDominiopthisPeriodicidad.getOid() : null;
    }

    private void checkPeriodicidadDelPeriodicidadDominiopthisPeriodicidad() throws NotNullArgumentException {
        if(this.getPeriodicidadDelPeriodicidadDominiopthisPeriodicidadInstance() == null || this.getPeriodicidadDelPeriodicidadDominiopthisPeriodicidadInstance().isNull()) {
            throw new NotNullArgumentException(PeriodicidadConstants.SERV_ID_DELPERIODICIDADDOMINIO, PeriodicidadConstants.CLASS_ID, PeriodicidadConstants.ARG_ID_DELPERIODICIDADDOMINIO_PERIODICIDADDELPERIODICIDADDOMINIOPTHISPERIODICIDAD, PeriodicidadConstants.SERV_ALIAS_DELPERIODICIDADDOMINIO, PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ARG_ALIAS_DELPERIODICIDADDOMINIO_PERIODICIDADDELPERIODICIDADDOMINIOPTHISPERIODICIDAD);
        }
    }

    @JsonIgnore
    public DominioOid getPeriodicidadDelPeriodicidadDominiopevcDominio() {
        return this.periodicidadDelPeriodicidadDominiopevcDominio;
    }
    
    public void setPeriodicidadDelPeriodicidadDominiopevcDominio(DominioOid periodicidadDelPeriodicidadDominiopevcDominio) {
        this.periodicidadDelPeriodicidadDominiopevcDominio = periodicidadDelPeriodicidadDominiopevcDominio;
    }

    @JsonIgnore
    public Dominio getPeriodicidadDelPeriodicidadDominiopevcDominioInstance() {
        return this.periodicidadDelPeriodicidadDominiopevcDominioInstance;
    }
    
    public void setPeriodicidadDelPeriodicidadDominiopevcDominioInstance(Dominio periodicidadDelPeriodicidadDominiopevcDominio) {
        this.periodicidadDelPeriodicidadDominiopevcDominioInstance = periodicidadDelPeriodicidadDominiopevcDominio;
        this.periodicidadDelPeriodicidadDominiopevcDominio = periodicidadDelPeriodicidadDominiopevcDominio != null ? periodicidadDelPeriodicidadDominiopevcDominio.getOid() : null;
    }

    private void checkPeriodicidadDelPeriodicidadDominiopevcDominio() throws NotNullArgumentException {
        if(this.getPeriodicidadDelPeriodicidadDominiopevcDominioInstance() == null || this.getPeriodicidadDelPeriodicidadDominiopevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(PeriodicidadConstants.SERV_ID_DELPERIODICIDADDOMINIO, PeriodicidadConstants.CLASS_ID, PeriodicidadConstants.ARG_ID_DELPERIODICIDADDOMINIO_PERIODICIDADDELPERIODICIDADDOMINIOPEVCDOMINIO, PeriodicidadConstants.SERV_ALIAS_DELPERIODICIDADDOMINIO, PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ARG_ALIAS_DELPERIODICIDADDOMINIO_PERIODICIDADDELPERIODICIDADDOMINIOPEVCDOMINIO);
        }
    }
}
