package com.blibli.pos_minimarket.Model;

public class TransactionDetail {
    Integer detail_Id, quantity;
    Double price,discount;
    Integer product_Id, transaction_Id, p_discount_id, p_bxgy_id;
    /* Foreign Key productId,
     * transactionId,
     * discountPId = discount product id//
     * discountPxy = discount product Buy x Get y
     */
    public TransactionDetail() {}
    public TransactionDetail(Integer detail_Id, Integer quantity, Double price, Double discount, Integer product_Id, Integer transaction_Id, Integer p_discount_id, Integer p_bxgy_id) {
        this.detail_Id=detail_Id;
        this.quantity=quantity;
        this.price=price;
        this.discount=discount;
        this.product_Id=product_Id;
        this.transaction_Id=transaction_Id;
        this.p_discount_id=p_discount_id;
        this.p_bxgy_id=p_bxgy_id;
    }

    public Integer getDetail_Id() {
        return detail_Id;
    }

    public void setDetail_Id(Integer detail_Id) {
        this.detail_Id = detail_Id;
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

    public Integer getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(Integer product_Id) {
        this.product_Id = product_Id;
    }

    public Integer getTransaction_Id() {
        return transaction_Id;
    }

    public void setTransaction_Id(Integer transaction_Id) {
        this.transaction_Id = transaction_Id;
    }

    public Integer getP_discount_id() {
        return p_discount_id;
    }

    public void setP_discount_id(Integer p_discount_id) {
        this.p_discount_id = p_discount_id;
    }

    public Integer getP_bxgy_id() {
        return p_bxgy_id;
    }

    public void setP_bxgy_id(Integer p_bxgy_id) {
        this.p_bxgy_id = p_bxgy_id;
    }
}
