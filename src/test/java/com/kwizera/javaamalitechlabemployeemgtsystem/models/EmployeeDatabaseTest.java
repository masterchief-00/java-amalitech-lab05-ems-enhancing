package com.kwizera.javaamalitechlabemployeemgtsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDatabaseTest {
    private EmployeeDatabase<UUID> database;

    @BeforeEach
    void setup() {
        database = new EmployeeDatabase<>();
    }

    @Test
    @DisplayName("TEST: Add employee")
    void testCreateEmployee() {
        final Employee<UUID> testEmployee = new Employee<>(UUID.randomUUID(), "John Doe", "Engineering", 250000.00, 3.5, 5, true);

        boolean result = database.addEmployee(testEmployee);

        assertTrue(result, "Employee should be added successfully");
    }

    @Test
    @DisplayName("TEST: Avoid duplicate IDs")
    void testCreateEmployeeWithDuplicates() {
        UUID employeeId = UUID.randomUUID();
        final Employee<UUID> testEmployee1 = new Employee<>(employeeId, "John Doe", "Engineering", 250000.00, 3.5, 5, true);
        final Employee<UUID> testEmployee2 = new Employee<>(employeeId, "John Doe", "Engineering", 250000.00, 3.5, 5, true);

        database.addEmployee(testEmployee1);
        boolean result = database.addEmployee(testEmployee2);

        assertFalse(result, "Adding an employee with an existing ID should fail.");
    }

    @Test
    @DisplayName("TEST: Search employee - non-empty list")
    void testForSuccessfulEmployeeSearch() {
        final Employee<UUID> testEmployee1 = new Employee<>(UUID.randomUUID(), "John Doe", "Engineering", 250000.00, 3.5, 5, true);
        final Employee<UUID> testEmployee2 = new Employee<>(UUID.randomUUID(), "Kalinda Vital", "HR", 550000.00, 2.5, 2, true);

        database.addEmployee(testEmployee1);
        database.addEmployee(testEmployee2);

        List<Employee<UUID>> employeesList = database.getEmployeeBySearchTerm("doe");

        assertFalse(employeesList.isEmpty(), "The search results should not be empty");

        Employee<UUID> firstOnTheList = employeesList.getFirst();

        assertEquals("John Doe", firstOnTheList.getName());
        assertEquals("Engineering", firstOnTheList.getDepartment());
        assertEquals(250000.00, firstOnTheList.getSalary());
    }

    @Test
    @DisplayName("TEST: Search employee - empty list")
    void testForEmptyEmployeeSearch() {
        final Employee<UUID> testEmployee1 = new Employee<>(UUID.randomUUID(), "John Doe", "Engineering", 250000.00, 3.5, 5, true);
        final Employee<UUID> testEmployee2 = new Employee<>(UUID.randomUUID(), "Kalinda Vital", "HR", 550000.00, 2.5, 2, true);

        database.addEmployee(testEmployee1);
        database.addEmployee(testEmployee2);

        List<Employee<UUID>> employeesList = database.getEmployeeBySearchTerm("Kalisa");

        assertTrue(employeesList.isEmpty(), "The search results should be empty");
    }

    @Test
    @DisplayName("TEST: Delete employee by ID")
    void testForSuccessfulEmployeeDeletion() {
        final Employee<UUID> testEmployee1 = new Employee<>(UUID.randomUUID(), "John Doe", "Engineering", 250000.00, 3.5, 5, true);

        database.addEmployee(testEmployee1);
        boolean result = database.removeEmployee(testEmployee1.getEmployeeId());

        assertTrue(result, "Employee should be deleted successfully");
    }

    @Test
    @DisplayName("TEST: Delete employee - check bad IDs")
    void testForNonExistingIdInEmployeeDeletion() {
        UUID fakeId = UUID.randomUUID();
        final Employee<UUID> testEmployee1 = new Employee<>(UUID.randomUUID(), "John Doe", "Engineering", 250000.00, 3.5, 5, true);

        database.addEmployee(testEmployee1);
        boolean result = database.removeEmployee(fakeId);

        assertFalse(result, "The provided is employee is fake. The operation should not proceed");
    }
}
