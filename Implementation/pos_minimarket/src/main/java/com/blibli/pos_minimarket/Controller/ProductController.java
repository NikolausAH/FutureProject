package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Services.CategoryService;
import com.blibli.pos_minimarket.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/Product")
    public String showAllProduct(HttpServletRequest request,@ModelAttribute("searchKey")String searchKey, Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null || employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        productService.initTable();
        model.addAttribute("productList", productService.search(searchKey));
        model.addAttribute("categoryList", categoryService.showAll());
        model.addAttribute("product_nextId", productService.getNextId());
        return "Product";
    }

    @RequestMapping(value = "/product/detail/{productId}", method = RequestMethod.GET)
    public String detailEmployee(HttpServletRequest request,@PathVariable Integer productId, Model model){
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null || employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        model.addAttribute("categoryList", categoryService.showAll());
        model.addAttribute("product", productService.getById(productId));
        return "ProductDetail";
    }

    @RequestMapping(value = "/Product/Add", method = RequestMethod.POST)
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
