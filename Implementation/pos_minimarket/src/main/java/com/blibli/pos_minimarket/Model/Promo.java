package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

public class Promo {
    private Integer Id;
    private Timestamp startDate;
    private Timestamp endDate;
    private String status;
    private String type;

    public Promo() {
    }

    public Promo(int id, Timestamp startDate, Timestamp endDate, String status, String type) {
        setId(id);
        setEndDate(startDate);
        setEndDate(endDate);
        setStatus(status);
        setType(type);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
