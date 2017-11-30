package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductDAO productDAO = new ProductDAO();


    public ProductService() {
    }
}

//
 /*   public List<Product> showAllProduct() {
        connectionSettings.makeConnection();
        List<Product> productList = productDAO.getAllProduct();
        connectionSettings.closeConnection();
        return productList;
>>>>>>> spring
    }

    public void initTable(){
        try {
            productDAO.initTable();
        }
        catch (Exception EX){
            System.out.println("Error ProductService initTable");
            System.out.println(EX.toString());
        }
    }

    public void add(Product product){
        try {
            productDAO.add(product);
        }
        catch (Exception EX){
            System.out.println("Error ProductService Add");
            System.out.println(EX.toString());
        }
    }
    public void update(Product product){
        try {
            productDAO.update(product);
        }
        catch (Exception EX){
            System.out.println("Error ProductService Update");
            System.out.println(EX.toString());
        }
    }

    public void delete(Integer productId){
        try {
            productDAO.delete(productId);
        }
        catch (Exception EX){
            System.out.println("Error ProductService Delete");
            System.out.println(EX.toString());
        }
    }

    public void softDelete(Integer productId){
        try {
            productDAO.softDelete(productId);
        }
        catch (Exception EX){
            System.out.println("Error ProductService SoftDelete");
            System.out.println(EX.toString());
        }
    }

    public Product getById(Integer productId){
        Product product = new Product();
        try {

            product = productDAO.getById(productId);
        }
        catch (Exception EX){
            System.out.println("Error ProductService getById");
            System.out.println(EX.toString());
        }
        return product;
    }

    public List<Product> showAll() {
       List<Product> productList = new ArrayList<>();
       try {
           productList = productDAO.getAll();
       }
       catch (Exception EX){
           System.out.println("Error ProductService showAll");
           System.out.println(EX.toString());
       }
        return productList;
    }

    public List<Product> search(String searchKey) {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productDAO.search(searchKey);
        } catch (Exception EX) {
            System.out.println("Error CategoryServices search");
            System.out.println(EX.toString());
        }
        return productList;
    }

    boolean isAny(Product product){
        List<Product> productList;
        productList = productDAO.getAll();
        for (Product ProductList : productList) {
            if (ProductList.getProductId().equals(product.getProductId())) {
                return true;
            }
        }
        return false;
    }*/



