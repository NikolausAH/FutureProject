package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class ProfileController {
    private final ProfileService profileService = new ProfileService(); 

    @Autowired
    public ProfileController() {
    }
    @RequestMapping("/Profile")
    public String Sh(HttpServletRequest request, Model model) {
        model.addAttribute("minimarket", profileService.());
        return "Minimarket";
    }
}
