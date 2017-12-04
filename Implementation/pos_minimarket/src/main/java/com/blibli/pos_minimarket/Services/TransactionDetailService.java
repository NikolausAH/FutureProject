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
    private ProductDAO productDAO = new ProductDAO();
    public TransactionDetailService() {
    }

    public void add(Transaction transaction){
        TransactionDetail transactionDetail = new TransactionDetail();
        List<Product> productList;
        /* EDIT INI PROMO*/
        PromoProduct promoProduct = new PromoProduct();
        PromoXY promoXY = new PromoXY();
        /* EDIT INI PROMO*/
        try {
            transactionDetail.setTransaction(transaction);
            productList = transactionDAO.getFromCart();
            for (Product ProductList : productList) {
                Product product = productDAO.getById(ProductList.getProductId());

                transactionDetail.setQuantity(ProductList.getQuantity());
                transactionDetail.setProduct(product);
                transactionDetail.setPrice(product.getPrice());
                transactionDetail.setDiscount(0.0);
                transactionDetail.setPromoProduct(promoProduct);
                transactionDetail.setPromoXY(promoXY);
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
}
