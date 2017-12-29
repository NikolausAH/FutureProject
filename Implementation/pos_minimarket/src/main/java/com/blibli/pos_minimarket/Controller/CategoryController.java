package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Category;
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

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "Category")
    public String showAllCategory(@ModelAttribute("searchKey")String searchKey,Model model) {
        categoryService.initTable();
        model.addAttribute("category", categoryService.search(searchKey));
        model.addAttribute("category_nextId", categoryService.getNextId());
        return "Category";
    }

    @RequestMapping(value = "/category/detail/{categoryId}", method = RequestMethod.GET)
    public String detailCategory(@PathVariable Integer categoryId, Model model){
        model.addAttribute("category", categoryService.getById(categoryId));
        return "CategoryDetail";
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
    public ModelAndView updateProduct(@ModelAttribute("category") Category category){
        ModelAndView mav = new ModelAndView();
        categoryService.update(category);
        mav.setViewName("redirect:/Category");
        return mav;
    }

    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public ModelAndView deleteCategory(@ModelAttribute("category")Category category){
        System.out.println(category.getCategoryId());
        ModelAndView mav = new ModelAndView();
        categoryService.softDelete(category.getCategoryId());
        mav.setViewName("redirect:/Category");
        return mav;
    }
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public String cancelUpdateCategory(){
        return "redirect:/Category";
    }
}