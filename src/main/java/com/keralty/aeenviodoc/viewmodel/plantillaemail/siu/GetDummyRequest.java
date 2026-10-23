package com.keralty.aeenviodoc.viewmodel.plantillaemail.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.PlantillaEmailConstants;
import com.keralty.aeenviodoc.persistence.oid.PlantillaEmailOid;
import com.keralty.aeenviodoc.persistence.PlantillaEmail;

public class GetDummyRequest {

    @JsonProperty("p_thisplantillaemail_oid")
    private PlantillaEmailOid plantillaEmailgetDummypthisPlantillaEmail;
    
    @JsonIgnore
    private PlantillaEmail plantillaEmailgetDummypthisPlantillaEmailInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPlantillaEmailgetDummypthisPlantillaEmail();
    }

    @JsonIgnore
    public PlantillaEmailOid getPlantillaEmailgetDummypthisPlantillaEmail() {
        return this.plantillaEmailgetDummypthisPlantillaEmail;
    }
    
    public void setPlantillaEmailgetDummypthisPlantillaEmail(PlantillaEmailOid plantillaEmailgetDummypthisPlantillaEmail) {
        this.plantillaEmailgetDummypthisPlantillaEmail = plantillaEmailgetDummypthisPlantillaEmail;
    }

    @JsonIgnore
    public PlantillaEmail getPlantillaEmailgetDummypthisPlantillaEmailInstance() {
        return this.plantillaEmailgetDummypthisPlantillaEmailInstance;
    }
    
    public void setPlantillaEmailgetDummypthisPlantillaEmailInstance(PlantillaEmail plantillaEmailgetDummypthisPlantillaEmail) {
        this.plantillaEmailgetDummypthisPlantillaEmailInstance = plantillaEmailgetDummypthisPlantillaEmail;
        this.plantillaEmailgetDummypthisPlantillaEmail = plantillaEmailgetDummypthisPlantillaEmail != null ? plantillaEmailgetDummypthisPlantillaEmail.getOid() : null;
    }

    private void checkPlantillaEmailgetDummypthisPlantillaEmail() throws NotNullArgumentException {
        if(this.getPlantillaEmailgetDummypthisPlantillaEmailInstance() == null || this.getPlantillaEmailgetDummypthisPlantillaEmailInstance().isNull()) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_GETDUMMY, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_GETDUMMY_PLANTILLAEMAILGETDUMMYPTHISPLANTILLAEMAIL, PlantillaEmailConstants.SERV_ALIAS_GETDUMMY, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_GETDUMMY_PLANTILLAEMAILGETDUMMYPTHISPLANTILLAEMAIL);
        }
    }
}
