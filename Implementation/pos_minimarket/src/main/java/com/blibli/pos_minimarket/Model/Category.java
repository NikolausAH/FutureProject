package com.blibli.pos_minimarket.Model;

public class Category {
    private Integer categoryId;
    private String name,description,status;
    public Category() {}

    public Category(Integer categoryId, String name, String description, String status){
        this.categoryId=categoryId;
        this.name=name;
        this.description=description;
        this.status=status;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}