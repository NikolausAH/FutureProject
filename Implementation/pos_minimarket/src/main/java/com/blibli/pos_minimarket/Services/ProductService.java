package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ConnectionSettings;
import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ConnectionSettings connectionSettings = new ConnectionSettings();
    private ProductDAO productDAO = new ProductDAO();

 /*   public List<Product> showAllProduct() {
        connectionSettings.makeConnection();
        List<Product> productList = productDAO.getAllProduct();
        connectionSettings.closeConnection();
        return productList;
    }
    public void createProduct(Product product){
        connectionSettings.makeConnection();
        productDAO.createProduct(product);
        connectionSettings.closeConnection();
    }
    public void updateProduct(Product product){
        connectionSettings.makeConnection();
        productDAO.updateProduct(product);
        connectionSettings.closeConnection();
    }

    public void deleteProduct(Integer kode){
        connectionSettings.makeConnection();
        productDAO.deleteProduct(kode);
        connectionSettings.closeConnection();
    }

    public void updateStock(Product product){
        connectionSettings.makeConnection();
        productDAO.updateStock(product);
        connectionSettings.closeConnection();
    }
    */
}
