package com.julioluis.noahrdsystem.utils;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class MapFilter {

    public static FilterProvider getFilterProvider(String ... fields) {
        SimpleBeanPropertyFilter beanFilter = SimpleBeanPropertyFilter.serializeAllExcept(fields);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("memberFilter",beanFilter);
        return filterProvider;
    }
}
