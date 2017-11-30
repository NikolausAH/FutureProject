package com.blibli.pos_minimarket.Model;

import java.sql.Timestamp;

public class Promo {
    private int Id;
    private Timestamp startDate;
    private Timestamp endDate;

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
}
