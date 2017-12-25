package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "Login")
    public String Main() {
        return "Login";
    }
    @RequestMapping(value = "/Login/Enter")
    public ModelAndView addPromo(@ModelAttribute("Username") String username, @ModelAttribute("Password") String password){
        ModelAndView mav = new ModelAndView();
        loginService.Login(username, password);
        mav.setViewName("redirect:/Login");
        return mav;
    }

}
