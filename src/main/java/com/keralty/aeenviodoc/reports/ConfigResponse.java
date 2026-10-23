package com.keralty.aeenviodoc.reports;

import com.keralty.aeenviodoc.viewmodel.Response;

public class ConfigResponse extends Response {
	
	private String configXMLData;
	
	public ConfigResponse() {
		super("000", "OK", 0L);
	}

	public String getConfigXMLData() {
		return configXMLData;
	}

	public void setConfigXMLData(String configXMLData) {
		this.configXMLData = configXMLData;
	}
}
