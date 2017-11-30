package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

public class PromoXY {
    private int pXYId;
    private int quantityX;
    private int quantityY;
    private Timestamp startDate;
    private Timestamp endDate;
    private int productXId;
    private int productYId;

    public PromoXY() {}

    public PromoXY(int pXYId, int quantityX, int quantityY, Timestamp startDate, Timestamp endDate, int productXId, int productYId) {
        this.setpXYId(pXYId);
        this.setQuantityX(quantityX);
        this.setQuantityY(quantityY);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setProductXId(productXId);
        this.setProductYId(productYId);


    }

    public int getpXYId() {
        return pXYId;
    }

    public void setpXYId(int pXYId) {
        this.pXYId = pXYId;
    }

    public int getQuantityX() {
        return quantityX;
    }

    public void setQuantityX(int quantityX) {
        this.quantityX = quantityX;
    }

    public int getQuantityY() {
        return quantityY;
    }

    public void setQuantityY(int quantityY) {
        this.quantityY = quantityY;
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

    public int getProductXId() {
        return productXId;
    }

    public void setProductXId(int productXId) {
        this.productXId = productXId;
    }

    public int getProductYId() {
        return productYId;
    }

    public void setProductYId(int productYId) {
        this.productYId = productYId;
    }
}
