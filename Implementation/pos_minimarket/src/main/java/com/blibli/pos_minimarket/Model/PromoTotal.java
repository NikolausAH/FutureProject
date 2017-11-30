package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

public class PromoTotal {
    private int pTotalId;
    private double discountPercent;
    private double buyMin;
    private Timestamp startDate;
    private Timestamp endDate;

    public PromoTotal() {}

    public PromoTotal(int pTotalId, double discountPercent, double buyMin, Timestamp startDate, Timestamp endDate){
    this.pTotalId = pTotalId;
    this.discountPercent = discountPercent;
    this.buyMin = buyMin;
    this.startDate = startDate;
    this.endDate = endDate;
    }

    public int getpTotalId() {
        return pTotalId;
    }

    public void setpTotalId(int pTotalId) {
        this.pTotalId = pTotalId;
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