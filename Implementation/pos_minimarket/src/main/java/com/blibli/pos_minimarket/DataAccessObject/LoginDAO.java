package com.blibli.pos_minimarket.DataAccessObject;


import com.blibli.pos_minimarket.Model.Category;
import com.blibli.pos_minimarket.Model.Pegawai;

import javax.validation.constraints.Null;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends GeneralDAO {

    public Pegawai getByIdAndPassword(Integer employeeId, String password) {
        Pegawai pegawai = new Pegawai();
        String sql = "SELECT * FROM employees WHERE employee_id = '" + employeeId +
                "' AND password = '" + password + "';";
        String message = "Error LoginDAO getByIdAndPassword";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    pegawai.setId(resultSet.getInt("employee_id"));
                    pegawai.setPassword(resultSet.getString("password"));
                    pegawai.setNama(resultSet.getString("name"));
                    pegawai.setIdRole(resultSet.getInt("role_id"));
                    pegawai.setEmail(resultSet.getString("email"));
                }
                resultSet.close();
            }
            else {
                return null;
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return pegawai;
    }
}