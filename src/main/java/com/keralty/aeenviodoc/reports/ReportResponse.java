package com.keralty.aeenviodoc.reports;

import com.keralty.aeenviodoc.viewmodel.Response;

public class ReportResponse extends Response {
	private byte[] reportData;
	
	private String reportName;
	
	public ReportResponse() {
		super("000", "OK", 0L);
	}

	public byte[] getReportData() {
		return reportData;
	}

	public void setReportData(byte[] reportData) {
		this.reportData = reportData;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}
