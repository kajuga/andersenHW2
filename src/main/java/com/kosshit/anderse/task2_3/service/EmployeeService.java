package com.kosshit.anderse.task2_3.service;

import com.kosshit.anderse.task2_3.jdbc.EmployeeDAO;
import com.kosshit.anderse.task2_3.model.Employee;

import java.util.List;

public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public boolean create(Employee employee) {
        return employeeDAO.createEmployee(employee);
    }

    public Employee getById(int employeeId) {
        return employeeDAO.getEmployeeById(employeeId);
    }

    public void update(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    public void delete(int employeeId) {
        employeeDAO.deleteById(employeeId);
    }

    public List<Employee> getAll() {
        return employeeDAO.getAllEmployees();
    }
}
