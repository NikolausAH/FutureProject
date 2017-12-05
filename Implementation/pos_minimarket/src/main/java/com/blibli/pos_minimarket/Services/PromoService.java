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

    public void add(Promo promo, PromoXY promoXY, PromoProduct promoProduct, PromoTotal promoTotal) {
        try {
            if (promo.getType().equals("Promo XY")) {
                promoXY.setId(promo.getId());
                promoXY.setStartDate(promo.getStartDate());
                promoXY.setEndDate(promo.getEndDate());
                promoXY.setStatus(promo.getStatus());
                promoXYDAO.add(promoXY);
            } else if (promo.getType().equals("Promo Produk")) {
                promoProduct.setId(promo.getId());
                promoProduct.setStartDate(promo.getStartDate());
                promoProduct.setEndDate(promo.getEndDate());
                promoProduct.setStatus(promo.getStatus());
                promoProductDAO.add(promoProduct);
            } else if (promo.getType().equals("Promo Total")) {
                promoTotal.setId(promo.getId());
                promoTotal.setStartDate(promo.getStartDate());
                promoTotal.setEndDate(promo.getEndDate());
                promoTotal.setStatus(promo.getStatus());
                promoTotalDAO.add(promoTotal);
            }
        } catch (Exception EX) {
            System.out.println();
        }
    }

    public void update(Promo promo, PromoProduct promoProduct, PromoTotal promoTotal, PromoXY promoXY) {
        try {
            if (promo.getType().equals("Promo Produk")) {
                promoProduct.setId(promo.getId());
                promoProduct.setStartDate(promo.getStartDate());
                promoProduct.setEndDate(promo.getEndDate());
                promoProduct.setStatus(promo.getStatus());
                promoProductDAO.update(promoProduct);
            } else if (promo.getType().equals("Promo Total")) {
                promoTotal.setId(promo.getId());
                promoTotal.setStartDate(promo.getStartDate());
                promoTotal.setEndDate(promo.getEndDate());
                promoTotal.setStatus(promo.getStatus());
                promoTotalDAO.update(promoTotal);
            } else if (promo.getType().equals("Promo XY")) {
                promoXY.setId(promo.getId());
                promoXY.setStartDate(promo.getStartDate());
                promoXY.setEndDate(promo.getEndDate());
                promoXY.setStatus(promo.getStatus());
                promoXYDAO.update(promoXY);
            }
        } catch (Exception EX) {
            System.out.println(EX.toString());
            System.out.println("Error PromoService Update");
        }
    }
    public void delete(Promo promo,PromoXY promoXY, PromoTotal promoTotal, PromoProduct promoProduct){
        try {
            if (promo.getType().equals("Promo Produk")) {
                promoProductDAO.delete(promoProduct.getId());
            } else if (promo.getType().equals("Promo Total")) {
                promoTotalDAO.delete(promoTotal.getId());
            } else if (promo.getType().equals("Promo XY")) {
                promoXYDAO.delete(promoXY.getId());
            }
        } catch (Exception EX) {
            System.out.println(EX.toString());
            System.out.println("Error PromoService Delete");
        }
}
}
