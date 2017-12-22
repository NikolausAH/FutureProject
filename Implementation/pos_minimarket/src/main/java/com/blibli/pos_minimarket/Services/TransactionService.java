package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDAO;
import com.blibli.pos_minimarket.Model.*;
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
    private promoServices promoServices = new promoServices();
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
    public Double getTax(Double total){
        return total * (minimarketService.getTax()/100);
    }

    private Double sumDiscount(Integer quantity, PromoProduct promoProduct, Double price, PromoXY promoXY){
        Double discount = 0.0;
        if(promoProduct!=null){
            discount += quantity * price * (promoProduct.getDiscountPercent() / 100);
        }
        if(promoXY!=null && quantity >= promoXY.getQuantityX()){
            Integer discountQuantity = (quantity / promoXY.getQuantityX()) * promoXY.getQuantityY();
            Product product = productDAO.getById(promoXY.getProductYId());
            discount += product.getPrice() * discountQuantity;
        }
        return discount;
    }
    public Double getTotal(List<TransactionDetail> transactionDetailList){
        Double total = 0.0;
        try {
            if (transactionDetailList != null) {
                for (TransactionDetail TransactionDetailList : transactionDetailList) {
                    Integer quantity = TransactionDetailList.getQuantity();
                    Product product = TransactionDetailList.getProduct();
                    Double price = product.getPrice();
                    PromoProduct promoProduct = TransactionDetailList.getPromoProduct();
                    PromoXY promoXY = promoServices.getPromoXY(product.getProductId());
                    Double discount = this.sumDiscount(quantity,promoProduct,price,promoXY);

                    total += (quantity * price)- discount;
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

    public List<TransactionDetail> getAllFromCart() {
        List<Product> productList;
        List<TransactionDetail> transactionDetailList = new ArrayList<>();
        try {
            productList = transactionDAO.getFromCart();
            for (Product ProductList : productList) {
                TransactionDetail transactionDetail = new TransactionDetail();
                Product product=productDAO.getById(ProductList.getProductId());
                transactionDetail.setQuantity(ProductList.getQuantity());
                transactionDetail.setProduct(product);
                PromoProduct promoProduct = promoServices.getPromoProduct(product.getProductId());
                PromoXY promoXY = promoServices.getPromoXY(product.getProductId());
                if(promoXY!=null && ProductList.getQuantity() >= promoXY.getQuantityX()){
                    TransactionDetail tempDetail = new TransactionDetail();
                    Integer multi = ProductList.getQuantity() / promoXY.getQuantityX();
                    tempDetail.setQuantity(promoXY.getQuantityY() * multi);
                    Product product1 = productDAO.getById(promoXY.getProductYId());
                    tempDetail.setProduct(product1);
                    tempDetail.setPrice(product1.getPrice());
                    tempDetail.setDiscount(product1.getPrice() * tempDetail.getQuantity());
                    transactionDetailList.add(tempDetail);
                }
                Double discount = 0.0;
                if(promoProduct!=null) {
                    discount = ProductList.getQuantity() * product.getPrice() * (promoProduct.getDiscountPercent() / 100);
                }
                transactionDetail.setDiscount(discount);
                transactionDetail.setPromoProduct(promoProduct);
                transactionDetailList.add(transactionDetail);
            }
        }
        catch (Exception EX){
            System.out.println("Error TransactionService getFromCart");
            System.out.println(EX.toString());
        }
        return transactionDetailList;
    }

  public void addTransaction(String dateTime, Double total, Double tax) {
        Transaction transaction = new Transaction();
        Double finalTotal = total + tax;
        Double discountTotal = 0.0; //belum
        Integer p_total_id = 0; //belum
        Integer employee_id = 0; //belum
        try {
            transaction.setTax(tax);
            transaction.setTotal(finalTotal);
            transaction.setDiscount(discountTotal);
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
    public List<Transaction> showAllTransaction() {
        List<Transaction> transactionList = new ArrayList<>();
        try{
            transactionList = transactionDAO.getAllTransaction();
        }catch (Exception EX)
        {
            System.out.println("Error TransactionService showAllTransaction");
            System.out.println(EX.toString());
        }
        return transactionList;
    }

    public Double getDiscountTotal(Double total, LocalDateTime dateTime){
        Double discountTotal = 0.0;
        PromoTotal promoTotal = null;
        try{
            promoTotal = transactionDAO.getDiscountTotal(total,dateTime);
            if(promoTotal != null){
                discountTotal = total * (promoTotal.getDiscountPercent() / 100);
            }
        }catch (Exception EX){
            System.out.println("Error TransactionService getDiscountTotal");
            System.out.println(EX.toString());
        }
        return discountTotal;
    }
}
