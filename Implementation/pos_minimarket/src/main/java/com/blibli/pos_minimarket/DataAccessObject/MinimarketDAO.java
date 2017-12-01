package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Minimarket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MinimarketDAO extends ConnectionSettings {
    private GeneralDAO generalDAO = new GeneralDAO();

    private void inisialisasiMinimarket() {
        String sql = "INSERT INTO minimarket (nama,address,nomortelp, email, tax, receiptdesc) values('blibli', 'jalan xxx', '087885172045', 'blibli@gmail.com', 30, 'keterangan receipt');";
        String message = "Error inisialisasi Minimarket";
        generalDAO.executeSet(sql, message);
    }

    public void update(Minimarket minimarket) {
        String nama = minimarket.getName();
        String address = minimarket.getAddress();
        String telephoneNumber = minimarket.getTelephoneNumber();
        String email = minimarket.getEmail();
        int tax = minimarket.getTax();
        String receiptText = minimarket.getReceiptDesc();

        String sql = "UPDATE minimarket SET nama ='" + nama + "',address='" + address + "',nomortelp='" + telephoneNumber + "',email='" + email + "',tax='" + tax + "',receiptdesc='" + receiptText + "';";
        String message = "Error Update Minimarket";
        generalDAO.executeSet(sql, message);
    }

    public void updateMinimarket(Minimarket minimarket) {

        String sql = "select * from minimarket";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {

                inisialisasiMinimarket();
            } else {
                update(minimarket);
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
