package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Product;
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

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

   @RequestMapping("/Product")
    public String showAllProduct(Model model) {
        model.addAttribute("product", productService.showAll());
        return "Product";
    }

    @PostMapping(value = "/createProduct")
    public ModelAndView createProduct(@ModelAttribute("product") Product product){
        ModelAndView mav = new ModelAndView();
        productService.add(product);
        mav.setViewName("redirect:/Product");
        return mav;
    }

    @PostMapping(value = "updateProduct")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product){
        ModelAndView mav = new ModelAndView();
        productService.update(product);
        mav.setViewName("redirect:/Product");
        return mav;
    }

    @RequestMapping(value = "deleteProduct")
    public ModelAndView deleteProduct(@ModelAttribute("productId")Integer productId){
        ModelAndView mav = new ModelAndView();
        productService.delete(productId);
        mav.setViewName("redirect:/Product");
        return mav;
    }

    @RequestMapping("/Stock")
    public String showAllStock(Model model) {
        model.addAttribute("product", productService.showAll());
        return "Stock";
    }

    @PostMapping(value = "updateStock")
    public ModelAndView updateStock(@ModelAttribute("product") Product product){
        ModelAndView mav = new ModelAndView();
        productService.updateQuantity(product);
        mav.setViewName("redirect:/Stock");
        return mav;
    }
}
