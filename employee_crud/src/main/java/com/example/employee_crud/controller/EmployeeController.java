package com.example.employee_crud.controller;

import com.example.employee_crud.model.Employee;
import com.example.employee_crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    // Ensure that `empId` is not set manually.
    Employee savedEmployee = employeeService.saveEmployee(employee);
    return ResponseEntity.ok(savedEmployee);
}


    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeService.getEmployeeById(id)
                                           .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setName(employeeDetails.getName());
        employee.setAge(employeeDetails.getAge());
        employee.setDateOfBirth(employeeDetails.getDateOfBirth());
        employee.setSalary(employeeDetails.getSalary());
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }
}
