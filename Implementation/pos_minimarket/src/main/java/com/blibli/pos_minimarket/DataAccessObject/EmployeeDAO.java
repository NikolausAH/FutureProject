package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.Role;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class EmployeeDAO extends ConnectionSettings implements InterfaceDAO<Employee, Integer, String> {
    private GeneralDAO generalDAO = new GeneralDAO();
    @Override
    public void initTable() {
        String sqlRole = "CREATE TABLE IF NOT EXISTS public.role" +
                "(" +
                "    role_id SERIAL PRIMARY KEY NOT NULL," +
                "    name VARCHAR(25) NOT NULL" +
                ");";

        String sqlEmployee = "CREATE TABLE IF NOT EXISTS public.employees" +
                "(" +
                "    employee_id SERIAL PRIMARY KEY NOT NULL," +
                "    password VARCHAR(25) NOT NULL," +
                "    name VARCHAR(25) NOT NULL," +
                "    email VARCHAR(50)," +
                "    role_id INTEGER NOT NULL,"+
                "    status VARCHAR(15) NOT NULL," +
                "    CONSTRAINT role_id___fk FOREIGN KEY (role_id) REFERENCES role (role_id)" +
                ");";
        String messageRole = "Error EmployeeDAO initTable ROLE";
        String messageEmployee = "Error EmployeeDAO initTable employees";
        generalDAO.executeSet(sqlRole,messageRole);
        generalDAO.executeSet(sqlEmployee,messageEmployee);
    }

    public List<Role> getAllRole(){
        List<Role> roleList = new ArrayList<>();
        String sql = "SELECT * FROM role";
        try {
            this.makeConnection();
            Statement preparedStatement = this.connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Role role = new Role();
                    role.setRole_id(resultSet.getInt("role_id"));
                    role.setName(resultSet.getString("name"));
                    roleList.add(role);
                }resultSet.close();
            }this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error EmployeeDAO getAllRole");
            System.out.println(EX.toString());
        }
        return roleList;
    }
    public Role getRoleById(Integer role_id){
        Role role = new Role();
        String sql = "SELECT * FROM role WHERE role_id='"+role_id+"';";
        try {
            this.makeConnection();
            Statement preparedStatement = this.connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    role.setRole_id(resultSet.getInt("role_id"));
                    role.setName(resultSet.getString("name"));
                }resultSet.close();
            }this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error EmployeeDAO getAllRole");
            System.out.println(EX.toString());
        }
        return role;
    }
    public Integer getNextId(){
        Integer nextId = 1;
        String sql = "SELECT employees_employee_id_seq.last_value FROM employees_employee_id_seq;";
        String message = "Error EmployeeDAO getNextId";
        nextId = generalDAO.getNextId(sql,message);
        return nextId;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM employees;";
        try {
            this.makeConnection();
            Statement preparedStatement = this.connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmployee_Id(resultSet.getInt("employee_id"));
                    employee.setName(resultSet.getString("name"));
                    employee.setRole( this.getRoleById(resultSet.getInt("role_id")));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setStatus(resultSet.getString("status"));
                    employeeList.add(employee);
                }resultSet.close();
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error EmployeeDAO getAll");
            System.out.println(EX.toString());
        }
        return employeeList;
    }

    @Override
    public Employee getById(Integer employee_id) {
        Employee employee = new Employee();
        String sql = "SELECT employee_id, name, email, role_id FROM employees WHERE employee_id="+
                "'"+employee_id+"';";
        try {
            this.makeConnection();
            Statement preparedStatement = this.connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    employee.setEmployee_Id(resultSet.getInt("employee_id"));
                    employee.setName(resultSet.getString("name"));
                    employee.setRole( this.getRoleById(resultSet.getInt("role_id")));
                    employee.setEmail(resultSet.getString("email"));
                }
                resultSet.close();
            }this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error EmployeeDAO getById");
            System.out.println(EX.toString());
        }
        return employee;
    }

    @Override
    public List<Employee> search(String searchKey) {
        List<Employee> employeeList = new ArrayList<>();
        Scanner scanner = new Scanner(searchKey);
//        select * from category where cast(category_id as CHARACTER VARYING) like '%2%' or name like '%o%'
        String sqlString = "SELECT employee_id, name, email, role_id, status FROM employees WHERE name LIKE '%"+searchKey+"%' AND status ='active' ORDER BY employee_id;";
        String sqlInteger = "SELECT employee_id, name, email, role_id, status FROM employees WHERE employee_id = '"+searchKey+"' AND status ='active' OR name LIKE '%"+searchKey+"%' AND status ='active' ORDER BY employee_id;";
        String message = "Error EmployeeDAO search";
        try {
            this.makeConnection();
            ResultSet resultSet;
            if (scanner.hasNextInt()){
                PreparedStatement preparedStatement = this.connection.prepareStatement(sqlInteger);
                resultSet = preparedStatement.executeQuery();
            }else {
                PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
                resultSet = preparedStatement.executeQuery();
            }
            if (resultSet != null) {
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmployee_Id(resultSet.getInt("employee_Id"));
                    employee.setName(resultSet.getString("name"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setRole( this.getRoleById(resultSet.getInt("role_id")));
                    employee.setStatus(resultSet.getString("status"));
                    employeeList.add(employee);
                }
                resultSet.close();
            }
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return employeeList;
    }

    @Override
    public void add(Employee employee) {
        String sql = "INSERT INTO employees (password, name, email, role_id, status)"+
                "VALUES ('"+employee.getPassword()+"','"+employee.getName()+"','"+employee.getEmail()+"',"+
                "'"+employee.getRole().getRole_id()+"','active');";
        String message = "Error EmployeeDAO add";
        generalDAO.executeSet(sql,message);
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = '"+employee.getName()+"', email = '"+employee.getEmail()+"',"+
                "role_id = '"+employee.getRole().getRole_id()+"' WHERE employee_id = '"+employee.getEmployee_Id()+"';";
        String message = "Error EmployeeDAO update";
        generalDAO.executeSet(sql,message);
    }

    @Override
    public void delete(Integer employee_id) {
        String sql = "DELETE FROM employees where employee_id = '"+employee_id+"';";
        String message = "Error EmployeeDAO delete";
        generalDAO.executeSet(sql,message);
    }

    @Override
    public void softDelete(Integer employee_id) {
        String sql = "UPDATE employees SET status = 'non active' where employee_id = '"+employee_id+"';";
        String message = "Error EmployeeDAO softDelete";
        generalDAO.executeSet(sql,message);
    }
    public String getPasswordById(Integer employee_id){
        String sql = "SELECT password from employees where employee_id = "+ employee_id +";";
        String password = null;
        try {
            this.makeConnection();
            Statement preparedStatement = this.connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    password = (resultSet.getString("password"));
                }
                resultSet.close();
            }this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error EmployeeDAO getPasswordById");
            System.out.println(EX.toString());
        }
        return password;
    }
    public void updatePassword(Integer id, String password) {
        String sql = "UPDATE employees SET password = '"+password+"' WHERE employee_id = '"+id+"';";
        String message = "Error EmployeeDAO update";
        generalDAO.executeSet(sql,message);
    }
}