package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Minimarket;

import java.sql.ResultSet;
import java.sql.Statement;

public class MinimarketDAO extends MyConnection{
    private void inisialisasiMinimarket(){
        String sql = "INSERT INTO minimarket (nama,address,nomortelp, email, tax, receiptdesc) values('blibli', 'jalan xxx', '087885172045', 'blibli@gmail.com', 30, 'keterangan receipt');";
        try{
            Statement state = c.createStatement();
            state.executeQuery(sql);
            state.close();
        }
        catch(Exception EX){
            System.out.println("Error MinimarketDAO inisialisasiMinimarket");
            System.out.println(EX.toString());
        }
    }
    private void update(Minimarket minimarket){
        String nama  = minimarket.getName();
        String address = minimarket.getAddress();
        String telephoneNumber = minimarket.getTelephoneNumber();
        String email = minimarket.getEmail();
        int tax = minimarket.getTax();
        String receiptText = minimarket.getReceiptDesc();

        String sql = "UPDATE minimarket SET nama ='"+nama+"',address='"+address+"',nomortelp='"+telephoneNumber+"',email='"+email+"',tax='"+tax+"',receiptdesc='"+receiptText+"';";
        try{
            Statement state = c.createStatement();
            state.executeUpdate(sql);
            state.close();
        } catch (Exception EX) {
            System.out.println("Error MinimarketDAO update");
            System.out.println(EX.toString());
        }
    }
    public void updateMinimarket(Minimarket minimarket) {

        String sql = "select * from minimarket";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs == null) {

                inisialisasiMinimarket();
                }

            else{
                update(minimarket);
            }
                state.close();

        } catch (Exception EX) {
            System.out.println("Error CategoryDAO getAllCategory");
            System.out.println(EX.toString());
        }

    }

}
