package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Services.CategoryService;
import com.blibli.pos_minimarket.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("productList", productService.search(searchKey));
        model.addAttribute("categoryList", categoryService.showAll());
        model.addAttribute("product_nextId", productService.getNextId());
        return "Product";
    }

    @RequestMapping(value = "/product/detail/{productId}", method = RequestMethod.GET)
    public String detailEmployee(@PathVariable Integer productId, Model model){
        model.addAttribute("categoryList", categoryService.showAll());
        model.addAttribute("product", productService.getById(productId));
        return "ProductDetail";
    }

    @PostMapping(value = "/Product/Add")
    public ModelAndView createProduct(@ModelAttribute("product") Product product,@ModelAttribute("categoryId")Integer categoryId){
        ModelAndView mav = new ModelAndView();
        product.setCategory(categoryService.getById(categoryId));
        product.setProductId(productService.getNextId());
        productService.add(product);
        mav.setViewName("redirect:/Product");
        return mav;
    }

    @RequestMapping(value = "/Product/Detail", params = "update", method = RequestMethod.POST)
    public ModelAndView updateProduct(@ModelAttribute("product") Product product,@ModelAttribute("categoryId")Integer categoryId){
        ModelAndView mav = new ModelAndView();
        product.setCategory(categoryService.getById(categoryId));
        productService.update(product);
        mav.setViewName("redirect:/Product");
        return mav;
    }

    @RequestMapping(value = "/Product/Detail", params = "delete", method = RequestMethod.POST)
    public ModelAndView deleteProduct(@ModelAttribute("productId")Integer productId){
        ModelAndView mav = new ModelAndView();
        productService.softDelete(productId);
        mav.setViewName("redirect:/Product");
        return mav;
    }

    @RequestMapping(value = "/Product/Detail",params = "cancel", method = RequestMethod.POST)
    public String cancelCategory(){
        return "redirect:/Product";
    }

}
