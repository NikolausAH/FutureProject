package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Category;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAO extends MyConnection {

    public List<Category> getAllCategory() {
        List<Category> categoryList = new ArrayList<>();

        String sql = "select * from Category";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setKode(rs.getInt("kode"));
                    category.setNama(rs.getString("nama"));
                    category.setDeskripsi(rs.getString("deskripsi"));
                    categoryList.add(category);
                }
            }
            rs.close();
            state.close();
        } catch (Exception EX) {
            System.out.println("Error CategoryDAO getAllCategory");
            System.out.println(EX);
        }
        return categoryList;
    }


    public void createCategory(Category category) {
        Integer tkode=category.getKode();
        String tNama=category.getNama();
        String tdeskripsi=category.getDeskripsi();
        String sql = "INSERT INTO Category (kode,nama,deskripsi)"
                +"VALUES('"+tkode+"','"+tNama+"','"+tdeskripsi+"');";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            state.executeUpdate(sql);
            state.close();
        } catch (Exception EX) {
            System.out.println("Error CategoryDAO addCategory");
            System.out.println(EX);
        }
    }

    public void updateCategory(Category category) {
        Integer tkode=category.getKode();
        String tNama=category.getNama();
        String tdeskripsi=category.getDeskripsi();
        String sql = "UPDATE Category "
                + "SET NAMA='"+tNama+"',"
                + "DESKRIPSI='"+tdeskripsi+"'"
                +"WHERE KODE='"+tkode+"'";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            state.executeUpdate(sql);
            state.close();
        } catch (Exception EX) {
            System.out.println("Error CategoryDAO UpdateCategory");
            System.out.println(EX);
        }
    }

    public void deleteCategory(Integer kode){
        String sql="DELETE FROM Category WHERE KODE='"+kode+"'";
        try {
            Statement state = c.createStatement();
            state.executeUpdate(sql);
            state.close();
        } catch (Exception EX) {
            System.out.println("Error CategoryDAO DeleteCategory");
            System.out.println(EX);
        }
    }
}
