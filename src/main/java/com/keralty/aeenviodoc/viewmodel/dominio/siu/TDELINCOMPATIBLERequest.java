package com.keralty.aeenviodoc.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.DominioConstants;
import com.keralty.aeenviodoc.persistence.Dominio;
import com.keralty.aeenviodoc.persistence.oid.DominioOid;

public class TDELINCOMPATIBLERequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioTDELINCOMPATIBLEpthisDominio;
    
    @JsonIgnore
    private Dominio dominioTDELINCOMPATIBLEpthisDominioInstance;

    @JsonProperty("pt_p_evcdominio_oid")
    private DominioOid dominioTDELINCOMPATIBLEptpevcDominio;
    
    @JsonIgnore
    private Dominio dominioTDELINCOMPATIBLEptpevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioTDELINCOMPATIBLEpthisDominio();
        checkDominioTDELINCOMPATIBLEptpevcDominio();
    }

    @JsonIgnore
    public DominioOid getDominioTDELINCOMPATIBLEpthisDominio() {
        return this.dominioTDELINCOMPATIBLEpthisDominio;
    }
    
    public void setDominioTDELINCOMPATIBLEpthisDominio(DominioOid dominioTDELINCOMPATIBLEpthisDominio) {
        this.dominioTDELINCOMPATIBLEpthisDominio = dominioTDELINCOMPATIBLEpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioTDELINCOMPATIBLEpthisDominioInstance() {
        return this.dominioTDELINCOMPATIBLEpthisDominioInstance;
    }
    
    public void setDominioTDELINCOMPATIBLEpthisDominioInstance(Dominio dominioTDELINCOMPATIBLEpthisDominio) {
        this.dominioTDELINCOMPATIBLEpthisDominioInstance = dominioTDELINCOMPATIBLEpthisDominio;
        this.dominioTDELINCOMPATIBLEpthisDominio = dominioTDELINCOMPATIBLEpthisDominio != null ? dominioTDELINCOMPATIBLEpthisDominio.getOid() : null;
    }

    private void checkDominioTDELINCOMPATIBLEpthisDominio() throws NotNullArgumentException {
        if(this.getDominioTDELINCOMPATIBLEpthisDominioInstance() == null || this.getDominioTDELINCOMPATIBLEpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TDELINCOMPATIBLE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TDELINCOMPATIBLE_DOMINIOTDELINCOMPATIBLEPTHISDOMINIO, DominioConstants.SERV_ALIAS_TDELINCOMPATIBLE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TDELINCOMPATIBLE_DOMINIOTDELINCOMPATIBLEPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public DominioOid getDominioTDELINCOMPATIBLEptpevcDominio() {
        return this.dominioTDELINCOMPATIBLEptpevcDominio;
    }
    
    public void setDominioTDELINCOMPATIBLEptpevcDominio(DominioOid dominioTDELINCOMPATIBLEptpevcDominio) {
        this.dominioTDELINCOMPATIBLEptpevcDominio = dominioTDELINCOMPATIBLEptpevcDominio;
    }

    @JsonIgnore
    public Dominio getDominioTDELINCOMPATIBLEptpevcDominioInstance() {
        return this.dominioTDELINCOMPATIBLEptpevcDominioInstance;
    }
    
    public void setDominioTDELINCOMPATIBLEptpevcDominioInstance(Dominio dominioTDELINCOMPATIBLEptpevcDominio) {
        this.dominioTDELINCOMPATIBLEptpevcDominioInstance = dominioTDELINCOMPATIBLEptpevcDominio;
        this.dominioTDELINCOMPATIBLEptpevcDominio = dominioTDELINCOMPATIBLEptpevcDominio != null ? dominioTDELINCOMPATIBLEptpevcDominio.getOid() : null;
    }

    private void checkDominioTDELINCOMPATIBLEptpevcDominio() throws NotNullArgumentException {
        if(this.getDominioTDELINCOMPATIBLEptpevcDominioInstance() == null || this.getDominioTDELINCOMPATIBLEptpevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TDELINCOMPATIBLE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TDELINCOMPATIBLE_DOMINIOTDELINCOMPATIBLEPTPEVCDOMINIO, DominioConstants.SERV_ALIAS_TDELINCOMPATIBLE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TDELINCOMPATIBLE_DOMINIOTDELINCOMPATIBLEPTPEVCDOMINIO);
        }
    }
}
