package com.kwizera.javaamalitechlabemployeemgtsystem.controllers;

import com.kwizera.javaamalitechlabemployeemgtsystem.exceptions.*;
import com.kwizera.javaamalitechlabemployeemgtsystem.models.Employee;
import com.kwizera.javaamalitechlabemployeemgtsystem.models.EmployeeDatabase;
import com.kwizera.javaamalitechlabemployeemgtsystem.services.impl.EmployeeManagementServicesImplementation;
import com.kwizera.javaamalitechlabemployeemgtsystem.session.SessionManager;
import com.kwizera.javaamalitechlabemployeemgtsystem.utils.InputValidationUtil;
import com.kwizera.javaamalitechlabemployeemgtsystem.utils.MainUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class AddEmployeePageController {
    SessionManager<UUID> instance = SessionManager.getInstance();
    private EmployeeDatabase<UUID> database;
    MainUtil util = new MainUtil();

    private EmployeeManagementServicesImplementation employeeServices;

    @FXML
    public TextField nameInput;
    @FXML
    public Label nameErrorLabel;
    @FXML
    public TextField salaryInput;
    @FXML
    public Label salaryErrorLabel;
    @FXML
    public Label departmentErrorLabel;
    @FXML
    public ComboBox<String> selectDepartmentInput;
    @FXML
    public TextField experienceInput;
    @FXML
    public Label experienceErrorLabel;
    @FXML
    public TextField ratingInput;
    @FXML
    public Label ratingErrorLabel;
    @FXML
    public Button submitEmployeeBtn;
    @FXML
    public Button cancelBtn;

    @FXML
    private void onConfirmClicked() {

        // read data from inputs
        String names = nameInput.getText();
        String salary = salaryInput.getText();
        String department = selectDepartmentInput.getValue();
        String experience = experienceInput.getText();
        String rating = ratingInput.getText();

        try {
            employeeServices.createEmployee(names, salary, department, experience, rating, submitEmployeeBtn, database, this::resetForm);
        } catch (InvalidNameException err) {
            nameErrorLabel.setText(err.getMessage());
            nameErrorLabel.setVisible(true);
            nameInput.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        } catch (InvalidSalaryException err) {
            salaryErrorLabel.setText(err.getMessage());
            salaryErrorLabel.setVisible(true);
            salaryInput.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        } catch (InvalidDepartmentException err) {
            departmentErrorLabel.setText("Please select a department");
            departmentErrorLabel.setVisible(true);
            selectDepartmentInput.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        } catch (InvalidExperienceYearsException err) {
            experienceErrorLabel.setText(err.getMessage());
            experienceErrorLabel.setVisible(true);
            experienceInput.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        } catch (InvalidRatingException err) {
            ratingErrorLabel.setText(err.getMessage());
            ratingErrorLabel.setVisible(true);
            ratingInput.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
    }

    public void onCancelClicked() throws IOException {
        // closes the window
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        // get employee database from session instance
        database = instance.getDatabase();
        employeeServices = new EmployeeManagementServicesImplementation();

        if (database == null) {
            util.displayError("Database initialization failed, please try again later");
            return;
        } else {
            selectDepartmentInput.getItems().addAll("Engineering", "Marketing", "Sales", "HR");
        }
    }

    private void resetForm() {
        nameInput.clear();
        salaryInput.clear();
        ratingInput.clear();
        experienceInput.clear();
        resetErrorValidation();
    }

    private void resetErrorValidation() {
        nameErrorLabel.setVisible(false);
        nameInput.setStyle("");

        salaryErrorLabel.setVisible(false);
        salaryInput.setStyle("");

        departmentErrorLabel.setVisible(false);
        selectDepartmentInput.setStyle("");

        experienceErrorLabel.setVisible(false);
        experienceInput.setStyle("");

        ratingErrorLabel.setVisible(false);
        ratingInput.setStyle("");
    }
}
