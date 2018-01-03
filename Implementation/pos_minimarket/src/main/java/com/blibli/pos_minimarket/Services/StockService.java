package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.Model.Product;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private ProductDAO productDAO = new ProductDAO();

    public StockService() {
    }

    public void updateQuantity(Product product, Integer quantity){
        try {
            productDAO.updateQuantity(product,quantity);//+ Quantity
        }
        catch (Exception EX){
            System.out.println("Error StockService UpdateQuantity");
            System.out.println(EX.toString());
        }
    }
}
