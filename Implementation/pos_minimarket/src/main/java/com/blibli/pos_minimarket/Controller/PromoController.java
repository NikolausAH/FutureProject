package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.DataAccessObject.PromoTotalDAO;
import com.blibli.pos_minimarket.Model.*;
import com.blibli.pos_minimarket.Services.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPBinding;

@Controller
public class PromoController {
    private final PromoService promoService;

    @Autowired
    public PromoController(PromoService promoService) {
        this.promoService = promoService;
    }

    @RequestMapping(value = "Promo")
    public String getAll(HttpServletRequest request, Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        if (employee == null ||employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        promoService.initTable();
        model.addAttribute("promo", promoService.showAll());
        return "Promo";
    }

    @RequestMapping(value = "/Promo/Add")
    public ModelAndView addPromo(@ModelAttribute("promoAdd") Promo promo, @ModelAttribute("promoXY") PromoXY promoXY, @ModelAttribute("promoTotal") PromoTotal promoTotal, @ModelAttribute("promoProduct") PromoProduct promoProduct) {
        ModelAndView mav = new ModelAndView();
        promoService.add(promo, promoXY, promoProduct, promoTotal);
        mav.setViewName("redirect:/Promo");
        return mav;
    }

    @RequestMapping(value = "/Promo/Update")
    public ModelAndView updatePromo(@ModelAttribute("promoUpdated") Promo promo, @ModelAttribute("promoXY") PromoXY promoXY, @ModelAttribute("promoTotal") PromoTotal promoTotal, @ModelAttribute("promoProduct") PromoProduct promoProduct) {
        ModelAndView mav = new ModelAndView();
        promoService.update(promo, promoProduct, promoTotal, promoXY);
        mav.setViewName("redirect:/Promo");
        return mav;
    }

    @RequestMapping(value = "/Promo/Delete")
    public ModelAndView deletePromo(@ModelAttribute("promoDelete") Promo promo, @ModelAttribute("promoXY") PromoXY promoXY, @ModelAttribute("promoTotal") PromoTotal promoTotal, @ModelAttribute("promoProduct") PromoProduct promoProduct) {
        ModelAndView mav = new ModelAndView();
        promoService.delete(promo, promoXY, promoTotal, promoProduct);
        mav.setViewName("redirect:/Promo");
        return mav;
    }
}

