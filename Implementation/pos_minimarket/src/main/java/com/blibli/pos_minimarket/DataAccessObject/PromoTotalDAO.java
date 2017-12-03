package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Promo;
import com.blibli.pos_minimarket.Model.PromoProduct;
import com.blibli.pos_minimarket.Model.PromoTotal;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

///
@Repository
public class PromoTotalDAO extends ConnectionSettings implements InterfaceDAO<PromoTotal, Integer, String> {
    private GeneralDAO generalDAO = new GeneralDAO();

    @Override
    public void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS public.promo_total_buy" +
                "(" +
                "    p_total_id SERIAL PRIMARY KEY NOT NULL," +
                "    discount_percent DOUBLE PRECISION NOT NULL," +
                "    buy_min DOUBLE PRECISION NOT NULL," +
                "    start_date TIMESTAMP NOT NULL," +
                "    end_date TIMESTAMP NOT NULL," +
                "    status VARCHAR(15) NOT NULL" +
                ");";
        String message = "Error PromoTotalDAO initTable";
        generalDAO.executeSet(sql, message);
    }

    @Override
    public List<PromoTotal> getAll() {
        List<PromoTotal> promoList = new ArrayList<>();
        String sql = "SELECT * FROM promo_total_buy ORDER BY p_total_id;";
        String message = "Error PromoTotalDAOgetAll";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    PromoTotal promoTotal = new PromoTotal();
                    promoTotal.setId(resultSet.getInt("p_total_id"));
                    promoTotal.setDiscountPercent(resultSet.getDouble("discount_percent"));
                    promoTotal.setBuyMin(resultSet.getDouble("buy_min"));
                    promoTotal.setStartDate(resultSet.getTimestamp("start_date"));
                    promoTotal.setEndDate(resultSet.getTimestamp("end_date"));
                    promoTotal.setStatus(resultSet.getString("status"));
                    promoList.add(promoTotal);
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
    public PromoTotal getById(Integer key) {

        return null;
    }

    @Override
    public List<PromoTotal> search(String key) {
        return null;
    }

    @Override
    public void add(PromoTotal promoTotal) {
        String sql = "INSERT INTO promo_total_buy (p_total_id, discount_percent, buy_min, start_date, end_date, status) VALUES" +
                "("+promoTotal.getId()+","+
                "("+promoTotal.getDiscountPercent()+","+
                "("+promoTotal.getBuyMin()+","+
                "("+promoTotal.getStartDate()+","+
                "("+promoTotal.getEndDate()+","+
                "("+promoTotal.getStatus()+","
                +")";
        String message = "Error PromoTotal Add";
        generalDAO.executeSet(sql,message);
    }

    @Override
    public void update(PromoTotal t) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void softDelete(Integer integer) {

    }
}