package com.zipe.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println(DataSourceHolder.getDataSourceName());
        return DataSourceHolder.getDataSourceName();
    }
}
