package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Model.Transaction;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDAO extends ConnectionSettings {

    public void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS public.transaction" +
                "(" +
                "    transactionId SERIAL PRIMARY KEY NOT NULL," +
                "    dateTime TIMESTAMP," +
                "    tax FLOAT," +
                "    discount FLOAT," +
                "    total FLOAT" +
                ");";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        }catch (Exception EX)
        {
            System.out.println("Error TransactionDAO initTable");
            System.out.println(EX.toString());
        }
    }

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
                    transaction.setDateTime(resultSet.getString("dateTime"));
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

    public void addToCart(Integer productId, Integer quantity){
        String sql = "INSERT INTO tempdetail (productId,quantity) VALUES (?,?);";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,productId);
            preparedStatement.setInt(2,quantity);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        }catch (Exception EX){
            System.out.print("Error Transaction DAO addToCart");
            System.out.print(EX.toString());
        }

    }

    public List<Product> getFromCart(){
        String sql = "SELECT * FROM tempdetail ORDER BY productid;";
//                +
//                     "ALTER SEQUENCE tempdetail_productid_seq RESTART WITH 1;";

        List<Product> productList = new ArrayList<>();
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            this.closeConnection();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getInt("productId"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    productList.add(product);
                }
                resultSet.close();
            }
            preparedStatement.close();
        }catch (Exception EX){
            System.out.print("Error Transaction DAO getFromCart");
            System.out.print(EX.toString());
        }
        return productList;
    }

    public void add(Transaction transaction) {
        String sql = "INSERT INTO transaction (date_time,tax,discount,total) VALUES (?,?,?,?);";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getDateTime());
            preparedStatement.setDouble(2,transaction.getTax());
            preparedStatement.setDouble(3, transaction.getDiscount());
            preparedStatement.setDouble(4, transaction.getTotal());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error TransactionDAO Add");
            System.out.println(EX.toString());
        }
    }

    public Integer getNextId(){
        Integer nextId = 1;
        String sql = "SELECT transaction_transactionid_seq.last_value FROM transaction_transactionid_seq;";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null)  {
                while (resultSet.next()){
                    nextId+=resultSet.getInt("last_value");
                }
            }
            else {
                System.out.println("TransactionDao getNextId null ResultSet");
            }
            preparedStatement.close();
            this.closeConnection();
        }
        catch (Exception EX){
            System.out.println("Error TransactionDAO getNextId");
            System.out.println(EX.toString());
        }
        return nextId;
    }
}