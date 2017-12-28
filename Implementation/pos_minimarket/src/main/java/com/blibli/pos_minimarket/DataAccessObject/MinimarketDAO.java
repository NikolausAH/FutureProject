package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Minimarket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MinimarketDAO extends ConnectionSettings {
    private GeneralDAO generalDAO = new GeneralDAO();

    private void insert(Minimarket minimarket) {
        String nama = minimarket.getName();
        String address = minimarket.getAddress();
        String telephoneNumber = minimarket.getTelp_no();
        String email = minimarket.getEmail();
        Double tax = minimarket.getTax();
        String receiptText = minimarket.getReceipt_desc();
        System.out.println(tax);
        String sql = "INSERT INTO mini_market (name,address,email,telp_no,tax,receipt_desc)" +
                "VALUES('" + nama + "','" + address + "', '" + email + "','" + telephoneNumber + "','" + tax + "','" + receiptText + "');";
        String message = "Error Insert Minimarket";
        generalDAO.executeSet(sql, message);
    }

    public void update(Minimarket minimarket) {
        String nama = minimarket.getName();
        String address = minimarket.getAddress();
        String telephoneNumber = minimarket.getTelp_no();
        String email = minimarket.getEmail();
        Double tax = minimarket.getTax();
        String receiptText = minimarket.getReceipt_desc();
        String sql = "UPDATE mini_market SET name ='" + nama + "',address='" + address + "',telp_no='" + telephoneNumber + "',email='" + email + "',tax='" + tax + "',receipt_desc='" + receiptText + "';";
        String message = "Error Update Minimarket";
        generalDAO.executeSet(sql, message);
    }

    public Minimarket show() {
        String sql = "SELECT * FROM mini_market";
        Minimarket minimarket = new Minimarket();
        try {
        this.makeConnection();
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            while (resultSet.next()) {
                minimarket.setName(resultSet.getString("name"));
                minimarket.setAddress(resultSet.getString("address"));
                minimarket.setEmail(resultSet.getString("email"));
                minimarket.setTelp_no(resultSet.getString("telp_no"));
                minimarket.setTax(resultSet.getDouble("tax"));
                minimarket.setReceipt_desc(resultSet.getString("receipt_desc"));
            }
            this.closeConnection();
        }
    } catch (Exception EX) {
        System.out.println("Error MinimarketDAO show");
        System.out.println(EX.toString());
    }
        return minimarket;
}

    public void updateMinimarket(Minimarket minimarket) {
        String sql = "select * from mini_market";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                this.insert(minimarket);
            } else {
                this.update(minimarket);
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error CategoryDAO getAllCategory");
            System.out.println(EX.toString());
        }
    }

    public Double getTax() {
        Double tax = 0.0;
        String sql = "SELECT tax FROM mini_market;";
        String message = "Error MinimarketDAO getTax";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    tax = resultSet.getDouble("tax");
                }
                resultSet.close();
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return tax;
    }
}
