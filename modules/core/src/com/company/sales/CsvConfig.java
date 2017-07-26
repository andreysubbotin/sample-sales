/*
 * Copyright (c) ${YEAR} ${PACKAGE_NAME}
 */

package com.company.sales;


import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;

@Source(type = SourceType.APP)
public interface CsvConfig extends Config {

    /**
     * Directory to store CSV files
     */
    @Property("sales.csvDir")
    String getCsvDir();
}
