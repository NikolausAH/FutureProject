package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Promo;
import com.blibli.pos_minimarket.Model.PromoTotal;
import com.blibli.pos_minimarket.Services.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PromoController {
    private final PromoService promoService;
    @Autowired
    public PromoController(PromoService promoService) {
        this.promoService = promoService;
    }

    @RequestMapping(value = "Promo")
    public String getAll(Model model) {
        promoService.initTable();
        model.addAttribute("promo", promoService.showAll());

        try {
            List<Promo> promoList = promoService.showAll();
            if(promoList!=null){
                System.out.println("sjhkahdkjsahdskhdjas");
                System.out.println(promoList.size());
            }
            for (Promo PromoTotalList : promoList) {
                System.out.println("uuuuuuuuuuuuuuuuuuuuuuuu");
                System.out.println(PromoTotalList.getStatus());
            }
        }catch (Exception EX){
            System.out.println("HHHHHHHHHHHHHHHHHHHHHH");
        }

        return "Promo";
    }}

