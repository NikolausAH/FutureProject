package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.EmployeeDAO;
import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.Role;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service //menandakan class ini service
public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    Employee employee = new Employee();

    public void initTable(){
        try {
            employeeDAO.initTable();
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService initTable");
            System.out.println(EX.toString());
        }
    }

    public List<Employee> showAll() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            employeeList = employeeDAO.getAll();
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService showAll");
            System.out.println(EX.toString());
        }
        return employeeList;
    }

    public List<Employee> search(String searchKey) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            employeeList = employeeDAO.search(searchKey);
        } catch (Exception EX) {
            System.out.println("Error CategoryServices search");
            System.out.println(EX.toString());
        }
        return employeeList;
    }

    public List<Role> showAllRole() {
        List<Role> roleList = new ArrayList<>();
        try {
            roleList = employeeDAO.getAllRole();
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService showAllRole");
            System.out.println(EX.toString());
        }
        return roleList;
    }
    public Role getRoleById(Integer role_id) {
        Role role = new Role();
        try {
            role = employeeDAO.getRoleById(role_id);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService getRoleById");
            System.out.println(EX.toString());
        }
        return role;
    }
    public Integer getNextId() {
        Integer nextId = 1;
        try {
            nextId = employeeDAO.getNextId();
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService getNextId");
            System.out.println(EX.toString());
        }
        return nextId;
    }
    public void add(Employee Employee) {
        try {
            employeeDAO.add(Employee);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService Add");
            System.out.println(EX.toString());
        }
    }
    public void update(Employee Employee){
        try{
            employeeDAO.update(Employee);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService Update");
            System.out.println(EX.toString());
        }
    }

    public Employee getById(Integer employeeId){
        try {
            employee = employeeDAO.getById(employeeId);
        }
        catch (Exception EX){
            System.out.println("Error CategoryService Add");
            System.out.println(EX.toString());
        }
        return employee;
    }

    public void delete(Integer employee_id){
        try {
            employeeDAO.delete(employee_id);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService Delete");
            System.out.println(EX.toString());
        }
    }
    public void softDelete(Integer employee_id){
        try {
            employeeDAO.softDelete(employee_id);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService softDelete");
            System.out.println(EX.toString());
        }
    }

    public String encrypt(String password) {
        byte[] bytesOfMessage = password.getBytes();
        byte[] arr = new byte[0];
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            arr = md.digest(bytesOfMessage);}
        catch (Exception EX){
            System.out.println(EX.toString());
            System.out.println("Error ProfileService encrypt");
        }
        return Base64.getEncoder().encodeToString(arr);
    }
}
