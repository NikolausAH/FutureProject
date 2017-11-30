package com.blibli.pos_minimarket.Model;

public class TransactionDetail {
    Integer detailId, quantity;
    Double price,discount;
    Integer productId, transactionId, discountPId, discountPxy;
    /* Foreign Key productId,
     * transactionId,
     * discountPId = discount product id//
     * discountPxy = discount product Buy x Get y
     */
    public TransactionDetail() {}
    public TransactionDetail(Integer detailId, Integer quantity, Double price, Double discount, Integer productId, Integer transactionId, Integer discountPId, Integer discountPxy) {
        this.detailId=detailId;
        this.quantity=quantity;
        this.price=price;
        this.discount=discount;
        this.productId=productId;
        this.transactionId=transactionId;
        this.discountPId=discountPId;
        this.discountPxy=discountPxy;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getDiscountPId() {
        return discountPId;
    }

    public void setDiscountPId(Integer discountPId) {
        this.discountPId = discountPId;
    }

    public Integer getDiscountPxy() {
        return discountPxy;
    }

    public void setDiscountPxy(Integer discountPxy) {
        this.discountPxy = discountPxy;
    }
}
