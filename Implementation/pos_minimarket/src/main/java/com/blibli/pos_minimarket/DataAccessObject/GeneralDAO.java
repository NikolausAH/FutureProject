package com.blibli.pos_minimarket.DataAccessObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GeneralDAO extends ConnectionSettings{
    public GeneralDAO() {
    }

    public void executeSet(String sql, String message) {
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        }catch (Exception EX)
        {
            System.out.println(message);
            System.out.println(EX.toString());
        }
    }
}
