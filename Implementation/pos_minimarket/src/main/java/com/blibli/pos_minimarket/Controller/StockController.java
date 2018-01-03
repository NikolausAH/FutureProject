package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Services.ProductService;
import com.blibli.pos_minimarket.Services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StockController {

    private ProductService productService = new ProductService();
    private StockService stockService = new StockService();

    @Autowired
    public StockController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/Stock")
    public String showAllProduct(HttpServletRequest request ,@ModelAttribute("searchKey")String searchKey, Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null || employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        productService.initTable();
        model.addAttribute("productList", productService.search(searchKey));
        return "Stock";
    }

    @RequestMapping(value = "/stock/detail/{productId}", method = RequestMethod.GET)
    public String detailEmployee(@PathVariable Integer productId, Model model){
        model.addAttribute("product", productService.getById(productId));
        return "StockDetail";
    }

    @PostMapping(value = "/Stock/Update")
    public ModelAndView updateStock(@ModelAttribute("product") Product product, @ModelAttribute("addQuantity") Integer quantity){
        ModelAndView mav = new ModelAndView();
        stockService.updateQuantity(product,quantity);
        mav.setViewName("redirect:/Stock");
        return mav;
    }

    @RequestMapping(value = "/Stock/Update",params = "cancel", method = RequestMethod.POST)
    public String cancelCategory(){
        return "redirect:/Stock";
    }

}
