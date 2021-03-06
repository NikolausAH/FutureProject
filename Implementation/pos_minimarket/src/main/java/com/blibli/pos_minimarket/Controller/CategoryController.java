package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Category;
import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "Category")
    public String showAllCategory(HttpServletRequest request ,@ModelAttribute("searchKey")String searchKey,Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null || employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        categoryService.initTable();
        model.addAttribute("categoryList", categoryService.search(searchKey));
        model.addAttribute("category_nextId", categoryService.getNextId());
        return "Category";
    }

    @RequestMapping(value = "/category/detail/{categoryId}", method = RequestMethod.GET)
    public String detailCategory(HttpServletRequest request ,@PathVariable Integer categoryId, Model model){
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null || employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        model.addAttribute("category", categoryService.getById(categoryId));
        return "CategoryDetail";
    }

    @RequestMapping(value = "/Category/Add", method = RequestMethod.POST)
    public ModelAndView addCategory(@ModelAttribute("category") Category category){
        ModelAndView mav = new ModelAndView();
        category.setCategoryId(categoryService.getNextId());
        categoryService.add(category);
        mav.setViewName("redirect:/Category");
        return mav;
    }

    @RequestMapping(value = "/Category/Detail", params = "update", method = RequestMethod.POST)
    public ModelAndView updateProduct(@ModelAttribute("category") Category category){
        ModelAndView mav = new ModelAndView();
        categoryService.update(category);
        mav.setViewName("redirect:/Category");
        return mav;
    }

    @RequestMapping(value = "/Category/Detail", params = "delete", method = RequestMethod.POST)
    public ModelAndView deleteCategory(@ModelAttribute("category")Category category){
        ModelAndView mav = new ModelAndView();
        categoryService.softDelete(category.getCategoryId());
        mav.setViewName("redirect:/Category");
        return mav;
    }
    @RequestMapping(value = "/Category/Detail",params = "cancel", method = RequestMethod.POST)
    public String cancelCategory(){
        return "redirect:/Category";
    }
}