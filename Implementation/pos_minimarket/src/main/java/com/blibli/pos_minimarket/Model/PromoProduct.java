package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

//
public class PromoProduct extends Promo {
    private double discountPercent;
    private Integer productId;

    public PromoProduct() {
    }

    public PromoProduct(Integer id, Timestamp startDate, Timestamp endDate, String status, String type, double discountPercent, Integer productId) {
        super(id, startDate, endDate, status,type);
        this.setDiscountPercent(discountPercent);
        this.setProductId(productId);
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
