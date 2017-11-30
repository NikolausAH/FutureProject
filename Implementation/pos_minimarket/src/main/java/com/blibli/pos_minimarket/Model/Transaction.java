package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

public class Transaction {
    private Integer transactionId;
    private String dateTime;
    private Double tax;
    private Double discount;
    private Double total;
    private String status;
    //tambah entity promo total sama pegawai
    /* tax = total tax (money)
     * Foreign Key
     * discount = discount (money) from product discount, buy x get y, and discount product total buy
     * discountPId = id discount product total buy
     * employeeId = employee ID
     */
    public Transaction() {}
    public Transaction(Integer transactionId, String dateTime, Double tax, Double discount, Double total, String status){
        this.transactionId=transactionId;
        this.dateTime=dateTime;
        this.tax=tax;
        this.discount=discount;
        this.total=total;
        this.status=status;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
