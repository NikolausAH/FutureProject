package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    public ModelAndView doLogin(HttpServletRequest request, @ModelAttribute("Username") Integer id, @ModelAttribute("Password") String password){
        Map map = loginService.doLogin(id, password);
        ModelAndView mav;
        if((boolean) map.get("loginError")){
            mav = new ModelAndView("Login");
            mav.addObject("loginError", map.get("loginError"));
        } else {
            mav = new ModelAndView("Category");
            Employee employee = (Employee) map.get("pegawai");
            mav.addObject("pegawai", employee);
            request.getSession().setAttribute("pegawai", employee);
        }
        return mav;
    }

}
