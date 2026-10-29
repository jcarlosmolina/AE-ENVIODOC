package com.keralty.aeenviodoc.viewmodel.tipodireccion.filter;


import com.keralty.aeenviodoc.global.TipoDireccionConstants;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;

public class IFTipoDireccionResidencial {

    public IFTipoDireccionResidencial() {
        // Default constructor
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(TipoDireccionConstants.FILTER_NAME_IFTIPODIRECCIONRESIDENCIAL);
        return filterRequest;
    }
}
