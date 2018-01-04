package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "Login")
    public String Main(HttpServletRequest request)
    {Employee employee = null;
        request.getSession().setAttribute("pegawai", employee);
        return "Login";
    }

    @RequestMapping(value = "Login/Enter")
    public ModelAndView doLogin(HttpServletRequest request, @ModelAttribute("Username") Integer id, @ModelAttribute("Password") String password) {
        password = loginService.encrypt(password);
        Employee employee = loginService.doLogin(id, password);
        ModelAndView mav = new ModelAndView();
        if (employee == null) { //if true
            mav = new ModelAndView("Login");
            mav.addObject("loginError", true);
        } else {
            if (employee.getRole().getName().equals("Admin")) {
                mav = new ModelAndView("redirect:/MainMenu");
                mav.addObject("pegawai", employee);
                request.getSession().setAttribute("pegawai", employee);
            }
            else if (employee.getRole().getName().equals("Kasir")) {
                mav = new ModelAndView("redirect:/Transaction");
                mav.addObject("pegawai", employee);
                request.getSession().setAttribute("pegawai", employee);
            }
        }
        return mav;
    }

    @RequestMapping("/MainMenu")
    public String showHomeMenu(HttpServletRequest request, Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null || employee.getRole().getName().equals("Kasir")) {
            return "Login"; //????
        }
        return "MainMenu";
    }

}
