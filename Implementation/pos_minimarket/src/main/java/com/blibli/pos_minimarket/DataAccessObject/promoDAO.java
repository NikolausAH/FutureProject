package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.PromoProduct;
import com.blibli.pos_minimarket.Model.PromoXY;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

@Repository
public class promoDAO extends ConnectionSettings {

    public promoDAO(){}

    public PromoProduct getPromoProduct(Integer productId){
        PromoProduct promoProduct = new PromoProduct();
        String sql = "SELECT * FROM promo_product_discount WHERE p_discount_id = '"+productId+"';";
        String message = "Error promoDAO getByProductId";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    promoProduct.setpProductId(resultSet.getInt("p_discount_id"));
                    promoProduct.setDiscountPercent(resultSet.getDouble("discount_percent"));
                    promoProduct.setStartDate(resultSet.getTimestamp("start_date"));
                    promoProduct.setEndDate(resultSet.getTimestamp("end_date"));
                    promoProduct.setProductId(resultSet.getInt("product_id"));
                    }
                resultSet.close();
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return promoProduct;
    }

    public PromoXY getPromoXY(Integer productId){
        PromoXY promoXY = null;
        String sql = "SELECT * FROM promo_buyx_gety WHERE p_bxgy_id = '"+productId+"';";
        String message = "Error promoDAO getPromoXY";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                PromoXY tempPromoXY = new PromoXY();
                while (resultSet.next()) {
                    tempPromoXY.setId(resultSet.getInt("p_bxgy_id"));
                    tempPromoXY.setQuantityX(resultSet.getInt("quantity_x"));
                    tempPromoXY.setQuantityY(resultSet.getInt("quantity_y"));
                    tempPromoXY.setStartDate(resultSet.getTimestamp("start_date"));
                    tempPromoXY.setEndDate(resultSet.getTimestamp("end_date"));
                    tempPromoXY.setProductXId(resultSet.getInt("productx_id"));
                    tempPromoXY.setProductYId(resultSet.getInt("producty_id"));
                    tempPromoXY.setStatus(resultSet.getString("status"));
                }resultSet.close();
                if(tempPromoXY.getStatus().equals("active") && tempPromoXY.getStartDate().toLocalDateTime().isBefore(LocalDateTime.now()) && LocalDateTime.now().isBefore(tempPromoXY.getEndDate().toLocalDateTime())){
                    promoXY = tempPromoXY;
                }
          }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return promoXY;
    }

}
