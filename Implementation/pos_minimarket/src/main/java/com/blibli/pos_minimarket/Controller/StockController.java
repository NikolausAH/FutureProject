package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Services.ProductService;
import com.blibli.pos_minimarket.Services.StockService;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String showAllStock(HttpServletRequest request, Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        if (employee == null ||employee.getRole().getName().equals("Kasir")) {
            return "Login";
        }
        model.addAttribute("product", productService.showAll());
        return "Stock";
    }

    @PostMapping(value = "/Stock/Update")
    public ModelAndView updateStock(@ModelAttribute("product") Product product) {
        ModelAndView mav = new ModelAndView();
        System.out.println(product.getQuantity());
        stockService.updateQuantity(product);
        mav.setViewName("redirect:/Stock");
        return mav;
    }
}
