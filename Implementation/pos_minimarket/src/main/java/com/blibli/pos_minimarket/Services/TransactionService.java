package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDAO;
import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Model.Transaction;
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

    public void addToCart(List<Product> productList, Product product){
        List<Product> productList1;
        try {
            productList1 = productDAO.getAll();
            if (productService.isExist(productList1,product)){
                productList.add(product);
            }
            else {
                System.out.println("TransactionService AddToCart Product can not add to cart");
            }
        }catch (Exception EX){
            System.out.println("Error TransactionService AddToCart");
            System.out.println(EX.toString());
        }
    }
}
