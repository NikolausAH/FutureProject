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
        model.addAttribute("pegawai", employee);
        return "Promo";
    }

    @RequestMapping(value = "/Promo/AddXY")
    public ModelAndView addPromo(@ModelAttribute("promoXY") PromoXY promoXY) {
        ModelAndView mav = new ModelAndView();
        promoService.addPromoXY(promoXY);
        mav.setViewName("redirect:/Promo");
        return mav;
    }

    @RequestMapping(value = "/Promo/AddTotal")
    public ModelAndView addPromo(@ModelAttribute("promoTotal") PromoTotal promoTotal) {
        ModelAndView mav = new ModelAndView();
        promoService.addPromoTotal(promoTotal);
        mav.setViewName("redirect:/Promo");
        return mav;
    }

    @RequestMapping(value = "/Promo/AddProduct")
    public ModelAndView addPromo(@ModelAttribute("promoProduct") PromoProduct promoProduct) {
        ModelAndView mav = new ModelAndView();
        promoService.addPromoProduct(promoProduct);
        mav.setViewName("redirect:/Promo");
        return mav;
    }

    @RequestMapping(value = "/Promo/UpdateProduct")
    public ModelAndView updatePromo(@ModelAttribute("promoProduct") PromoProduct promoProduct) {
        ModelAndView mav = new ModelAndView();
        promoService.updatePromoProduct(promoProduct);
        mav.setViewName("redirect:/Promo");
        return mav;
    }

    @RequestMapping(value = "/Promo/UpdateTotal")
    public ModelAndView updatePromo(@ModelAttribute("promoTotal") PromoTotal promoTotal) {
        ModelAndView mav = new ModelAndView();
        promoService.updatePromoTotal(promoTotal);
        mav.setViewName("redirect:/Promo");
        return mav;
    }

    @RequestMapping(value = "/Promo/UpdateXY")
    public ModelAndView updatePromo(@ModelAttribute("promoXY") PromoXY promoXY) {
        ModelAndView mav = new ModelAndView();
        promoService.updatePromoXY(promoXY);
        mav.setViewName("redirect:/Promo");
        return mav;
    }

    @RequestMapping(value = "/Promo/Delete")
    public ModelAndView deletePromo(@ModelAttribute("deleteId") Integer id) {
        ModelAndView mav = new ModelAndView();
        promoService.delete(id);
        mav.setViewName("redirect:/Promo");
        return mav;
    }
}

