package com.blibli.pos_minimarket.Model;

public class Minimarket {
    private String name;
    private String address;
    private String telp_no;
    private String email;
    private Double tax;
    private String receipt_desc;

    public Minimarket() {
    }

    public Minimarket(String name, String address, String telp_no, String email, Double tax, String receipt_desc) {
        this.name = name;
        this.address = address;
        this.telp_no = telp_no;
        this.email = email;
        this.tax = tax;
        this.receipt_desc = receipt_desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelp_no() {
        return telp_no;
    }

    public void setTelp_no(String telp_no) {
        this.telp_no = telp_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getReceipt_desc() {
        return receipt_desc;
    }

    public void setReceipt_desc(String receipt_desc) {
        this.receipt_desc = receipt_desc;
    }
}
