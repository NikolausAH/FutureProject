package com.blibli.pos_minimarket.Model;

public class Transaction {
    private Integer transactionId;
    private String date;
    private String time;
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
    public Transaction(Integer transactionId, String date, String time, Double tax, Double discount, Double total, String status){
        this.transactionId=transactionId;
        this.date=date;
        this.time=time;
        this.tax=tax;
        this.discount=discount;
        this.total=total;
        this.setStatus(status);
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
