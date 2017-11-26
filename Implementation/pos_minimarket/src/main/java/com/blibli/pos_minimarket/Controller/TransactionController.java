package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Services.ProductService;
import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private ProductService productService = new ProductService();
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping("/Transaction")
    public String initialTransaction(Model model) {
        transactionService.initTable();
        try {
            model.addAttribute("dateTime", transactionService.setDate());
            model.addAttribute("transactionId",transactionService.setNextTransactionId());
            model.addAttribute("tax",transactionService.setTax());
            model.addAttribute("total",transactionService.setTotal());
        }catch (Exception EX){
            System.out.println("Error TransactionController initialTransaction");
        }
        return "Transaction";
    }

    @RequestMapping("/Transaction/Cart")
    public String Transaction(Model model, @ModelAttribute("transactionId") Integer transactionId) {
        List<Product> productList;
        try {
            productList=transactionService.getFromCart();
            model.addAttribute("cart", productList);
        }catch (Exception EX){
            System.out.println("Error TransactionController Transaction/Cart");
            System.out.println(EX.toString());
        }
        return "Transaction";
    }

    @PostMapping(value = "/Transaction/addToCart")
    public ModelAndView addToCart(Model model,@ModelAttribute("productId") Integer productId, @ModelAttribute("quantity") Integer quantity, @ModelAttribute("transactionId") Integer transactionId){
        ModelAndView mav = new ModelAndView();
        model.addAttribute("transactionId",transactionId);
        transactionService.addToCart(productId,quantity);
      //  model.addAttribute("transactionId",transactionId);
        mav.setViewName("redirect:/Transaction/Cart");
        return mav;
    }

}
