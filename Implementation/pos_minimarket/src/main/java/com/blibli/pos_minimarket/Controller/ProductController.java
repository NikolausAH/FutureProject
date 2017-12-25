package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Services.CategoryService;
import com.blibli.pos_minimarket.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService = new CategoryService();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/Product")
    public String showAllProduct(@ModelAttribute("searchKey")String searchKey, Model model) {
        productService.initTable();
        model.addAttribute("product", productService.search(searchKey));
        model.addAttribute("categoryList", categoryService.showAll());
        model.addAttribute("product_nextId", categoryService.getNextId());
        return "Product";
    }

    @PostMapping(value = "/Product/Add")
    public ModelAndView createProduct(@ModelAttribute("product") Product product,@ModelAttribute("categoryId")Integer categoryId){
        ModelAndView mav = new ModelAndView();
        product.setCategory(categoryService.getById(categoryId));
        product.setProductId(categoryService.getNextId());
        productService.add(product);
        mav.setViewName("redirect:/Product");
        return mav;
    }

    @PostMapping(value = "/Product/Update")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product,@ModelAttribute("categoryId")Integer categoryId){
        ModelAndView mav = new ModelAndView();
        product.setCategory(categoryService.getById(categoryId));
        productService.update(product);
        mav.setViewName("redirect:/Product");
        return mav;
    }

    @RequestMapping(value = "/Product/Delete")
    public ModelAndView deleteProduct(@ModelAttribute("productId")Integer productId){
        ModelAndView mav = new ModelAndView();
        productService.softDelete(productId);
        mav.setViewName("redirect:/Product");
        return mav;
    }

}
