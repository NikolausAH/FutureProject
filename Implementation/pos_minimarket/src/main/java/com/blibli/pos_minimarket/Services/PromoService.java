package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.PromoProductDAO;
import com.blibli.pos_minimarket.DataAccessObject.PromoTotalDAO;
import com.blibli.pos_minimarket.DataAccessObject.PromoXYDAO;
import com.blibli.pos_minimarket.Model.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class PromoService {
    PromoProductDAO promoProductDAO = new PromoProductDAO();
    PromoXYDAO promoXYDAO = new PromoXYDAO();
    PromoTotalDAO promoTotalDAO = new PromoTotalDAO();
    private Product product = new Product();

    public PromoService() {
    }

    public void initTable() {
        try {
            promoProductDAO.initTable();
            promoTotalDAO.initTable();
            promoXYDAO.initTable();
        } catch (Exception EX) {
            System.out.println("Error CategoryService initTable");
            System.out.println(EX.toString());
        }
    }

    public List<Promo> showAll() {

        List<Promo> promoList = new ArrayList<>();
        List<PromoProduct> promoProductList;
        List<PromoTotal> promoTotalList;
        List<PromoXY> promoXYList;
        promoProductList = promoProductDAO.getAll();
        promoTotalList = promoTotalDAO.getAll();
        promoXYList = promoXYDAO.getAll();
        PromoTotalDAO promoTotalDAO = new PromoTotalDAO();
        if (promoTotalList != null) {
            try {
                for (PromoTotal PromoTotalList : promoTotalList) {
                    Promo promo = new Promo();
                    promo.setId(PromoTotalList.getId());
                    promo.setStatus(PromoTotalList.getStatus());
                    promo.setStartDate(PromoTotalList.getStartDate());
                    promo.setEndDate(PromoTotalList.getEndDate());
                    promoList.add(promo);
                }
            } catch (Exception EX) {
                System.out.println("Error PromoService add");
                System.out.println(EX.toString());
            }
        }
        if (promoProductList != null) {
            try {
                for (PromoProduct PromoProdukList : promoProductList) {
                    Promo promo = new Promo();
                    promo.setId(PromoProdukList.getId());
                    promo.setStatus(PromoProdukList.getStatus());
                    promo.setStartDate(PromoProdukList.getStartDate());
                    promo.setEndDate(PromoProdukList.getEndDate());
                    promoList.add(promo);
                }
            } catch (Exception EX) {
                System.out.println(EX.toString());
            }
        }
        if (promoXYList != null) {
            try {
                for (PromoXY PromoXYList : promoXYList) {
                    Promo promo = new Promo();
                    promo.setId(PromoXYList.getId());
                    promo.setStatus(PromoXYList.getStatus());
                    promo.setStartDate(PromoXYList.getStartDate());
                    promo.setEndDate(PromoXYList.getEndDate());
                    promoList.add(promo);
                }
            } catch (Exception EX) {
                System.out.println(EX.toString());
            }
        }
        return promoList;
    }

    public void addPromoXY(PromoXY promoXY) {
        try {
                promoXYDAO.add(promoXY);
        } catch (Exception EX) {
            System.out.println();
            System.out.println("Error PromoService addPromoXY");
        }
    }

    public void addPromoProduct(PromoProduct promoProduct) {
        try {
            promoProductDAO.add(promoProduct);
        } catch (Exception EX) {
            System.out.println();
            System.out.println("Error PromoService addPromoProduct");
        }
    }

    public void addPromoTotal(PromoTotal promoTotal) {
        try {
            promoTotalDAO.add(promoTotal);
        } catch (Exception EX) {
            System.out.println();
            System.out.println("Error PromoService addPromoTotal");
        }
    }

    public void updatePromoProduct (PromoProduct promoProduct) {
        try {
            promoProductDAO.update(promoProduct);
            }
            catch (Exception EX) {
            System.out.println(EX.toString());
            System.out.println("Error PromoService UpdatePromoProduct");
        }
    }

    public void updatePromoXY (PromoXY promoXY) {
        try {
            promoXYDAO.update(promoXY);
        }
        catch (Exception EX) {
            System.out.println(EX.toString());
            System.out.println("Error PromoService UpdatePromoXY");
        }
    }

    public void updatePromoTotal (PromoTotal promoTotal) {
        try {
            promoTotalDAO.update(promoTotal);
        }
        catch (Exception EX) {
            System.out.println(EX.toString());
            System.out.println("Error PromoService UpdatePromoTotal");
        }
    }

    public void delete(Integer id){
        try {
            promoTotalDAO.delete(id);
            promoXYDAO.delete(id);
            promoProductDAO.delete(id);
        } catch (Exception EX) {
            System.out.println(EX.toString());
            System.out.println("Error PromoService Delete");
        }
}

    public void updateStatus (Long currentTime){
        try{
            promoProductDAO.updateStatus(currentTime);
            promoTotalDAO.updateStatus(currentTime);
            promoXYDAO.updateStatus(currentTime);
        }
        catch (Exception EX){
            System.out.println(EX.toString());
            System.out.println("Error PromoService updateStatus");
        }
    }

    public PromoTotal getPromoTotal (Integer id){
        PromoTotal promoTotal = new PromoTotal();
        try{
           promoTotal =  promoTotalDAO.getById(id);
        }
        catch (Exception EX){
            System.out.println(EX.toString());
            System.out.println("Error PromoService getPromoTotal");
        }
        return promoTotal;
    }
}
