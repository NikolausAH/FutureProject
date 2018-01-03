package com.blibli.pos_minimarket.DataAccessObject;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class ReportDAO extends ConnectionSettings {
    public ReportDAO(){}

    public Integer getTransactionAmount(Integer productId){
        Integer transactionAmount = 0;

        String sql = "SELECT count(DISTINCT transaction_id) from transaction_detail WHERE product_id='"+productId+"';";
        String message = "Error ReportDAO getTransactionAmount";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    transactionAmount = resultSet.getInt("count");
                }
                resultSet.close();
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return transactionAmount;
    }

    public Integer getQuantityAmount(Integer productId){
        Integer quantityAmount = 0;

        String sql = "SELECT sum(quantity) from transaction_detail where product_id='"+productId+"';";
        String message = "Error ReportDAO getQuantityAmount";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    quantityAmount = resultSet.getInt("sum");
                }
                resultSet.close();
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return quantityAmount;
    }
}
