package com.keralty.aeenviodoc.viewmodel.reportesdominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeenviodoc.persistence.oid.ReportesDominioOid;
import com.keralty.aeenviodoc.persistence.ReportesDominio;
import com.keralty.aeenviodoc.viewmodel.ServiceResponse;
import java.util.HashMap;
import java.util.Map;

public class GetDummyResponse extends ServiceResponse {


    @JsonProperty("oareportedominio")
    private ReportesDominioOid reportesDominiogetDummyoaReporteDominio;
    
    @JsonIgnore
    private ReportesDominio reportesDominiogetDummyoaReporteDominioInstance;

    public GetDummyResponse() {
        super();
    }

    @JsonIgnore
    public ReportesDominioOid getReportesDominiogetDummyoaReporteDominio() {
        return this.reportesDominiogetDummyoaReporteDominio;
    }
    
    public void setReportesDominiogetDummyoaReporteDominio(ReportesDominioOid reportesDominiogetDummyoaReporteDominio) {
        this.reportesDominiogetDummyoaReporteDominio = reportesDominiogetDummyoaReporteDominio;
    }
    @JsonIgnore
    public ReportesDominio getReportesDominiogetDummyoaReporteDominioInstance() {
        return this.reportesDominiogetDummyoaReporteDominioInstance;
    }
    
    public void setReportesDominiogetDummyoaReporteDominioInstance(ReportesDominio reportesDominiogetDummyoaReporteDominio) {
        if (reportesDominiogetDummyoaReporteDominio != null && !reportesDominiogetDummyoaReporteDominio.isNull()) {
            this.reportesDominiogetDummyoaReporteDominio = reportesDominiogetDummyoaReporteDominio.getOid();
            this.reportesDominiogetDummyoaReporteDominioInstance = reportesDominiogetDummyoaReporteDominio;
        } else {
            this.reportesDominiogetDummyoaReporteDominio = null;
            this.reportesDominiogetDummyoaReporteDominioInstance = null;
        }
    }

    @Override
    public Map<String, Object> buildOutArgs() {
        Map<String, Object> outArgs = new HashMap<>();
        outArgs.put("reportesDominiogetDummyoaReporteDominio", reportesDominiogetDummyoaReporteDominio);
        return outArgs;
    }
}
