/*
 * Copyright (c) 2017 com.company.sales.service
 */
package com.company.sales.service;

import com.company.sales.CsvConfig;
import com.company.sales.entity.Order;
import com.google.common.base.Strings;
import com.haulmont.cuba.core.global.Configuration;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

@Service(ExportCsvService.NAME)
public class ExportCsvServiceBean implements ExportCsvService {
    @Inject
    private Configuration configuration;

    @Override
    public void export(Collection<Order> orders) {
        CsvConfig csvConfig = configuration.getConfig(CsvConfig.class);
        if (Strings.isNullOrEmpty(csvConfig.getCsvDir())) {
            throw new RuntimeException("Directory for CSV does not exists");
        }
        //directory to store CSV files
        File csvDir = new File(csvConfig.getCsvDir());
        if (!csvDir.exists()) {
            throw new RuntimeException("Directory for CSV does not exists");
        }
        try {
            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(csvDir, System.currentTimeMillis() + ".csv")))) {
                //Create CSV file with fields: order date, customer name and order amount
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (Order order : orders) {
                    csvWriter.writeNext(new String[]{
                            order.getDate() != null ? simpleDateFormat.format(order.getDate()) : "",
                            order.getCustomer() != null ? order.getCustomer().getInstanceName() : "",
                            order.getAmount() != null ? order.getAmount().toString() : ""
                    });
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while write CSV", e);
        }
    }
}