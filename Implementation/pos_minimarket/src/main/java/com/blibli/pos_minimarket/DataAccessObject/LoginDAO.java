package com.blibli.pos_minimarket.DataAccessObject;


import com.blibli.pos_minimarket.Model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class LoginDAO extends GeneralDAO {

    public Employee getByIdAndPassword(Integer employeeId, String password) {
        Employee employee = new Employee();
        RoleDAO roleDAO = new RoleDAO();
        String sql = "SELECT * FROM employees WHERE employee_id = '" + employeeId +
                "' AND password = '" + password + "' AND status = 'active';";
        String message = "Error LoginDAO getByIdAndPassword";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    employee.setEmployee_Id(resultSet.getInt("employee_id"));
                    employee.setPassword(resultSet.getString("password"));
                    employee.setName(resultSet.getString("name"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setRole(roleDAO.getRoleById(resultSet.getInt("role_id")));
                } while (resultSet.next());
                resultSet.close();
            } else {
                return null;
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return employee;
    }
}