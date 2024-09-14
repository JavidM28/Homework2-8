package com.javid.projectv2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            Employee employee = employeeService.addEmployee(firstName, lastName);
            return ResponseEntity.ok(employee);
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Employee> removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            Employee employee = employeeService.removeEmployee(firstName, lastName);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Employee> findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            Employee employee = employeeService.findEmployee(firstName, lastName);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Collection<Employee>> listEmployees() {
        Collection<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}
