package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Promo;
import com.blibli.pos_minimarket.Model.PromoXY;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PromoXYDAO extends GeneralDAO implements InterfaceDAO<PromoXY, Integer, String> {
    private GeneralDAO generalDAO = new GeneralDAO();

    @Override
    public void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS public.promo_buyx_gety" +
                "(" +
                "    p_bxgy_id SERIAL PRIMARY KEY NOT NULL," +
                "    quantity_x INTEGER NOT NULL," +
                "    quantity_y INTEGER NOT NULL," +
                "    start_date TIMESTAMP NOT NULL," +
                "    end_date TIMESTAMP NOT NULL," +
                "    productx_id INTEGER NOT NULL," +
                "    producty_id INTEGER NOT NULL," +
                "    status VARCHAR(15) NOT NULL," +
                "    CONSTRAINT productx_id__fk FOREIGN KEY (product_id) REFERENCES product (product_id)," +
                "    CONSTRAINT producty_id__fk FOREIGN KEY (product_id) REFERENCES product (product_id)" +
                ");";
        String message = "Error PromoXYDAO initTable";
        generalDAO.executeSet(sql, message);
    }

    @Override
    public List<PromoXY> getAll() {
        List<PromoXY> promoList = new ArrayList<>();
        String sql = "SELECT * FROM promo_buyx_gety ORDER BY p_bxgy_id;";
        String message = "Error PromoXYDAO getAll";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    PromoXY promoXY = new PromoXY();
                    promoXY.setId(resultSet.getInt("p_bxgy_id"));
                    promoXY.setStartDate(resultSet.getTimestamp("start_date"));
                    promoXY.setEndDate(resultSet.getTimestamp("end_date"));
                    promoXY.setStatus(resultSet.getString("status"));
                    promoList.add(promoXY);
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
    public PromoXY getById(Integer id) {
        String sql = "SELECT * FROM promo_buyx_gety WHERE p_bxgy_id=" + id+";";
        String message = "Error PromoXYDAO getById";
        PromoXY promoXY = new PromoXY();
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    promoXY.setId(resultSet.getInt("p_bxgy_id"));
                    promoXY.setStartDate(resultSet.getTimestamp("start_date"));
                    promoXY.setEndDate(resultSet.getTimestamp("end_date"));
                    promoXY.setStatus(resultSet.getString("status"));
                }
                }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return promoXY;
    }

    @Override
    public List<PromoXY> search(String key) {
        return null;
    }

    @Override
    public void add(PromoXY promoXY) {
        String sql = "INSERT INTO promo_buyx_gety (p_bxgy_id, quantity_x, quantity_y, start_date, end_date, productx_id, producty_id, status) VALUES" +
                "(" + promoXY.getId() + "," +
                1 + "," +
                1 + "," + "'" +
                promoXY.getStartDate() + "'," + "'" +
                promoXY.getEndDate() + "'," +
                1 + "," +
                1 + "," +
                "'Active'" +
                ");";
        String message = "Error PromoXY Add";
        generalDAO.executeSet(sql, message);
    }

    @Override
    public void update(PromoXY promoXY) {
        String sql = "UPDATE promo_buyx_gety SET quantity_x =" +
                promoXY.getQuantityX() + "," +
                "quantity_y =" +
                promoXY.getQuantityY() + "," +
                "start_date ='" +
                promoXY.getStartDate() + "'," +
                "end_date ='" +
                promoXY.getEndDate() + "'," +
                "productx_id =" +
                promoXY.getProductXId() +
                "producty_id =" +
                promoXY.getProductYId() +
                "WHERE p_bxgy_id =" +
                promoXY.getId() +
                ";";
        String message = "Error PromoXYDAO update";
        generalDAO.executeSet(sql, message);
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from promo_buyx_gety where p_bxgy_id=" + id;
        String message = "Error PromoTotalDAO Delete";
        generalDAO.executeSet(sql, message);
    }

    @Override
    public void softDelete(Integer integer) {

    }

}
