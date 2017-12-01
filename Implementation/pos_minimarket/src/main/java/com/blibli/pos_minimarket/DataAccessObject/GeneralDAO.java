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
            preparedStatement.executeUpdate(); //executeUpdate selain select
            preparedStatement.close();
            this.closeConnection();
        }catch (Exception EX)
        {
            System.out.println(message);
            System.out.println(EX.toString());
        }
    }
    public Integer getNextId(String sql,String message){
        Integer nextId = 1;
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null)  {
                while (resultSet.next()){
                    nextId+=resultSet.getInt("last_value");
                }
            }
            this.closeConnection();
        }
        catch (Exception EX){
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return nextId;
    }
}
