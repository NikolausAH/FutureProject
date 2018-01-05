package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.PromoXY;
import com.blibli.pos_minimarket.Model.Role;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class RoleDAO extends GeneralDAO{

    public Role getRoleById(Integer id) {
        Role role = new Role();
        String sql = "SELECT * from role where role_id = "+id+";";
        String message = "Error RoleDAO getRoleById";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    role.setRole_id(resultSet.getInt("role_id"));
                    role.setName(resultSet.getString("name"));
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
        return role;
}
}
