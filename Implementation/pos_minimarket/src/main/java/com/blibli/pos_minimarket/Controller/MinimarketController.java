package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.Minimarket;
import com.blibli.pos_minimarket.Services.MinimarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MinimarketController {

    @Autowired
    private final MinimarketService minimarketService = new MinimarketService();

    @RequestMapping("/Minimarket")
    public String showMinimarket(HttpServletRequest request, Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null || employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        model.addAttribute("minimarket", minimarketService.showMinimarket());
        return "Minimarket";
    }
    @PostMapping(value = "/Minimarket/Update")
    public ModelAndView updateMinimarket(@ModelAttribute("minimarket") Minimarket minimarket){
        ModelAndView mav = new ModelAndView();
        minimarketService.updateMinimarket(minimarket);
        mav.setViewName("redirect:/Minimarket");
        return mav;
    }
}
