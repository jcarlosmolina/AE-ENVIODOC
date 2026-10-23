package com.keralty.aeenviodoc.viewmodel.eps.filter;


import com.keralty.aeenviodoc.global.EpsConstants;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;

public class IFEPSSanitas {

    public IFEPSSanitas() {
        // Default constructor
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(EpsConstants.FILTER_NAME_IFEPSSANITAS);
        return filterRequest;
    }
}
