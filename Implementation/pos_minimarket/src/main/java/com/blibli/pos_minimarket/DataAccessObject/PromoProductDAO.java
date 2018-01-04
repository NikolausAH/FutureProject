package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Promo;
import com.blibli.pos_minimarket.Model.PromoProduct;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PromoProductDAO extends ConnectionSettings implements InterfaceDAO<PromoProduct, Integer, String> {
    private GeneralDAO generalDAO = new GeneralDAO();

    @Override
    public void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS public.promo_product_discount" +
                "(" +
                "    p_discount_id SERIAL PRIMARY KEY NOT NULL," +
                "    discount_percent DOUBLE PRECISION NOT NULL," +
                "    start_date TIMESTAMP NOT NULL," +
                "    start_date TIMESTAMP NOT NULL," +
                "    product_id INTEGER NOT NULL," +
                "    CONSTRAINT product_id___fk FOREIGN KEY (product_id) REFERENCES product (product_id)" +
                ");";
        String message = "Error PromoProductDAO initTable";
        generalDAO.executeSet(sql, message);
    }

    @Override
    public List<PromoProduct> getAll() {
        List<PromoProduct> promoList = new ArrayList<>();
        String sql = "SELECT * FROM promo_product_discount ORDER BY p_discount_id;";
        String message = "Error PromoProductDAO getAll";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    PromoProduct promoProduct = new PromoProduct();
                    promoProduct.setId(resultSet.getInt("p_discount_id"));
                    promoProduct.setDiscountPercent(resultSet.getDouble("discount_percent"));
                    promoProduct.setStartDate(resultSet.getTimestamp("start_date"));
                    promoProduct.setEndDate(resultSet.getTimestamp("end_date"));
                    promoProduct.setProductId(resultSet.getInt("product_id"));
                    promoProduct.setStatus(resultSet.getString("status"));
                    promoList.add(promoProduct);
                }
                resultSet.close();
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return promoList;
    }

    @Override
    public PromoProduct getById(Integer id) {
        String sql = "SELECT * FROM promo_product_discount WHERE p_discount_id =" + id + ";";
        String message = "Error PromoProductDAO getById";
        PromoProduct promoProduct = new PromoProduct();
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    promoProduct.setId(resultSet.getInt("p_discount_id"));
                    promoProduct.setDiscountPercent(resultSet.getDouble("discount_percent"));
                    promoProduct.setStartDate(resultSet.getTimestamp("start_date"));
                    promoProduct.setEndDate(resultSet.getTimestamp("end_date"));
                    promoProduct.setProductId(resultSet.getInt("product_id"));
                    promoProduct.setStatus(resultSet.getString("status"));
                }
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return promoProduct;
    }

    public PromoProduct getByProductId(Integer productId) {
        String sql = "SELECT * FROM promo_product_discount WHERE product_id =" + productId + ";";
        String message = "Error PromoProductDAO getByProductId";
        PromoProduct promoProduct = new PromoProduct();
        promoProduct.setId(0);
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    promoProduct.setId(resultSet.getInt("p_discount_id"));
                    promoProduct.setDiscountPercent(resultSet.getDouble("discount_percent"));
                    promoProduct.setStartDate(resultSet.getTimestamp("start_date"));
                    promoProduct.setEndDate(resultSet.getTimestamp("end_date"));
                    promoProduct.setProductId(resultSet.getInt("product_id"));
                    promoProduct.setStatus(resultSet.getString("status"));
                }
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return promoProduct;
    }

    @Override
    public List<PromoProduct> search(String key) {
        return null;
    }

    @Override
    public void add(PromoProduct promoProduct) {
        String sql = "INSERT INTO promo_product_discount(p_discount_id, discount_percent, start_date, end_date, product_id, status)VALUES " +
                "(" + promoProduct.getId() + "," +
                promoProduct.getDiscountPercent() + "," + "'" +
                promoProduct.getStartDate() + "'," + "'" +
                promoProduct.getEndDate() + "'," +
                promoProduct.getProductId() + "," +
                "'Active'" +
                ");";
        String message = "Error PromoProduct Add";
        generalDAO.executeSet(sql, message);
    }

    @Override
    public void update(PromoProduct promoProduct) {
        String sql = "UPDATE promo_product_discount SET discount_percent =" +
                promoProduct.getDiscountPercent() + "," +
                "start_date ='" +
                promoProduct.getStartDate() + "'," +
                "end_date ='" +
                promoProduct.getEndDate() + "'," +
                "product_id =" +
                promoProduct.getProductId() +
                "WHERE p_total_id =" +
                promoProduct.getId() +
                ";";
        String message = "Error PromoProductDAO update";
        generalDAO.executeSet(sql, message);
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from promo_product_discount where p_discount_id=" + id;
        String message = "Error PromoProductDAO Delete";
        generalDAO.executeSet(sql, message);
    }

    @Override
    public void softDelete(Integer integer) {

    }

    public void updateStatus(Long currentTime){
        String sql1 = "UPDATE promo_product_discount SET status = 'Expired' WHERE EXTRACT(EPOCH FROM start_date) > " +currentTime +
                " OR EXTRACT(EPOCH FROM end_date) <"+currentTime+ ";";
        String sql2 = "UPDATE promo_product_discount SET status = 'Active' WHERE EXTRACT(EPOCH FROM start_date) <= " +currentTime +
                " AND EXTRACT(EPOCH FROM end_date) >="+currentTime+ ";";
        String message = "Error PromoProductDAO updateStatus";
        generalDAO.executeSet(sql1,message);
        generalDAO.executeSet(sql2,message);
    }


}