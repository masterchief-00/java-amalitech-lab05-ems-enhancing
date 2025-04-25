package com.kwizera.javaamalitechlabemployeemgtsystem.services;

import com.kwizera.javaamalitechlabemployeemgtsystem.exceptions.*;
import com.kwizera.javaamalitechlabemployeemgtsystem.models.EmployeeDatabase;
import javafx.scene.control.Button;

import java.util.UUID;

public interface EmployeeManagementServices {
    void createEmployee(String names, String salary, String department, String experience, String rating, Button confirmBtn, EmployeeDatabase<UUID> database, SuccessCallBack onSuccess) throws InvalidNameException, InvalidSalaryException, InvalidDepartmentException, InvalidRatingException, InvalidExperienceYearsException;
}
