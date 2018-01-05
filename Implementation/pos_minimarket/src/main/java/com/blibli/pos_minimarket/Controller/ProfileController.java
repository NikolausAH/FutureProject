package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Services.EmployeeService;
import com.blibli.pos_minimarket.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired //INJECT
    private ProfileService profileService;
    @Autowired
    private EmployeeService employeeService;

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
    @RequestMapping(value = "/Profile/Update" , method = RequestMethod.POST)
    public ModelAndView update(HttpServletRequest request, @ModelAttribute("Employeeupdated") Employee pegawai){
        ModelAndView mav = new ModelAndView();
        profileService.updateProfile(pegawai);
        mav.setViewName("redirect:/Profile");
        Employee employee = employeeService.getById(pegawai.getEmployee_Id());
        request.getSession().setAttribute("pegawai", employee);
        return mav;
    }
    @RequestMapping(value = "/Profile/ChangePassword", method = RequestMethod.POST)
    public ModelAndView changePassword(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        String passwordLama = request.getParameter("passwordLama");
        String passwordBaru1 = request.getParameter("passwordBaru1");
        String passwordBaru2 = request.getParameter("passwordBaru2");
        passwordBaru1 = profileService.encrypt(passwordBaru1);
        passwordBaru2 = profileService.encrypt(passwordBaru2);
        passwordLama = profileService.encrypt(passwordLama);
        Integer id = employee.getEmployee_Id();
        String password = profileService.getPasswordById(id);
        if(passwordLama.equals(password) && passwordBaru1.equals(passwordBaru2)){
        profileService.changePassword(id,passwordBaru1);
        }
        else System.out.println("error ganti pass");
        mav.setViewName("redirect:/Profile");
        return mav;
    }
}
