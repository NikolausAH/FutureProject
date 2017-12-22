package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.promoDAO;
import com.blibli.pos_minimarket.Model.PromoProduct;
import com.blibli.pos_minimarket.Model.PromoXY;
import org.springframework.stereotype.Service;

@Service
public class promoServices {
    private promoDAO promoDAO = new promoDAO();
    public promoServices(){}

    public PromoProduct getPromoProduct(Integer productId){
        PromoProduct promoProduct = new PromoProduct();
        try{
            promoProduct = promoDAO.getPromoProduct(productId);
        }catch (Exception EX){
            System.out.println("Error promoServices getPromoProduct");
            System.out.println(EX.toString());
        }
        return promoProduct;
    }

    public PromoXY getPromoXY(Integer productId){
        PromoXY promoXY = null;
        try{
            promoXY = promoDAO.getPromoXY(productId);
        }catch (Exception EX){
            System.out.println("Error promoServices getPromoXY");
            System.out.println(EX.toString());
        }
        return promoXY;
    }
}
