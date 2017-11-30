package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Product;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO extends ConnectionSettings {
 /*   public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
//
        String sql = "select * from product";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setKode(rs.getInt("kode"));
                    product.setNama(rs.getString("nama"));
                    product.setKategoriId(rs.getInt("kategoriId"));
                    product.setDeskripsi(rs.getString("deskripsi"));
                    product.setStok(rs.getInt("stok"));
                    product.setHarga(rs.getInt("harga"));
                    productList.add(product);
                }
            }
            rs.close();
            state.close();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO getAllProduct");
            System.out.println(EX);
        }
        return productList;
    }

    public void createProduct(Product product) {
        Integer tkode=product.getKode();
        String tNama=product.getNama();
        Integer tkategoriId=product.getKategoriId();
        String tdeskripsi=product.getDeskripsi();
        Integer tstok=0;
        Integer tharga=product.getHarga();
        String sql = "INSERT INTO Product (kode,nama,kategoriid,deskripsi,stok,harga)"
                +"VALUES('"+tkode+"','"+tNama+"','"+tkategoriId+"','"+tdeskripsi+"','"+tstok+"','"+tharga+"');";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            state.executeUpdate(sql);
            state.close();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO createProduct");
            System.out.println(EX);
        }
    }

    public void updateProduct(Product product) {
        Integer tkode=product.getKode();
        String tNama=product.getNama();
        Integer tkategoriId=product.getKategoriId();
        String tdeskripsi=product.getDeskripsi();
        Integer tharga=product.getHarga();
        String sql = "UPDATE Product "
                + "SET NAMA='"+tNama+"',"
                + "KATEGORIID='"+tkategoriId+"',"
                + "DESKRIPSI='"+tdeskripsi+"',"
                + "HARGA='"+tharga+"'"
                +"WHERE KODE='"+tkode+"'";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            state.executeUpdate(sql);
            state.close();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO UpdateProduct");
            System.out.println(EX);
        }
    }

    public void deleteProduct(Integer kode){
        String sql="DELETE FROM Product WHERE KODE='"+kode+"'";
        try {
            Statement state = c.createStatement();
            state.executeUpdate(sql);
            state.close();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO DeleteProduct");
            System.out.println(EX);
        }
    }
//
    public void updateStock(Product product) {
        Integer tkode=product.getKode();
        String tNama=product.getNama();
        Integer tJumlah=product.getStok();

        //getStokAwal
        String sql = "SELECT STOK FROM PRODUCT WHERE KODE='"+tkode+"'";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs != null) {
                while (rs.next()){
                    tJumlah+=rs.getInt("stok");
                }
            }
            rs.close();
            state.close();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO getStokAwal");
            System.out.println(EX);
        }

        sql = "UPDATE Product "
                + "SET STOK='"+tJumlah+"'"
                +"WHERE KODE='"+tkode+"'";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            state.executeUpdate(sql);
            state.close();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO UpdateStock");
            System.out.println(EX);
        }
    }
    */
}
