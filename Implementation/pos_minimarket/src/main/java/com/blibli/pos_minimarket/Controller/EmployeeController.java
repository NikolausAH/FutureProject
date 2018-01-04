package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService = new EmployeeService();

    @Autowired //menandakan fungsi/konstruktor akan di init secara otomatis saat program di Run.
    public EmployeeController() {
    }

    @RequestMapping(value = "Employee")
    public String showAllEmployee(HttpServletRequest request,@ModelAttribute("searchKey")String searchKey, Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null || employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        employeeService.initTable();
        model.addAttribute("employeeList", employeeService.search(searchKey));
        model.addAttribute("roleList", employeeService.showAllRole());
        model.addAttribute("employee_nextId", employeeService.getNextId());
        return "Employee";
    }

    @RequestMapping(value = "/employee/detail/{employee_Id}", method = RequestMethod.GET)
    public String detailEmployee(HttpServletRequest request ,@PathVariable Integer employee_Id, Model model){
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null || employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        model.addAttribute("roleList", employeeService.showAllRole());
        model.addAttribute("employee", employeeService.getById(employee_Id));
        return "EmployeeDetail";
    }

    @PostMapping(value = "Employee/Add")
    public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee, @ModelAttribute("role_id") Integer role_id){
        ModelAndView mav = new ModelAndView();
        employee.setRole(employeeService.getRoleById(role_id));
        employee.setEmployee_Id(employeeService.getNextId());
        employee.setPassword("Password");
        employee.setPassword(employeeService.encrypt(employee.getPassword()));
        employeeService.add(employee);
        mav.setViewName("redirect:/Employee");
        return mav;
    }

    @RequestMapping(value = "/Employee/Detail", params = "update", method = RequestMethod.POST)
    public ModelAndView updateEmployee(@ModelAttribute("employee") Employee employee, @ModelAttribute("role_id") Integer role_id){
        ModelAndView mav = new ModelAndView();
        employee.setRole(employeeService.getRoleById(role_id));
        employeeService.update(employee);
        mav.setViewName("redirect:/Employee");
        return mav;
    }

    @RequestMapping(value = "/Employee/Detail", params = "delete", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(@ModelAttribute("employee") Employee employee){
        ModelAndView mav = new ModelAndView();
        employeeService.softDelete(employee.getEmployee_Id());
        mav.setViewName("redirect:/Employee");
        return mav;
    }

    @RequestMapping(value = "/Employee/Detail",params = "cancel", method = RequestMethod.POST)
    public String cancelCategory(){
        return "redirect:/Employee";
    }
}
