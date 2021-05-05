package com.kosshit.anderse.task2_3.web.controller;

import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.service.EmployeeService;

import java.util.List;

public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    public boolean create(Employee employee) {
        return service.create(employee);
    }

    public Employee getById(int employeeId) {
        return service.getById(employeeId);
    }

    public List<Employee> getAll() {
        return service.getAll();
    }

    public void update(Employee employee) {
        service.update(employee);
    }

    public void delete(int employeeId) {
        service.delete(employeeId);
    }
}
