package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDAO extends ConnectionSettings {

    /*
    public List<Transaction> getAllTransaction() {
        List<Transaction> transactionList = new ArrayList<>();

        String sql = "select * from Transaction";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    Transaction transaction = new Transaction();

                    transaction.setTransactionId(rs.getInt("transactionId"));
                    transaction.setDate(rs.getString("date"));
                    transaction.setTime(rs.getString("time"));
                    transaction.setTax(rs.getDouble("tax"));
                    transaction.setTotal(rs.getDouble("total"));
                    transaction.setDiscount(rs.getDouble("discount"));
                    transaction.setDiscountPId(rs.getInt("discountPId"));
                    transaction.setEmployeeId(rs.getInt("employeeId"));
                    transactionList.add(transaction);
                }
            }
            rs.close();
            state.close();
        } catch (Exception EX) {
            System.out.println("Error TransactionDAO getAllTransaction");
            System.out.println(EX);
        }
        return transactionList;
    }
    */
}
