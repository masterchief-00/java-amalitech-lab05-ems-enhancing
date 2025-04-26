package com.kwizera.javaamalitechlabemployeemgtsystem.services;

import com.kwizera.javaamalitechlabemployeemgtsystem.exceptions.*;
import com.kwizera.javaamalitechlabemployeemgtsystem.models.Employee;
import com.kwizera.javaamalitechlabemployeemgtsystem.models.EmployeeDatabase;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.util.List;
import java.util.UUID;

public interface EmployeeManagementServices {
    void createEmployee(String names, String salary, String department, String experience, String rating, Button confirmBtn, SuccessCallBack onSuccess) throws InvalidNameException, InvalidSalaryException, InvalidDepartmentException, InvalidRatingException, InvalidExperienceYearsException;

    boolean deleteEmployee(UUID employeeId);

    boolean updateEmployee(String field, Employee<UUID> emp);

    ObservableList<Employee<UUID>> retrieveAllEmployees();

    double departAverageSalary(String dept);

    List<Employee<UUID>> topEarningEmployees(int N);

    long giveRaiseToTopPerformers(double minScore, double increaseRate);

    List<Employee<UUID>> employeesOfSalaryRange(double minSalary, double maxSalary);

    List<Employee<UUID>> employeesOfSearchTerm(String searchTerm);

    List<Employee<UUID>> employeesSortedByExperience();

    List<Employee<UUID>> employeesSortedBySalary();

    List<Employee<UUID>> employeesSortedByPerformance();

    List<Employee<UUID>> employeesFilteredByRating(double minRating);

    List<Employee<UUID>> employeesFilteredByDepartment(String department);

    String generateReport();

    void resetDb() throws NullEmployeeDbException;
}
