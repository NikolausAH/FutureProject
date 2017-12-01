package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Minimarket;
import com.blibli.pos_minimarket.Services.MinimarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MinimarketController {
    private final MinimarketService minimarketService = new MinimarketService();

    @Autowired //menandakan fungsi/konstruktor akan di init secara otomatis saat program di Run.
    public MinimarketController() {
    }
    @RequestMapping("/Minimarket")
    public String showAllProduct(Model model) {
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
