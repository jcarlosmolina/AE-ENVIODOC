package com.keralty.aeenviodoc.reports;

public class ConfigRequest {

	private String reportConfigPath;
	
	public ConfigRequest() {
		reportConfigPath = "";
	}

	public String getReportConfigPath() {
		return reportConfigPath;
	}

	public void setReportConfigPath(String reportConfigPath) {
		this.reportConfigPath = reportConfigPath;
	}
}
