/*
 * Copyright (c) 2017 com.company.sales.service
 */
package com.company.sales.service;

import com.company.sales.entity.Order;

import java.util.Collection;

public interface ExportCsvService {
    String NAME = "sales_ExportCsvService";

    /**
     * Stores selected orders into CSV file on the disk
     * @param orders to store
     */
    void export(Collection<Order> orders);
}