package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDAO;
import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Model.Transaction;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private TransactionDAO transactionDAO = new TransactionDAO();
    private ProductDAO productDAO = new ProductDAO();
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
/*
    public List<Transaction> showAllTransaction() {
        connectionSettings.makeConnection();
        List<Transaction> transactionList = transactionDAO.getAllTransaction();
        connectionSettings.closeConnection();
        return transactionList;
    }
    */

    public LocalDateTime setDate(){
        LocalDateTime localDateTime = null;
        try {
            localDateTime=LocalDateTime.now();
        }catch (Exception EX){
            System.out.println("Error TransactionService setDate");
            System.out.println(EX.toString());
        }
        return localDateTime;
    }

    public Integer setNextTransactionId(){
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

    public Double setTax(){
        return 0.0;
    }

    public Double setTotal(){
        return 0.0;
    }

    public void add(Transaction transaction) {
        try {
            transactionDAO.add(transaction);
        }
        catch (Exception EX){
            System.out.println("Error TransactionService Add");
            System.out.println(EX.toString());
        }
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
}
