package com.keralty.aeenviodoc.viewmodel.periodicidad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.PeriodicidadConstants;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.oid.DominioOid;
import com.keralty.aeenviodoc.persistence.oid.PeriodicidadOid;
import com.keralty.aeenviodoc.persistence.Periodicidad;

public class InsPeriodicidadDominioRequest {

    @JsonProperty("p_thisperiodicidad_oid")
    private PeriodicidadOid periodicidadInsPeriodicidadDominiopthisPeriodicidad;
    
    @JsonIgnore
    private Periodicidad periodicidadInsPeriodicidadDominiopthisPeriodicidadInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid periodicidadInsPeriodicidadDominiopevcDominio;
    
    @JsonIgnore
    private Dominio periodicidadInsPeriodicidadDominiopevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPeriodicidadInsPeriodicidadDominiopthisPeriodicidad();
        checkPeriodicidadInsPeriodicidadDominiopevcDominio();
    }

    @JsonIgnore
    public PeriodicidadOid getPeriodicidadInsPeriodicidadDominiopthisPeriodicidad() {
        return this.periodicidadInsPeriodicidadDominiopthisPeriodicidad;
    }
    
    public void setPeriodicidadInsPeriodicidadDominiopthisPeriodicidad(PeriodicidadOid periodicidadInsPeriodicidadDominiopthisPeriodicidad) {
        this.periodicidadInsPeriodicidadDominiopthisPeriodicidad = periodicidadInsPeriodicidadDominiopthisPeriodicidad;
    }

    @JsonIgnore
    public Periodicidad getPeriodicidadInsPeriodicidadDominiopthisPeriodicidadInstance() {
        return this.periodicidadInsPeriodicidadDominiopthisPeriodicidadInstance;
    }
    
    public void setPeriodicidadInsPeriodicidadDominiopthisPeriodicidadInstance(Periodicidad periodicidadInsPeriodicidadDominiopthisPeriodicidad) {
        this.periodicidadInsPeriodicidadDominiopthisPeriodicidadInstance = periodicidadInsPeriodicidadDominiopthisPeriodicidad;
        this.periodicidadInsPeriodicidadDominiopthisPeriodicidad = periodicidadInsPeriodicidadDominiopthisPeriodicidad != null ? periodicidadInsPeriodicidadDominiopthisPeriodicidad.getOid() : null;
    }

    private void checkPeriodicidadInsPeriodicidadDominiopthisPeriodicidad() throws NotNullArgumentException {
        if(this.getPeriodicidadInsPeriodicidadDominiopthisPeriodicidadInstance() == null || this.getPeriodicidadInsPeriodicidadDominiopthisPeriodicidadInstance().isNull()) {
            throw new NotNullArgumentException(PeriodicidadConstants.SERV_ID_INSPERIODICIDADDOMINIO, PeriodicidadConstants.CLASS_ID, PeriodicidadConstants.ARG_ID_INSPERIODICIDADDOMINIO_PERIODICIDADINSPERIODICIDADDOMINIOPTHISPERIODICIDAD, PeriodicidadConstants.SERV_ALIAS_INSPERIODICIDADDOMINIO, PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ARG_ALIAS_INSPERIODICIDADDOMINIO_PERIODICIDADINSPERIODICIDADDOMINIOPTHISPERIODICIDAD);
        }
    }

    @JsonIgnore
    public DominioOid getPeriodicidadInsPeriodicidadDominiopevcDominio() {
        return this.periodicidadInsPeriodicidadDominiopevcDominio;
    }
    
    public void setPeriodicidadInsPeriodicidadDominiopevcDominio(DominioOid periodicidadInsPeriodicidadDominiopevcDominio) {
        this.periodicidadInsPeriodicidadDominiopevcDominio = periodicidadInsPeriodicidadDominiopevcDominio;
    }

    @JsonIgnore
    public Dominio getPeriodicidadInsPeriodicidadDominiopevcDominioInstance() {
        return this.periodicidadInsPeriodicidadDominiopevcDominioInstance;
    }
    
    public void setPeriodicidadInsPeriodicidadDominiopevcDominioInstance(Dominio periodicidadInsPeriodicidadDominiopevcDominio) {
        this.periodicidadInsPeriodicidadDominiopevcDominioInstance = periodicidadInsPeriodicidadDominiopevcDominio;
        this.periodicidadInsPeriodicidadDominiopevcDominio = periodicidadInsPeriodicidadDominiopevcDominio != null ? periodicidadInsPeriodicidadDominiopevcDominio.getOid() : null;
    }

    private void checkPeriodicidadInsPeriodicidadDominiopevcDominio() throws NotNullArgumentException {
        if(this.getPeriodicidadInsPeriodicidadDominiopevcDominioInstance() == null || this.getPeriodicidadInsPeriodicidadDominiopevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(PeriodicidadConstants.SERV_ID_INSPERIODICIDADDOMINIO, PeriodicidadConstants.CLASS_ID, PeriodicidadConstants.ARG_ID_INSPERIODICIDADDOMINIO_PERIODICIDADINSPERIODICIDADDOMINIOPEVCDOMINIO, PeriodicidadConstants.SERV_ALIAS_INSPERIODICIDADDOMINIO, PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ARG_ALIAS_INSPERIODICIDADDOMINIO_PERIODICIDADINSPERIODICIDADDOMINIOPEVCDOMINIO);
        }
    }
}
