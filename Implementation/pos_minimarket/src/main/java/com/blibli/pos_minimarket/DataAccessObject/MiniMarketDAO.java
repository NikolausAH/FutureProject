package com.blibli.pos_minimarket.DataAccessObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MiniMarketDAO extends ConnectionSettings {
    public MiniMarketDAO() {
    }

    public Double getTax(){
        Double tax = 0.0;
        String sql = "SELECT tax FROM mini_market;";
        String message = "Error TransactionDAO getNextId";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null)  {
                while (resultSet.next()){
                    tax = resultSet.getDouble("tax");
                }
            }
            this.closeConnection();
        }
        catch (Exception EX){
            System.out.println(message);
            System.out.println(EX.toString());
        }
        tax /=100;
        return tax;
    }
}
