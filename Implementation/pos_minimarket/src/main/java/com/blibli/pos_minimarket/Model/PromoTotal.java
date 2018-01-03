package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

public class PromoTotal extends Promo {
    private double discountPercent;
    private double buyMin;
<<<<<<< HEAD
    private Timestamp startDate;
    private Timestamp endDate;
    private String status;
//
    public PromoTotal() {}

    public PromoTotal(int pTotalId, double discountPercent, double buyMin, Timestamp startDate, Timestamp endDate, String status){
    this.pTotalId = pTotalId;
    this.discountPercent = discountPercent;
    this.buyMin = buyMin;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
    }
=======
>>>>>>> master

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

<<<<<<< HEAD
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
=======
>>>>>>> master
