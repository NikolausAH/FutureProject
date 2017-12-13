package com.blibli.pos_minimarket.Model;

public class ReportStatistic {
    private Integer number;
    private Product product;
    private Integer transactionAmount;
    private Integer quantityAmount;

    public ReportStatistic(){}
    public ReportStatistic(Integer number, Product product, Integer transactionAmount, Integer quantityAmount) {
        this.number = number;
        this.product = product;
        this.transactionAmount = transactionAmount;
        this.quantityAmount = quantityAmount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getQuantityAmount() {
        return quantityAmount;
    }

    public void setQuantityAmount(Integer quantityAmount) {
        this.quantityAmount = quantityAmount;
    }
}
