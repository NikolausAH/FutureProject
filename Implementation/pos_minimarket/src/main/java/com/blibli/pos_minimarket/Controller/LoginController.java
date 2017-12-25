package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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

    @RequestMapping(value = "Login/Enter")
    public ModelAndView doLogin(@ModelAttribute("Username") Integer id, @ModelAttribute("Password") String password){
        ModelAndView mav = new ModelAndView();
        Map map = loginService.doLogin(id, password);
        if((boolean) map.get("loginError")){
            mav.addObject("loginError", (boolean) map.get("loginError"));
            mav.setViewName("redirect:/Login");
        }
        return mav;
    }

}
