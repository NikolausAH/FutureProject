package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

public class PromoTotal extends Promo {
    private double discountPercent;
    private double buyMin;

    public PromoTotal() {
    }

    public PromoTotal(Timestamp startDate, Timestamp endDate, Integer id, String status, String type, double discountPercent, double buyMin) {
        super(id, startDate, endDate, status,type);
        this.setDiscountPercent(discountPercent);
        this.setBuyMin(buyMin);
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getBuyMin() {
        return buyMin;
    }

    public void setBuyMin(double buyMin) {
        this.buyMin = buyMin;
    }

}

