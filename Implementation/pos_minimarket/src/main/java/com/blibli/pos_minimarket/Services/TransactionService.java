package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDAO;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDetailDAO;
import com.blibli.pos_minimarket.Model.*;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class TransactionService {
    private TransactionDAO transactionDAO = new TransactionDAO();
    private ProductDAO productDAO = new ProductDAO();
    private MinimarketService minimarketService = new MinimarketService();
    private ProductService productService = new ProductService();
    private TransactionDetailService transactionDetailService = new TransactionDetailService();
    private PromoService promoService = new PromoService();
    private TransactionDetailDAO transactionDetailDAO = new TransactionDetailDAO();
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

    private Double sumDiscount(){
        Double discount = 0.0;
        List<TransactionDetail> transactionDetailList = new ArrayList<>();
        transactionDetailList = this.getAllFromCart();
        for(TransactionDetail transactionDetail : transactionDetailList){
            discount += transactionDetail.getDiscount();
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
                    Double discount = TransactionDetailList.getDiscount();

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

    public void addToCart(String searchKey, Integer quantity){
        try {
            Product product = new Product();
            product = productDAO.searchInCart(searchKey);
            if(product!=null){
                this.addIfBxGy(product.getProductId(),quantity);
                if (this.isAnyInCart(product)){
                    transactionDAO.addQuantityInCart(product.getProductId(),quantity);
                }
                else {
                    transactionDAO.addToCart(product.getProductId(),quantity,0);
                }
            }
            else {
                System.out.println("TransactionService AddToCart Product can not add to Cart");
            }
        }catch (Exception EX){
            System.out.println("Error TransactionService AddToCart");
            System.out.println(EX.toString());
        }
    }

    public void addIfBxGy(Integer productId, Integer quantity){
        PromoXY promoXY = new PromoXY();
        try{
            promoXY = promoService.getPromoXYByProductId(productId);
            Integer multi = quantity / promoXY.getQuantityX();
            Integer newQuantity = promoXY.getQuantityY() * multi;
            Product product = productDAO.getById(promoXY.getProductYId());
            if (this.isAnyInCart(product)){
                transactionDAO.addQuantityInCart(product.getProductId(),newQuantity);
                transactionDAO.updateStatusInCart(product.getProductId());
            }
            else {
                transactionDAO.addToCart(product.getProductId(),newQuantity,1);
            }
        }catch (Exception EX){
            System.out.println(EX.toString());
        }
    }

    boolean isAnyInCart(Product product){
        List<Product> productList;
        productList = transactionDAO.getFromCart();
        for (Product ProductList : productList) {
            if (ProductList.getProductId().equals(product.getProductId())) {
                return true;
            }
        }
        return false;
    }

    public void addDetail(Transaction transaction){
        List<TransactionDetail> transactionDetailList;
        try {
            System.out.println("abc");
            transactionDetailList = this.getAllFromCart();
            System.out.println("abc");
            for (TransactionDetail transactionDetail : transactionDetailList) {
                System.out.println("abc1");
                TransactionDetail temp = new TransactionDetail();
                System.out.println("abc2");
//                temp = transactionDetail;

                temp.setDetail_Id(0);
                System.out.println("abc2");
                temp.setTransaction(transaction);
                System.out.println("abc2");
                temp.setPromoProduct(transactionDetail.getPromoProduct());
                System.out.println("abc2");
                temp.setProduct(transactionDetail.getProduct());
                System.out.println("abc2");
                temp.setPrice(transactionDetail.getPrice());
                System.out.println("abc2");
                temp.setDiscount(transactionDetail.getDiscount());
                System.out.println("abc2");
                temp.setQuantity(transactionDetail.getQuantity());
                System.out.println("abc2");
                temp.setPromoXY(transactionDetail.getPromoXY());
                System.out.println("abc2");
                if (temp.getPromoXY().getId() == 0)
                    System.out.println("prekkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
//                if (temp.getPromoProduct().getId() == 0)
                    System.out.println("prekkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwaaaaaaaaaaaaaaa");
                System.out.println(temp.getPromoXY().getId());
                System.out.println(transactionDetail.getPromoProduct().getId());

                System.out.println(transaction.getTransactionId());
                System.out.println(transactionDetail.getDetail_Id());
                System.out.println(transactionDetail.getPromoProduct().getId());
//                System.out.println(transactionDetail.getTransaction().getTransactionId());
                System.out.println(transactionDetail.getProduct().getProductId());
                System.out.println(transactionDetail.getPrice());
                System.out.println(transactionDetail.getDiscount());
                System.out.println(transactionDetail.getQuantity());
                PromoXY promoXY = new PromoXY();
                promoXY.setId(12);
                transactionDetail.setPromoXY(promoXY);
                System.out.println(transactionDetail.getPromoXY().getId());
                try {

                    transactionDetailDAO.add(temp);
                }catch (Exception EX){
                    System.out.println(EX.toString());
                }
                System.out.println(temp.getDetail_Id());
                System.out.println(temp.getPromoProduct().getId());
                System.out.println(temp.getTransaction().getTransactionId());
                System.out.println(temp.getProduct().getProductId());
                System.out.println(temp.getPrice());
                System.out.println(temp.getDiscount());
                System.out.println(temp.getQuantity());
                System.out.println(temp.getPromoXY().getProductXId());
            }
        }
        catch (Exception EX){
            System.out.println("Error TransactionService Add Detail");
            System.out.println(EX.toString());
        }
    }

    public List<TransactionDetail> getAllFromCart() {
        List<Product> productList;
        List<TransactionDetail> transactionDetailList = new ArrayList<>();
        Double discount = 0.0;
        try {
            productList = transactionDAO.getFromCart();
            for (Product ProductList : productList) {
                TransactionDetail transactionDetail = new TransactionDetail();
                Product product = productDAO.getById(ProductList.getProductId());
                transactionDetail.setQuantity(ProductList.getQuantity());
                transactionDetail.setPrice(product.getPrice());
                transactionDetail.setProduct(product);
                PromoXY promoXY = new PromoXY();
                PromoProduct promoProduct = new PromoProduct();
                promoXY.setId(0);
                promoProduct.setId(0);
                try{
                    promoXY = promoService.getPromoXYByProductId(product.getProductId());
                }catch (Exception EX){
                    System.out.println("Product "+product.getProductId()+"tidak mendapat bonus bxgy");
                }
                if(ProductList.getDescription().equals("1")){
                    try{
                        PromoXY promoXY2 = promoService.getPromoXYByBonusId(product.getProductId());
                        promoProduct = promoService.getPromoProductByProductId(product.getProductId());
                        if(ProductList.getQuantity() >= promoXY2.getQuantityY()) {
                            Integer multi = ProductList.getQuantity() / promoXY2.getQuantityY();

                            Integer newQuantity = promoXY2.getQuantityY() * multi;
                            discount += newQuantity * product.getPrice();
                        }
                        Integer mod = ProductList.getQuantity() % promoXY2.getQuantityY();
                        if(promoProduct != null) {
                         discount += mod * product.getPrice() * (promoProduct.getDiscountPercent() / 100);
                        }
                    }catch (Exception EX){
                        System.out.println(EX.toString());
                    }
                }else {
                    try{
                        System.out.println("promoProductId nihhh" +promoProduct.getId());
                        promoProduct = promoService.getPromoProductByProductId(product.getProductId());
                        System.out.println("aaaaa");
                        System.out.println("promoProductId nihhh" +promoProduct.getId());
//                        if(promoProduct != null) {
                            discount += ProductList.getQuantity() * product.getPrice() * (promoProduct.getDiscountPercent() / 100);
//                        }
                        System.out.println("promoProductId nihhh" +promoProduct.getId());
                    }catch (Exception EX){
                        System.out.println("Product "+product.getProductId()+"tidak mendapat promo diskon");
                    }
                }
                transactionDetail.setDiscount(discount);
                transactionDetail.setPromoProduct(promoProduct);
                transactionDetail.setPromoXY(promoXY);
                System.out.println("promoProductId nihhh" +promoProduct.getId());
                transactionDetailList.add(transactionDetail);
                discount = 0.0;

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
        Double discountTotal = this.sumDiscount(); //belum
        Integer p_total_id = 0; //belum
        Integer employee_id = 0; //belum
        try {
            transaction.setTax(tax);
            transaction.setTotal(total);
            transaction.setDiscount(discountTotal);
            transaction.setDateTime(dateTime);
            transaction.setTransactionId(transactionDAO.getNextId());
            transactionDAO.add(transaction);
            this.addDetail(transaction);
            transactionDAO.removeAllFromCart();
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

    public Transaction getById(Integer transactionId) {
        Transaction transaction = new Transaction();
        try{
            transaction = transactionDAO.getById(transactionId);
        }catch (Exception EX)
        {
            System.out.println("Error TransactionService getById");
            System.out.println(EX.toString());
        }
        return transaction;
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

    public void removeAllFromCart(){
        try{
            transactionDAO.removeAllFromCart();
        }catch (Exception EX){
            System.out.println("Error TransactionService removeAllFromCart");
            System.out.println(EX.toString());
        }
    }

    public void removeFromCartByProductId(Integer productId){
        try{
            transactionDAO.removeFromCartByProductId(productId);
        }catch (Exception EX){
            System.out.println("Error TransactionService removeFromCartByProductId");
            System.out.println(EX.toString());
        }
    }
}
