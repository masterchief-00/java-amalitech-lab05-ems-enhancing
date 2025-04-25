package com.kwizera.javaamalitechlabemployeemgtsystem.services.impl;

import com.kwizera.javaamalitechlabemployeemgtsystem.exceptions.*;
import com.kwizera.javaamalitechlabemployeemgtsystem.models.Employee;
import com.kwizera.javaamalitechlabemployeemgtsystem.models.EmployeeDatabase;
import com.kwizera.javaamalitechlabemployeemgtsystem.services.EmployeeManagementServices;
import com.kwizera.javaamalitechlabemployeemgtsystem.services.SuccessCallBack;
import com.kwizera.javaamalitechlabemployeemgtsystem.utils.InputValidationUtil;
import com.kwizera.javaamalitechlabemployeemgtsystem.utils.MainUtil;
import javafx.scene.control.Button;

import java.util.UUID;

public class EmployeeManagementServicesImplementation implements EmployeeManagementServices {
    InputValidationUtil inputValidationUtil = new InputValidationUtil();
    MainUtil util = new MainUtil();

    @Override
    public void createEmployee(String names, String salary, String department, String experience, String rating, Button confirmBtn, EmployeeDatabase<UUID> database, SuccessCallBack onSuccess) throws InvalidNameException, InvalidSalaryException, InvalidDepartmentException, InvalidRatingException, InvalidExperienceYearsException {
        if (inputValidationUtil.invalidNames(names)) {
            throw new InvalidNameException("Invalid names", names);
        }
        if (inputValidationUtil.invalidSalary(salary)) {
            throw new InvalidSalaryException("Invalid number for salary", salary);
        }
        if (department == null || department.equals("None")) {
            throw new InvalidDepartmentException("Please select a department", department);
        }
        if (inputValidationUtil.invalidExperienceYears(experience)) {
            throw new InvalidExperienceYearsException("Invalid input for years of experience", experience);
        }
        if (inputValidationUtil.invalidRating(rating)) {
            throw new InvalidRatingException("Invalid input for performance rating", rating);
        }

        try {
            // if valid, create employee
            UUID uuid = UUID.randomUUID();
            double salaryDbl = Double.parseDouble(salary);
            int experienceInt = Integer.parseInt(experience);
            double ratingDbl = Double.parseDouble(rating);
            Employee<UUID> newEmployee = new Employee<>(uuid, names, department, salaryDbl, ratingDbl, experienceInt, true);

            if (database.addEmployee(newEmployee)) {
                util.displayConfirmation("Employee added successfully");
                confirmBtn.setDisable(true);
                onSuccess.onSuccess();
            } else {
                util.displayError("Employee not added, key/ID provided already exists");
            }

        } catch (RuntimeException e) {
            System.out.println(e);
            util.displayError("Employee not added, something went wrong");
        }
    }
}
