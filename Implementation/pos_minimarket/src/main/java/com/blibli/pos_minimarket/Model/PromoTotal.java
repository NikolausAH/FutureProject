package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

public class PromoTotal extends Promo {
    private double discountPercent;
    private double buyMin;
    private Integer idTotal;

    public PromoTotal() {
    }

    public PromoTotal(Timestamp startDate, Timestamp endDate, Integer id,String status, Integer idTotal, double discountPercent, double buyMin) {
        super(id, startDate, endDate, status);
        this.setDiscountPercent(discountPercent);
        this.setBuyMin(buyMin);
        this.setIdTotal(idTotal);
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

    public Integer getIdTotal() {
        return idTotal;
    }

    public void setIdTotal(Integer idTotal) {
        this.idTotal = idTotal;
    }
}

