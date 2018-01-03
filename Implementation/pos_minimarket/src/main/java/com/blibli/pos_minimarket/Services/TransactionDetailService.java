package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDAO;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDetailDAO;

import com.blibli.pos_minimarket.Model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionDetailService {
    private TransactionDetailDAO transactionDetailDAO = new TransactionDetailDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();
//    private promoServices promoServices = new promoServices();
    private ProductDAO productDAO = new ProductDAO();
    public TransactionDetailService() {
    }

    public void add(Transaction transaction){
        TransactionDetail transactionDetail = new TransactionDetail();
        List<Product> productList;
        try {
            transactionDetail.setTransaction(transaction);
            productList = transactionDAO.getFromCart();
            for (Product ProductList : productList) {
                Product product = productDAO.getById(ProductList.getProductId());
                transactionDetail.setQuantity(ProductList.getQuantity());
                transactionDetail.setProduct(product);
                transactionDetail.setPrice(product.getPrice());
//                PromoProduct promoProduct = promoServices.getPromoProduct(product.getProductId());
//                PromoXY promoXY = promoServices.getPromoXY(product.getProductId());
//                if(promoXY!=null && ProductList.getQuantity() >= promoXY.getQuantityX()){
//                    TransactionDetail tempDetail = new TransactionDetail();
//                    Integer multi = ProductList.getQuantity() / promoXY.getQuantityX();
//                    tempDetail.setQuantity(promoXY.getQuantityY() * multi);
//                    Product product1 = productDAO.getById(promoXY.getProductYId());
//                    tempDetail.setProduct(product1);
//                    tempDetail.setPrice(product1.getPrice());
//                    tempDetail.setTransaction(transaction);
//                    tempDetail.setDiscount(product1.getPrice() * tempDetail.getQuantity());
//                    transactionDetailDAO.add(tempDetail);
//                }
//                Double discount = 0.0;
//                if(promoProduct!=null) {
//                    discount = ProductList.getQuantity() * product.getPrice() * (promoProduct.getDiscountPercent() / 100);
//                }
//                transactionDetail.setDiscount(discount);
//                transactionDetail.setPromoProduct(promoProduct);

//                transactionDetail.setPromoXY(promoXY);
                transactionDetailDAO.add(transactionDetail);
            }
        }
        catch (Exception EX){
            System.out.println("Error TransactionDetailService Add");
            System.out.println(EX.toString());
        }
    }

    public List<TransactionDetail> showOne(Integer searchKey) {
        List<TransactionDetail> transactionDetailList = new ArrayList<>();
        try {
            transactionDetailList = transactionDetailDAO.getByIdTransaction(searchKey);
        }catch (Exception EX){
            System.out.println("Error TransactionDetailService showOne");
            System.out.println(EX.toString());
        }
        return transactionDetailList;
    }

    public List<Integer> getOne() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        return integerList;
    }
}
