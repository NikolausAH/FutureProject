package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDAO;
import com.blibli.pos_minimarket.Model.Minimarket;
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
    private MinimarketService minimarketService = new MinimarketService();
    private ProductService productService = new ProductService();
    private TransactionDetailService transactionDetailService = new TransactionDetailService();
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
   public Double updateTax(Double total){
        return total * (minimarketService.getTax()/100);
    }

    public Double updateTotal(){
        Double total = 0.0;
        Integer quantity;
        Double price;
        try {
            List<Product> productList = this.getFromCart();
            if (productList != null) {
                for (Product aProductList : productList) {
                    quantity = aProductList.getQuantity();
                    Product product;
                    product=productDAO.getById(aProductList.getProductId());
                    price = product.getPrice();
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

    public Integer getNextId(){
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


  public void addTransaction(String dateTime) {
        Transaction transaction = new Transaction();
        Double tax = this.updateTax(this.updateTotal());
        Double finalTotal = this.updateTotal() + tax;
        Double discount = 0.0; //belum
        Integer p_total_id = 0; //belum
        Integer employee_id = 0; //belum
        try {
            transaction.setTax(tax);
            transaction.setTotal(finalTotal);
            transaction.setDiscount(discount);
            transaction.setDateTime(dateTime);
            transaction.setTransactionId(transactionDAO.getNextId());
            transactionDAO.add(transaction);
            transactionDetailService.add(transaction);
            transactionDAO.removeFromCart();
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
