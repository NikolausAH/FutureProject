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

    @RequestMapping("/Category")
    public String showAllCategory(Model model) {
        model.addAttribute("category", categoryService.showAllCategory());
        return "Category";
    }

    @PostMapping(value = "/createCategory")
    public ModelAndView createCategory(@ModelAttribute("category") Category category){
        ModelAndView mav = new ModelAndView();
        categoryService.createCategory(category);
        mav.setViewName("redirect:/Category");
        return mav;
    }

    @PostMapping(value = "updateCategory")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category){
        ModelAndView mav = new ModelAndView();
        categoryService.updateCategory(category);
        mav.setViewName("redirect:/Category");
        return mav;
    }

    @RequestMapping(value = "deleteCategory")
    public ModelAndView deleteCategory(@ModelAttribute("kode")Integer kode){
        ModelAndView mav = new ModelAndView();
        categoryService.deleteCategory(kode);
        mav.setViewName("redirect:/Category");
        return mav;
    }
}