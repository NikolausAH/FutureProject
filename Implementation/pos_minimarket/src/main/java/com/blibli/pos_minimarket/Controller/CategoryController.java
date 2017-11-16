package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Category;
import com.blibli.pos_minimarket.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "Category")
    public String showAllCategory(Model model) {
        model.addAttribute("category", categoryService.showAll());
        return "Category";
    }

    @RequestMapping(value = "searchCategory")
    public String searchCategory(@ModelAttribute("searchKey")String searchKey,Model model){
        model.addAttribute("category", categoryService.search(searchKey));
        return "Category";
    }

    @PostMapping(value = "createCategory")
    public ModelAndView addCategory(@ModelAttribute("category") Category category){
        ModelAndView mav = new ModelAndView();
        categoryService.add(category);
        mav.setViewName("redirect:/Category");
        return mav;
    }

    @PostMapping(value = "updateCategory")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category){
        ModelAndView mav = new ModelAndView();
        categoryService.update(category);
        mav.setViewName("redirect:/Category");
        return mav;
    }

    @RequestMapping(value = "softDeleteCategory")
    public ModelAndView deleteCategory(@ModelAttribute("categoryId")Integer categoryId){
        ModelAndView mav = new ModelAndView();
        categoryService.softDelete(categoryId);
        mav.setViewName("redirect:/Category");
        return mav;
    }
}