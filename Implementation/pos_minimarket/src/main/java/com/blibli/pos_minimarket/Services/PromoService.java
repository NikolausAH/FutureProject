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

//    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

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
        Promo promo = new Promo();
        List<Promo> promoList = new ArrayList<>();
        List<PromoProduct> listProduk;
        List<PromoTotal> listTotal = new ArrayList<>();
        List<PromoXY> listXY = new ArrayList<>();
            listProduk = promoProductDAO.getAll();
            if (listProduk != null)
                try {
                    for (PromoProduct PromoProdukList : listProduk) {
                        promo.setId(PromoProdukList.getId());
                        promo.setStatus(PromoProdukList.getStatus());
                        promo.setStartDate(PromoProdukList.getStartDate());
                        promo.setEndDate(PromoProdukList.getEndDate());
                        promoList.add(promo);
                    }
                }
                catch (Exception EX){
                    System.out.println("error 1");
                    System.out.println(EX.toString());
                }
                if (listProduk != null)
                    try {
                        for (PromoTotal PromoTotalList : listTotal) {
                            promo.setId(PromoTotalList.getId());
                            promo.setStatus(PromoTotalList.getStatus());
                            promo.setStartDate(PromoTotalList.getStartDate());
                            promo.setEndDate(PromoTotalList.getEndDate());
                            promoList.add(promo);
                        }
                    }
                    catch (Exception EX){
                        System.out.println("error 2");
                        System.out.println(EX.toString());
                    }
                if (listProduk != null)
                    try {
                        for (PromoXY PromoXYList : listXY) {
                            promo.setId(PromoXYList.getId());
                            promo.setStatus(PromoXYList.getStatus());
                            promo.setStartDate(PromoXYList.getStartDate());
                            promo.setEndDate(PromoXYList.getEndDate());
                            promoList.add(promo);
                        }
                    }
                    catch (Exception EX){
                        System.out.println("error 3");
                        System.out.println(EX.toString());
                    }
        return promoList;
                }
            }
