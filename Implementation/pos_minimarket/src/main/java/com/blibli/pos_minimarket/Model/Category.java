package com.blibli.pos_minimarket.Model;

public class Category {
    private Integer categoryId;
    private String name,description;
    public Category() {}

    public Category(Integer categoryId, String name, String description){
        this.categoryId=categoryId;
        this.name=name;
        this.description=description;
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

    public void setDescription(String description) {
        this.description = description;
    }
}
