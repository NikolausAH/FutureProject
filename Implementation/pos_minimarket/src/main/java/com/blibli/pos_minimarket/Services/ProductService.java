package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ConnectionSettings;
import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ConnectionSettings connectionSettings = new ConnectionSettings();
    private ProductDAO productDAO = new ProductDAO();

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

    public void updateQuantity(Product product){
        try {
            productDAO.updateQuantity(product);
        }
        catch (Exception EX){
            System.out.println("Error ProductService Add");
            System.out.println(EX.toString());
        }
    }
}
