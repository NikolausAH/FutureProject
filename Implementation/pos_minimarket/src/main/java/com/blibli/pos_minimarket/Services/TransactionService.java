package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDAO;
import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private TransactionDAO transactionDAO = new TransactionDAO();
    private ProductDAO productDAO = new ProductDAO();
    //private MiniMarketDAO miniMarketDAO = new MiniMarketDAO();
    private ProductService productService = new ProductService();
    public TransactionService() {
    }

    public void initTable(){
        try {
            transactionDAO.initTable();
        }
        catch (Exception EX){
            System.out.println("Error TransactionService initTable");
            System.out.println(EX.toString());
        }
    }
   /* public Double updateTax(Double total){
        return total * miniMarketDAO.getTax();
    }*/

    public Double updateTotal(){
        Double total = 0.0;
        Integer quantity;
        Double price;
        try {
            List<Product> productList = this.getFromCart();
            if (productList != null) {
                for (Product aProductList : productList) {
                    quantity = aProductList.getQuantity();
                    System.out.println(quantity);
                    Product product;
                    product=productDAO.getById(aProductList.getProductId());
                    price = product.getPrice();
                    System.out.println(price);
                    total += quantity * price;
                }
            }
        }catch (Exception EX){
            System.out.println("Error TransactionService updateTotal");
            System.out.println(EX.toString());
        }
        return total;
    }

    public LocalDateTime initDate(){
        LocalDateTime localDateTime = null;
        try {
            localDateTime=LocalDateTime.now();
        }catch (Exception EX){
            System.out.println("Error TransactionService setDate");
            System.out.println(EX.toString());
        }
        return localDateTime;
    }

    public Integer initTransactionId(){
        Integer nextId = 1;
        try{
            nextId = transactionDAO.getNextId();
        }catch (Exception EX)
        {
            System.out.println("Error TransactionService setNextTransactionId");
            System.out.println(EX.toString());
        }
        return nextId;
    }

    public void addToCart(Integer productId, Integer quantity){
        Product product;
        try {
            product=productDAO.getById(productId);
            if (productService.isAny(product)){
                transactionDAO.addToCart(productId,quantity);
            }
            else {
                System.out.println("TransactionService AddToCart Product can not add to Cart");
            }
        }catch (Exception EX){
            System.out.println("Error TransactionService AddToCart");
            System.out.println(EX.toString());
        }
    }

    public List<Product> getFromCart() {
        List<Product> productList;
        List<Product> productList1 = new ArrayList<>();
        try {
            productList = transactionDAO.getFromCart();
            for (Product ProductList : productList) {
                Product product=productDAO.getById(ProductList.getProductId());
                product.setQuantity(ProductList.getQuantity());
                productList1.add(product);
            }
        }
        catch (Exception EX){
            System.out.println("Error TransactionService getFromCart");
            System.out.println(EX.toString());
        }
        return productList1;
    }


   /* public void addTransaction(String dateTime) {
        Transaction transaction = new Transaction();
       // Double tax = this.updateTax(this.updateTotal());
      //  Double finalTotal = this.updateTotal() + tax;
        Double discount = 0.0; //belum
        Integer p_total_id = 0; //belum
        Integer employee_id = 0; //belum
        try {
            transaction.setTax(tax);
            transaction.setTotal(finalTotal);
            transaction.setDiscount(discount);
            System.out.println("Error TransactionController initialTransaction");
            System.out.println(dateTime);
            transaction.setDateTime(dateTime);
            transactionDAO.add(transaction);
        }
        catch (Exception EX){
            System.out.println("Error TransactionService Add");
            System.out.println(EX.toString());
        }
    }
    /*
    public List<Transaction> showAllTransaction() {
        connectionSettings.makeConnection();
        List<Transaction> transactionList = transactionDAO.getAllTransaction();
        connectionSettings.closeConnection();
        return transactionList;
    }
    */
}
