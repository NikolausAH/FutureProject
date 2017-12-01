package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAO extends ConnectionSettings implements InterfaceDAO<Employee, Integer, String> {


    @Override
    public void initTable() {

    }

    @Override
    public List<Employee> getAll() {
        List<Employee> EmployeeList = new ArrayList<>();
        String sql = "SELECT employee_id,name,email,role_id from employees;";
        try {
            this.makeConnection();
            Statement preparedStatement = this.connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmployee_Id(resultSet.getInt(employee.getEmployee_Id()));
                    employee.setName(resultSet.getString(employee.getName()));
                    employee.setRole_id(resultSet.getInt(employee.getRole_id()));
                    employee.setEmail(resultSet.getString(employee.getEmail()));
                    EmployeeList.add(employee);
                }
                resultSet.close();
            }
            this.closeConnection();

        } catch (Exception EX) {
            System.out.println("Error EmployeeDAO getAll");
            System.out.println(EX.toString());
        }
        return EmployeeList;
    }

    @Override
    public Employee getById(Integer key) {
        return null;
    }

    @Override
    public List<Employee> search(String nama) {
        List<Employee> EmployeeList = new ArrayList<>();
        String sql = "SELECT id,nama,email,namarole,Employee.idrole,namarole FROM employees join role on (Employee.idrole = role.idrole) where nama LIKE"
                + nama + ";";
        try {
            this.makeConnection();
            Statement preparedStatement = this.connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmployee_Id(resultSet.getInt(employee.getEmployee_Id()));
                    employee.setRole(resultSet.getString(employee.getRole()));
                    employee.setName(resultSet.getString(employee.getName()));
                    employee.setRole_id(resultSet.getInt(employee.getRole_id()));
                    employee.setEmail(resultSet.getString(employee.getEmail()));
                    EmployeeList.add(employee);
                }
                resultSet.close();
            }
            this.closeConnection();

        } catch (Exception EX) {
            System.out.println("Error EmployeeDAO search");
            System.out.println(EX.toString());
        }
        return EmployeeList;
    }

    @Override
    public void add(Employee Employee) {

        String sql = "INSERT INTO employees (nama,idrole,email) VALUES (?,(SELECT idrole FROM role WHERE namarole LIKE ?),?);";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, Employee.getName());
            preparedStatement.setString(2, Employee.getRole());
            preparedStatement.setString(3, Employee.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error EmployeeDAO Add");
            System.out.println(EX.toString());
        }
    }

    @Override
    public void update(Employee Employee) {
        String sql = "UPDATE employees SET nama = ?, idrole = (SELECT idrole from role WHERE namarole LIKE ?) "
                + "WHERE id = ?;";
        try {
            this.makeConnection();
            System.out.println("Run Update Employee");
            System.out.println(Employee.getName());
            System.out.println(Employee.getRole());
            System.out.println(Employee.getEmployee_Id());
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, Employee.getName());
            preparedStatement.setString(2, Employee.getRole());
            preparedStatement.setInt(3, Employee.getEmployee_Id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error EmployeeDAO Update");
            System.out.println(EX.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM employees where id=?;";
        try {
            this.makeConnection();
            System.out.println("Run Delete Employee");
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error EmployeeDAO Delete");
            System.out.println(EX.toString());
        }

    }

    @Override
    public void softDelete(Integer integer) {

    }
}
