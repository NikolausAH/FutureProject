package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired //menandakan fungsi/konstruktor akan di init secara otomatis saat program di Run.
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @RequestMapping(value = "Employee")
    public String showAllEmployee(Model model) {
        System.out.println(employeeService.showAll().size());
        model.addAttribute("Pegawai", employeeService.showAll());
        model.addAttribute("pgw", new Employee());
        model.addAttribute("PegawaiUpdated", new Employee());
        return "Employee";
    }
    @PostMapping(value = "createEmployee")
    public ModelAndView addEmployee(@ModelAttribute("pgw") Employee pgw){
        ModelAndView mav = new ModelAndView();
        employeeService.add(pgw);
        mav.setViewName("redirect:/Employee");
        return mav;
    }

    @PostMapping(value = "updateEmployee")
    public ModelAndView updateEmployee(@ModelAttribute("EmployeeUpdated") Employee PegawaiUpdated){
        ModelAndView mav = new ModelAndView();
        employeeService.update(PegawaiUpdated);
        mav.setViewName("redirect:/Employee");
        return mav;
    }

    @PostMapping(value = "deleteEmployee")
    public ModelAndView deleteEmployee(@ModelAttribute("id") Integer id){
        ModelAndView mav = new ModelAndView();
        employeeService.delete(id);
        mav.setViewName("redirect:/Employee");
        return mav;
    }
}
