package com.keralty.aeenviodoc.viewmodel.cuestionariop.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.exception.NotNullArgumentException;
import com.keralty.aeenviodoc.global.CuestionarioPConstants;
import com.keralty.aeenviodoc.persistence.CuestionarioP;
import com.keralty.aeenviodoc.persistence.oid.CuestionarioPOid;

public class TELIMINARRequest {

    @JsonProperty("p_thiscuestionariop_oid")
    private CuestionarioPOid cuestionarioPTELIMINARpthisCuestionarioP;
    
    @JsonIgnore
    private CuestionarioP cuestionarioPTELIMINARpthisCuestionarioPInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkCuestionarioPTELIMINARpthisCuestionarioP();
    }

    @JsonIgnore
    public CuestionarioPOid getCuestionarioPTELIMINARpthisCuestionarioP() {
        return this.cuestionarioPTELIMINARpthisCuestionarioP;
    }
    
    public void setCuestionarioPTELIMINARpthisCuestionarioP(CuestionarioPOid cuestionarioPTELIMINARpthisCuestionarioP) {
        this.cuestionarioPTELIMINARpthisCuestionarioP = cuestionarioPTELIMINARpthisCuestionarioP;
    }

    @JsonIgnore
    public CuestionarioP getCuestionarioPTELIMINARpthisCuestionarioPInstance() {
        return this.cuestionarioPTELIMINARpthisCuestionarioPInstance;
    }
    
    public void setCuestionarioPTELIMINARpthisCuestionarioPInstance(CuestionarioP cuestionarioPTELIMINARpthisCuestionarioP) {
        this.cuestionarioPTELIMINARpthisCuestionarioPInstance = cuestionarioPTELIMINARpthisCuestionarioP;
        this.cuestionarioPTELIMINARpthisCuestionarioP = cuestionarioPTELIMINARpthisCuestionarioP != null ? cuestionarioPTELIMINARpthisCuestionarioP.getOid() : null;
    }

    private void checkCuestionarioPTELIMINARpthisCuestionarioP() throws NotNullArgumentException {
        if(this.getCuestionarioPTELIMINARpthisCuestionarioPInstance() == null || this.getCuestionarioPTELIMINARpthisCuestionarioPInstance().isNull()) {
            throw new NotNullArgumentException(CuestionarioPConstants.SERV_ID_TELIMINAR, CuestionarioPConstants.CLASS_ID, CuestionarioPConstants.ARG_ID_TELIMINAR_CUESTIONARIOPTELIMINARPTHISCUESTIONARIOP, CuestionarioPConstants.SERV_ALIAS_TELIMINAR, CuestionarioPConstants.CLASS_ALIAS, CuestionarioPConstants.ARG_ALIAS_TELIMINAR_CUESTIONARIOPTELIMINARPTHISCUESTIONARIOP);
        }
    }
}
