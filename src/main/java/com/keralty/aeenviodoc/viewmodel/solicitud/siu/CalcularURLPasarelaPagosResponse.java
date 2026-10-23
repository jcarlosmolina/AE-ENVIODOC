package com.keralty.aeenviodoc.viewmodel.solicitud.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.viewmodel.ServiceResponse;
import java.util.HashMap;
import java.util.Map;

public class CalcularURLPasarelaPagosResponse extends ServiceResponse {

    @JsonProperty("ps_urlpagos")
    private String solicitudcalcularURLPasarelaPagospsURLPagos;

    public CalcularURLPasarelaPagosResponse() {
        super();
    }

    @JsonIgnore
    public String getSolicitudcalcularURLPasarelaPagospsURLPagos() {
        return this.solicitudcalcularURLPasarelaPagospsURLPagos;
    }
    
    public void setSolicitudcalcularURLPasarelaPagospsURLPagos(String solicitudcalcularURLPasarelaPagospsURLPagos) {
        this.solicitudcalcularURLPasarelaPagospsURLPagos = solicitudcalcularURLPasarelaPagospsURLPagos;
    }

    @Override
    public Map<String, Object> buildOutArgs() {
        Map<String, Object> outArgs = new HashMap<>();
        outArgs.put("solicitudcalcularURLPasarelaPagospsURLPagos", solicitudcalcularURLPasarelaPagospsURLPagos);
        return outArgs;
    }
}
