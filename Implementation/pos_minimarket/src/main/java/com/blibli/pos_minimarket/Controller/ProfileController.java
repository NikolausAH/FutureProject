package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    private final ProfileService profileService = new ProfileService();

    @Autowired
    public ProfileController() {
    }
    @RequestMapping("/Profile")
    public String Show(HttpServletRequest request, Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        if (employee == null) {
            return "Login";
        } else if (employee.getRole().getName().equals("Kasir")) {
            model.addAttribute("pegawai", employee);
            return "ProfileCashier";
        } else{
            model.addAttribute("pegawai", employee);
            return "ProfileAdmin";
        }
    }
    @PostMapping(value = "/Profile/Update")
    public ModelAndView update(@ModelAttribute("Employeeupdated") Employee pegawai){
        ModelAndView mav = new ModelAndView();
        profileService.updateProfile(pegawai);
        mav.setViewName("redirect:/Profile");
        return mav;
    }
}
