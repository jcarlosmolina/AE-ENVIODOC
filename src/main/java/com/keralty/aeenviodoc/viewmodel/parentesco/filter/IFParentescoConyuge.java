package com.keralty.aeenviodoc.viewmodel.parentesco.filter;


import com.keralty.aeenviodoc.global.ParentescoConstants;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;

public class IFParentescoConyuge {

    public IFParentescoConyuge() {
        // Default constructor
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(ParentescoConstants.FILTER_NAME_IFPARENTESCOCONYUGE);
        return filterRequest;
    }
}
