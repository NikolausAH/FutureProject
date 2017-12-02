package com.blibli.pos_minimarket.Model;

public class TransactionDetail {
    private Integer detail_Id;
    private Integer quantity;
    private Double price;
    private Double discount;
    private PromoProduct promoProduct;
    private PromoXY promoXY;
    private Product product;
    private Transaction transaction;
    /* Foreign Key productId,
     * transactionId,
     * discountPId = discount product id
     * discountPxy = discount product Buy x Get y
     */
    public TransactionDetail() {}
    public TransactionDetail(Integer detail_Id, Integer quantity, Double price, Double discount, PromoProduct promoProduct, PromoXY promoXY, Product product, Transaction transaction) {
        this.detail_Id=detail_Id;
        this.quantity=quantity;
        this.price=price;
        this.discount=discount;
        this.product=product;
        this.transaction=transaction;
        this.promoProduct=promoProduct;
        this.promoXY=promoXY;
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

    public PromoProduct getPromoProduct() {
        return promoProduct;
    }

    public void setPromoProduct(PromoProduct promoProduct) {
        this.promoProduct = promoProduct;
    }

    public PromoXY getPromoXY() {
        return promoXY;
    }

    public void setPromoXY(PromoXY promoXY) {
        this.promoXY = promoXY;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
