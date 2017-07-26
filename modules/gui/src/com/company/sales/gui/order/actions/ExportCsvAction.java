package com.company.sales.gui.order.actions;

import com.company.sales.entity.Order;
import com.company.sales.service.ExportCsvService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowManagerProvider;
import com.haulmont.cuba.gui.components.AbstractAction;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;

import java.util.Set;

/**
 * Action to store selected orders in the table into CSV file on the disk
 */
public class ExportCsvAction extends AbstractAction {
    protected Table<Order> owner;

    public ExportCsvAction(Table<Order> owner) {
        super("exportCsv");
        this.owner = owner;
    }

    @Override
    public void actionPerform(Component component) {
        ExportCsvService exportCsvService = AppBeans.get(ExportCsvService.NAME);
        Set<Order> selected = owner.getSelected();
        WindowManagerProvider wmp = AppBeans.get(WindowManagerProvider.NAME);
        if (!selected.isEmpty()) {
            //ExportCsvService stores selected orders into CSV file on the disk
            exportCsvService.export(selected);
            wmp.get().showNotification(messages.getMessage(ExportCsvAction.class, "exportCsv.success"));
        } else {
            wmp.get().showNotification(messages.getMessage(ExportCsvAction.class, "exportCsv.selectOrder"));
        }
    }
}
