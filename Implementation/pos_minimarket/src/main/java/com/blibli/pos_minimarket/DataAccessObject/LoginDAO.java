package com.blibli.pos_minimarket.DataAccessObject;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends GeneralDAO {

    public String getRole(String name, String password) throws SQLException {
        String role = null;
        String sql = "SELECT EXISTS(SELECT * from employees " +
                "WHERE name = '" + name + "' " +
                "AND password = '" + password + "');";
        String sql2 = "SELECT role.name from employees NATURAL JOIN role " +
                "WHERE employees.name='" + name + "';";
        String message = "Error LoginDAO";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            PreparedStatement preparedStatement2 = this.connection.prepareStatement(sql2);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (resultSet.getBoolean(0) == true) {
                role = resultSet2.getString("role");
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(EX.toString());
            System.out.println(message);
        }
        return role;
    }
}