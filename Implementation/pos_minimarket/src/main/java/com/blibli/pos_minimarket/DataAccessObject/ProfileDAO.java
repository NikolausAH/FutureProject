package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileDAO extends GeneralDAO {
    public void update(Employee employee) {
        GeneralDAO generalDAO = new GeneralDAO();
        String nama = employee.getName();
        Integer id = employee.getEmployee_Id();
        String email = employee.getEmail();
        String sql = "UPDATE employees SET name ='" + nama + "',email='" + email +"'WHERE employee_id ="+id+"';";
        String message = "ProfileDAO update";
        generalDAO.executeSet(sql, message);
    }
    public Employee show(Employee employee) {
        Employee employeeShow = new Employee();
        Integer id = employee.getEmployee_Id();
        String sql = "SELECT * FROM employees WHERE employee_id = " + id + ";";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    employeeShow.setName(resultSet.getString("name"));
                    employeeShow.setEmployee_Id(resultSet.getInt("employee_id"));
                    employeeShow.setEmail(resultSet.getString("email"));
                }
                this.closeConnection();
            }
        } catch (Exception EX) {
            System.out.println("Error ProfileDao show");
            System.out.println(EX.toString());
        }
        return employeeShow;
    }
}
