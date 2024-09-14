package com.javid.projectv2;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

@Service
public class EmployeeService {

    private static final int MAX_EMPLOYEES = 100;
    private final Map<String, Employee> employees = new HashMap<>();

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Maximum number of employees reached.");
        }
        String fullName = firstName + " " + lastName;
        if (employees.containsKey(fullName)) {
            throw new EmployeeAlreadyAddedException("Employee already exists.");
        }
        Employee employee = new Employee(firstName, lastName);
        employees.put(fullName, employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        Employee employee = employees.remove(fullName);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found.");
        }
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        Employee employee = employees.get(fullName);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found.");
        }
        return employee;
    }

    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }
}
