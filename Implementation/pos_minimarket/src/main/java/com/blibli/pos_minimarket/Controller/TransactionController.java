package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Model.Transaction;
import com.blibli.pos_minimarket.Services.ProductService;
import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
        try {
            List<Product> productList = new ArrayList<>();
            model.addAttribute("dateTime", transactionService.setDate());
            model.addAttribute("transactionId",transactionService.setNextTransactionId());
            model.addAttribute("tax",transactionService.setTax());
            model.addAttribute("total",transactionService.setTotal());
            model.addAttribute("productList",productList);
        }catch (Exception EX){
            System.out.println("Error TransactionController initialTransaction");
        }
        return "Transaction";
    }

    @RequestMapping(value = "Transaction/addToCart")
    public String addToCart(@ModelAttribute("productId")Integer productId,Model model,@ModelAttribute("productList")String productList){
//        System.out.println(productId);
        try {
            System.out.println(productList);
           // productList.add(productService.getById(productId));System.out.println(productId);

            model.addAttribute("productList", productService.getById(productId));
        }catch (Exception EX){
            System.out.println(EX.toString()+"INI ERRRRRRROR");
        }
        return "Transaction";
    }
//
//    @PostMapping(value = "/Transaction/addToCart")
//    public ModelAndView addToCart(@ModelAttribute("productId") Integer productId){
//        ModelAndView mav = new ModelAndView();
//        try {
//            Product product = productService.getById(productId);
//            this.Cart(product)
//            //transactionService.addToCart(productList, product);
//        }
//        catch (Exception EX){
//            System.out.println("Error di sini");
//            System.out.println(EX.toString());
//        }
//        mav.setViewName("redirect:/Transaction/Cart");
//
//        return mav;
//
//        System.out.println("Error di sini");
//    }

}
