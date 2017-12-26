package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Category;
import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "Category")
    public String showAllCategory(HttpServletRequest request, @ModelAttribute("searchKey")String searchKey, Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        if (employee == null ||employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        categoryService.initTable();
        model.addAttribute("category", categoryService.search(searchKey));
        model.addAttribute("category_nextId", categoryService.getNextId());
        model.addAttribute("pegawai", employee);
        return "Category";
    }

    @PostMapping(value = "/Category/Add")
    public ModelAndView addCategory(@ModelAttribute("category") Category category){
        ModelAndView mav = new ModelAndView();
        category.setCategoryId(categoryService.getNextId());
        categoryService.add(category);
        mav.setViewName("redirect:/Category");
        return mav;
    }

    @PostMapping(value = "/Category/Update")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category){
        ModelAndView mav = new ModelAndView();
        categoryService.update(category);
        mav.setViewName("redirect:/Category");
        return mav;
    }

    @RequestMapping(value = "/Category/Delete")
    public ModelAndView deleteCategory(@ModelAttribute("categoryId")Integer categoryId){
        ModelAndView mav = new ModelAndView();
        categoryService.softDelete(categoryId);
        mav.setViewName("redirect:/Category");
        return mav;
    }
}