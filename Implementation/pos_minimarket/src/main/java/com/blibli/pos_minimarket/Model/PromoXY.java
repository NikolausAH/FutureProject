package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

public class PromoXY extends Promo {
    private int quantityX;
    private int quantityY;
    private int productXId;
    private int productYId;
    private String status;

<<<<<<< HEAD
    public PromoXY() {}
=======
    public PromoXY() {
    }

    public PromoXY(Integer id, Timestamp startDate, Timestamp endDate,String status,String type, Integer quantityX, Integer quantityY, Integer productXId, Integer productYId) {
        super(id, startDate, endDate, status,type);
        this.setQuantityX(quantityX);
        this.setQuantityY(quantityY);
        this.setProductXId(productXId);
        this.setProductYId(productYId);
    }
>>>>>>> master

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

<<<<<<< HEAD
    @Override
    public Timestamp getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Override
    public Timestamp getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

=======
>>>>>>> master
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
<<<<<<< HEAD

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//
//
=======
>>>>>>> master
}
