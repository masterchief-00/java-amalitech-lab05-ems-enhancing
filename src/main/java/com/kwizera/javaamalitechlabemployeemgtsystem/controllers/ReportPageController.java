package com.kwizera.javaamalitechlabemployeemgtsystem.controllers;

import com.kwizera.javaamalitechlabemployeemgtsystem.models.EmployeeDatabase;
import com.kwizera.javaamalitechlabemployeemgtsystem.services.EmployeeManagementServices;
import com.kwizera.javaamalitechlabemployeemgtsystem.services.impl.EmployeeManagementServicesImpl;
import com.kwizera.javaamalitechlabemployeemgtsystem.session.SessionManager;
import com.kwizera.javaamalitechlabemployeemgtsystem.utils.CustomLogger;
import com.kwizera.javaamalitechlabemployeemgtsystem.utils.MainUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.util.UUID;

public class ReportPageController {

    SessionManager<UUID> instance = SessionManager.getInstance();
    private EmployeeDatabase<UUID> database;
    private EmployeeManagementServices employeeManagementServices;
    MainUtil util = new MainUtil();

    @FXML
    public AnchorPane scrollPane;

    @FXML
    public TextArea reportTextArea;

    @FXML
    private void initialize() {
        // gets employee database from session instance
        database = instance.getDatabase();

        if (database == null) {
            CustomLogger.log(CustomLogger.LogLevel.ERROR, "Reports screen: Database initialization failed.");
            util.displayError("Database initialization failed, please try again later");
            return;
        } else {
            CustomLogger.log(CustomLogger.LogLevel.INFO, "Reports screen: Database loaded.");
            employeeManagementServices = new EmployeeManagementServicesImpl(database);
            // invokes method that returns report as a string
            String report = employeeManagementServices.generateReport();

            // populates text area component with the report
            reportTextArea.textProperty().setValue(report);
        }
    }
}
