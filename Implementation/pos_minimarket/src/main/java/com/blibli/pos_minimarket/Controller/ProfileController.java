package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Services.EmployeeService;
import com.blibli.pos_minimarket.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    private final ProfileService profileService = new ProfileService();
    private final EmployeeService employeeService = new EmployeeService();

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
    public ModelAndView update(HttpServletRequest request, @ModelAttribute("Employeeupdated") Employee pegawai){
        ModelAndView mav = new ModelAndView();
        profileService.updateProfile(pegawai);
        mav.setViewName("redirect:/Profile");
        Employee employee = employeeService.findById(pegawai.getEmployee_Id());
        request.getSession().setAttribute("pegawai", employee);
        return mav;
    }
    @PostMapping(value = "/Profile/ChangePassword")
    public ModelAndView changePassword(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        String passwordLama = request.getParameter("passwordLama");
        String passwordBaru1 = request.getParameter("passwordBaru1");
        String passwordBaru2 = request.getParameter("passwordBaru2");
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
