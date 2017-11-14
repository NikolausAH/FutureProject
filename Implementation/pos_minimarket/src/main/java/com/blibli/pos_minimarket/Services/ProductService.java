package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.MyConnection;
import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private MyConnection myConnection = new MyConnection();
    private ProductDAO productDAO = new ProductDAO();

    public List<Product> showAllProduct() {
        myConnection.makeConnection();
        List<Product> productList = productDAO.getAllProduct();
        myConnection.closeConnection();
        return productList;
    }
    public void createProduct(Product product){
        myConnection.makeConnection();
        productDAO.createProduct(product);
        myConnection.closeConnection();
    }
    public void updateProduct(Product product){
        myConnection.makeConnection();
        productDAO.updateProduct(product);
        myConnection.closeConnection();
    }

    public void deleteProduct(Integer kode){
        myConnection.makeConnection();
        productDAO.deleteProduct(kode);
        myConnection.closeConnection();
    }

    public void updateStock(Product product){
        myConnection.makeConnection();
        productDAO.updateStock(product);
        myConnection.closeConnection();
    }
}
