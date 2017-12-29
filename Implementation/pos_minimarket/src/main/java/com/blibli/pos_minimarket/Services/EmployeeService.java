package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.EmployeeDAO;
import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //menandakan class ini service
public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public List<Employee> showAll() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            employeeList = employeeDAO.getAll();
        } catch (Exception EX) {
            System.out.println("Error EmployeeService showAll");
            System.out.println(EX.toString());
        }
        return employeeList;
    }

    public List<Role> showAllRole() {
        List<Role> roleList = new ArrayList<>();
        try {
            roleList = employeeDAO.getAllRole();
        } catch (Exception EX) {
            System.out.println("Error EmployeeService showAllRole");
            System.out.println(EX.toString());
        }
        return roleList;
    }

    public Role getRoleById(Integer role_id) {
        Role role = new Role();
        try {
            role = employeeDAO.getRoleById(role_id);
        } catch (Exception EX) {
            System.out.println("Error EmployeeService getRoleById");
            System.out.println(EX.toString());
        }
        return role;
    }

    public Integer getNextId() {
        Integer nextId = 1;
        try {
            nextId = employeeDAO.getNextId();
        } catch (Exception EX) {
            System.out.println("Error EmployeeService getNextId");
            System.out.println(EX.toString());
        }
        return nextId;
    }

    public void add(Employee Employee) {
        try {
            employeeDAO.add(Employee);
        } catch (Exception EX) {
            System.out.println("Error EmployeeService Add");
            System.out.println(EX.toString());
        }
    }

    public void update(Employee Employee) {
        try {
            employeeDAO.update(Employee);
        } catch (Exception EX) {
            System.out.println("Error EmployeeService Update");
            System.out.println(EX.toString());
        }
    }

    public void delete(Integer employee_id) {
        try {
            employeeDAO.delete(employee_id);
        } catch (Exception EX) {
            System.out.println("Error EmployeeService Delete");
            System.out.println(EX.toString());
        }
    }

    public void softDelete(Integer employee_id) {
        try {
            employeeDAO.softDelete(employee_id);
        } catch (Exception EX) {
            System.out.println("Error EmployeeService softDelete");
            System.out.println(EX.toString());
        }
    }

    public Employee findById(Integer id) {
        Employee employee = new Employee();
        try {
            employee = employeeDAO.getById(id);
        } catch (Exception EX) {
            System.out.println("Error EmployeeService findById");
        }
        return employee;
    }
}

