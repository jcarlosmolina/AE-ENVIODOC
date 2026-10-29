package com.keralty.aeenviodoc.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keralty.aeenviodoc.viewmodel.FilterVariableRequest;

public class ReportRequest {

	private String reportTemplatePath;
	
	private String reportData;
	
	private List<FilterVariableRequest> reportParameters;
	
	public ReportRequest() {
		reportTemplatePath = "";
		reportData = "";
		reportParameters = new ArrayList<>();
	}
	
	public String getReportTemplatePath() {
		return reportTemplatePath;
	}

	public void setReportTemplatePath(String reportPath) {
		this.reportTemplatePath = reportPath;
	}

	public String getReportData() {
		return reportData;
	}

	public void setReportData(String reportData) {
		this.reportData = reportData;
	}

	public List<FilterVariableRequest> getReportParameters() {
		return reportParameters;
	}

	public void setReportParameters(List<FilterVariableRequest> reportParameters) {
		this.reportParameters = reportParameters;
	}

	@JsonIgnore
	public Map<String, Object> getEffectiveReportParameters(){
		Map<String, Object> parameters = new HashMap<>();

		if (getReportParameters() != null) {
			for(FilterVariableRequest fvar : getReportParameters()) {
				if (!parameters.containsKey(fvar.getName()))
					parameters.put(fvar.getName(), fvar.getValue());
			}			
		}
		return parameters;
	}
}
