package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.EmployeeDAO;
import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.Role;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service //menandakan class ini service
public class EmployeeService {
    private EmployeeDAO EmployeeDAO = new EmployeeDAO();

    public List<Employee> showAll() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            employeeList = EmployeeDAO.getAll();
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService showAll");
            System.out.println(EX.toString());
        }
        return employeeList;
    }
    public List<Role> showAllRole() {
        List<Role> roleList = new ArrayList<>();
        try {
            roleList = EmployeeDAO.getAllRole();
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService showAllRole");
            System.out.println(EX.toString());
        }
        return roleList;
    }
    public void add(Employee Employee) {
        try {
            EmployeeDAO.add(Employee);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService Add");
            System.out.println(EX.toString());
        }
    }
    public void update(Employee Employee){
        try{
            EmployeeDAO.update(Employee);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService Update");
            System.out.println(EX.toString());
        }
    }
    public void delete(Integer employee_id){
        try {
            EmployeeDAO.delete(employee_id);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService Delete");
            System.out.println(EX.toString());
        }
    }
    public void softDelete(Integer employee_id){
        try {
            EmployeeDAO.delete(employee_id);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService softDelete");
            System.out.println(EX.toString());
        }
    }
}
