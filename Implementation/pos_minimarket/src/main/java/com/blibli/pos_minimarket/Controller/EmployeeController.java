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

import java.util.EnumMap;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService = new EmployeeService();
    private Integer employee_nextId = employeeService.getNextId();

    @Autowired //menandakan fungsi/konstruktor akan di init secara otomatis saat program di Run.
    public EmployeeController() {
    }
    @RequestMapping(value = "Employee")
    public String showAllEmployee(Model model) {
        model.addAttribute("employeeList", employeeService.showAll());
        model.addAttribute("roleList", employeeService.showAllRole());
        model.addAttribute("employee_nextId", employee_nextId);
        return "Employee";
    }
    @PostMapping(value = "Employee/Add")
    public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee, @ModelAttribute("role_id") Integer role_id){
        ModelAndView mav = new ModelAndView();
        employee.setRole(employeeService.getRoleById(role_id));
        employee.setEmployee_Id(employee_nextId);
        System.out.println(employee.getEmail());
        System.out.println(employee.getName());
        System.out.println(employee.getRole().getRole_id());
        employeeService.add(employee);
        mav.setViewName("redirect:/Employee");
        return mav;
    }

    @PostMapping(value = "Employee/Update")
    public ModelAndView updateEmployee(@ModelAttribute("employee") Employee employee, @ModelAttribute("role_id") Integer role_id){
        ModelAndView mav = new ModelAndView();
        System.out.println(employee.getEmployee_Id());
        System.out.println(employee.getName());
        System.out.println(employee.getEmail());
        employee.setRole(employeeService.getRoleById(role_id));
        employeeService.update(employee);
        mav.setViewName("redirect:/Employee");
        return mav;
    }

    @PostMapping(value = "Employee/Delete")
    public ModelAndView deleteEmployee(@ModelAttribute("employee_Id") Integer employee_id){
        ModelAndView mav = new ModelAndView();
        employeeService.softDelete(employee_id);
        mav.setViewName("redirect:/Employee");
        return mav;
    }
}
