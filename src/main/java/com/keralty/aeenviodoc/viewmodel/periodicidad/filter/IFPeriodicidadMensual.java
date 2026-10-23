package com.keralty.aeenviodoc.viewmodel.periodicidad.filter;


import com.keralty.aeenviodoc.global.PeriodicidadConstants;
import com.keralty.aeenviodoc.viewmodel.FilterRequest;

public class IFPeriodicidadMensual {

    public IFPeriodicidadMensual() {
        // Default constructor
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(PeriodicidadConstants.FILTER_NAME_IFPERIODICIDADMENSUAL);
        return filterRequest;
    }
}
