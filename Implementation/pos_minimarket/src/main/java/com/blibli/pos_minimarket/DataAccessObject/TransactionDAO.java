package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Transaction;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDAO extends ConnectionSettings {

    public List<Transaction> getAll() {
        List<Transaction> transactionList = new ArrayList<>();

        String sql = "SELECT * FROM transaction";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            this.closeConnection();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(resultSet.getInt("transactionId"));
                    transaction.setDate(resultSet.getString("date"));
                    transaction.setTime(resultSet.getString("time"));
                    transaction.setTax(resultSet.getDouble("tax"));
                    transaction.setDiscount(resultSet.getDouble("discount"));
                    transaction.setTotal(resultSet.getDouble("total"));
                    transactionList.add(transaction);
                }
                resultSet.close();
            }
            preparedStatement.close();
        } catch (Exception EX) {
            System.out.println("Error TransactionDAO getAll");
            System.out.println(EX.toString());
        }
        return transactionList;
    }

    public void add(Transaction transaction) {
        String sql = "INSERT INTO transaction (date,time,tax,discount,total,status) VALUES (?,?,?,?,?,?);";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getDate());
            preparedStatement.setString(2, transaction.getTime());
            preparedStatement.setDouble(3,transaction.getTax());
            preparedStatement.setDouble(4, transaction.getDiscount());
            preparedStatement.setDouble(5, transaction.getTotal());
            preparedStatement.setString(6, transaction.getStatus());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error TransactionDAO Add");
            System.out.println(EX.toString());
        }
    }
}