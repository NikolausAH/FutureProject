package com.blibli.pos_minimarket.Model;

public class Product {
<<<<<<< HEAD

    private Integer productId;
    private Integer quantity;
    private String name;
    private String description;
    private String status;
    private Double price;
    private Category category;
=======
//
    private Integer kode,stok;
    private Integer kategoriId;
    private String nama,deskripsi;
    private Integer harga;
>>>>>>> spring
    public Product() {}
    public Product(Integer productId, String name, Double price, Integer quantity, String description, Category category,String status){
        this.productId=productId;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.description=description;
        this.category=category;
        this.status=status;

    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
