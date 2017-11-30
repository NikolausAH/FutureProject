package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class TransactionController {
    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping("/Transaction")
    public String initialTransaction(Model model) {
        transactionService.initTable();
        List<Product> productList;
        try {
            Double tax = transactionService.updateTax(transactionService.updateTotal());
            Double finalTotal = transactionService.updateTotal() + tax;
            productList = transactionService.getFromCart();
            model.addAttribute("productList", productList);
            model.addAttribute("dateTime", transactionService.initDate());
            model.addAttribute("transactionId", transactionService.initTransactionId());
            model.addAttribute("tax", tax);
            model.addAttribute("total",finalTotal);
        } catch (Exception EX) {
            System.out.println("Error TransactionController initialTransaction");
        }
        return "Transaction";
    }

    @PostMapping(value = "Transaction/addToCart")
    public ModelAndView addCategory(@ModelAttribute("productId") Integer productId, @ModelAttribute("quantity") Integer quantity){
        ModelAndView mav = new ModelAndView();
        transactionService.addToCart(productId,quantity);
        mav.setViewName("redirect:/Transaction");
        return mav;
    }

    @PostMapping(value = "Transaction/Payment")
    public ModelAndView payment(@ModelAttribute("dateTime") String dateTime){
        ModelAndView mav = new ModelAndView();
        System.out.println("Error TransactionController initialTransaction");
        System.out.println(dateTime);
        transactionService.addTransaction(dateTime);
        mav.setViewName("redirect:/Transaction");
        return mav;
    }
}
