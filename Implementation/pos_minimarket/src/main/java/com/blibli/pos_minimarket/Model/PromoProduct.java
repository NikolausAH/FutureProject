package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

public class PromoProduct {
    private Integer pProductId;
    private double discountPercent;
    private Integer ProductId;
    private Timestamp startDate;
    private Timestamp endDate;

    public PromoProduct(){}

    public PromoProduct(Integer pProductId, double discountPercent, Integer productId, Timestamp startDate, Timestamp endDate) {
        this.pProductId = pProductId;
        this.discountPercent = discountPercent;
        ProductId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getpProductId() {
        return pProductId;
    }

    public void setpProductId(Integer pProductId) {
        this.pProductId = pProductId;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Integer getProductId() {
        return ProductId;
    }

    public void setProductId(Integer productId) {
        ProductId = productId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}

