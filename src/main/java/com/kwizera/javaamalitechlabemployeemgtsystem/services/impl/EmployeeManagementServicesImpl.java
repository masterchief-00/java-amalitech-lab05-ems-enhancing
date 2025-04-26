package com.kwizera.javaamalitechlabemployeemgtsystem.services.impl;

import com.kwizera.javaamalitechlabemployeemgtsystem.exceptions.*;
import com.kwizera.javaamalitechlabemployeemgtsystem.models.Employee;
import com.kwizera.javaamalitechlabemployeemgtsystem.models.EmployeeDatabase;
import com.kwizera.javaamalitechlabemployeemgtsystem.services.EmployeeManagementServices;
import com.kwizera.javaamalitechlabemployeemgtsystem.services.SuccessCallBack;
import com.kwizera.javaamalitechlabemployeemgtsystem.utils.InputValidationUtil;
import com.kwizera.javaamalitechlabemployeemgtsystem.utils.MainUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.util.List;
import java.util.UUID;

public class EmployeeManagementServicesImpl implements EmployeeManagementServices {
    private final InputValidationUtil inputValidationUtil = new InputValidationUtil();
    private final MainUtil util = new MainUtil();
    private final EmployeeDatabase<UUID> database;

    public EmployeeManagementServicesImpl(EmployeeDatabase<UUID> database) {
        this.database = database;
    }

    @Override
    public void createEmployee(String names, String salary, String department, String experience, String rating, Button confirmBtn, SuccessCallBack onSuccess) throws InvalidNameException, InvalidSalaryException, InvalidDepartmentException, InvalidRatingException, InvalidExperienceYearsException {
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
            util.displayError("Employee not added, something went wrong");
        }
    }

    @Override
    public double departAverageSalary(String dept) {
        return database.calculateAverageSalaryByDepartment(dept);
    }

    @Override
    public List<Employee<UUID>> topEarningEmployees(int N) {
        return database.getTopEarners(N);
    }

    @Override
    public long giveRaiseToTopPerformers(double minScore, double increaseRate) {
        return database.giveRaiseToTopPerformers(minScore, increaseRate);
    }

    @Override
    public List<Employee<UUID>> employeesOfSalaryRange(double minSalary, double maxSalary) {
        return database.getEmployeeBySalaryRange(minSalary, maxSalary);
    }

    @Override
    public List<Employee<UUID>> employeesOfSearchTerm(String searchTerm) {
        return database.getEmployeeBySearchTerm(searchTerm);
    }

    @Override
    public List<Employee<UUID>> employeesSortedByExperience() {
        return database.sortByExperience();
    }

    @Override
    public List<Employee<UUID>> employeesSortedBySalary() {
        return database.sortBySalary();
    }

    @Override
    public List<Employee<UUID>> employeesSortedByPerformance() {
        return database.sortByPerformance();
    }

    @Override
    public List<Employee<UUID>> employeesFilteredByRating(double minRating) {
        return database.getEmployeeByPerformanceRating(minRating);
    }

    @Override
    public List<Employee<UUID>> employeesFilteredByDepartment(String department) {
        return database.getEmployeesByDepartment(department);
    }

    @Override
    public String generateReport() {
        return database.getReport();
    }

    @Override
    public boolean deleteEmployee(UUID employeeId) {
        return database.removeEmployee(employeeId);
    }

    @Override
    public boolean updateEmployee(String field, Employee<UUID> emp) {
        return database.updateEmployeeDetails(emp.getEmployeeId(), "name", emp);
    }

    @Override
    public ObservableList<Employee<UUID>> retrieveAllEmployees() {
        return database.getAllEmployees();
    }

    @Override
    public void resetDb() throws NullEmployeeDbException {
        if (database == null) throw new NullEmployeeDbException("Invalid database reference");
        database.reset();
    }
}
