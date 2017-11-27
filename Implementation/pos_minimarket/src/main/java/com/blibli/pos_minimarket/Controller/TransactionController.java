package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
            productList=transactionService.getFromCart();
            model.addAttribute("productList", productList);
            model.addAttribute("dateTime", transactionService.initDate());
            model.addAttribute("transactionId",transactionService.initTransactionId());
            model.addAttribute("tax",transactionService.initTax());
            model.addAttribute("total",transactionService.initTotal());
        }catch (Exception EX){
            System.out.println("Error TransactionController initialTransaction");
        }
        return "Transaction";
    }
/*
    @PostMapping(value = "/Transaction/addToCart")
    public ModelAndView addToCart(@ModelAttribute("productId") Integer productId, @ModelAttribute("quantity") Integer quantity){
        ModelAndView mav = new ModelAndView();
        transactionService.addToCart(productId,quantity);
        mav.setViewName("redirect:/Transaction");
        return mav;
    }

//    @RequestMapping(value = "/Transaction/Add")
//    public ModelAndView addTransaction(){
////        ModelAndView mav = new ModelAndView();
////        transactionService.addDetailTransaction();
//        mav.setViewName("redirect:/Product");
//        return mav;
//    }
//
//    @RequestMapping("/Transaction/getProduct")
//    public String addToCart(Model model, @ModelAttribute("productId") Integer productId, @ModelAttribute("quantity") Integer quantity) {
//        Product product;
//        List<Product> productList = new ArrayList<>();
//        try {
//            product=productService.getById(productId);
//            product.setQuantity(quantity);
//            System.out.println(product.getProductId());
//            System.out.println(product.getQuantity());
//            productList.add(product);
//            model.addAttribute("productList", productList);
//        }catch (Exception EX){
//            System.out.println("Error TransactionController Transaction/getProduct");
//            System.out.println(EX.toString());
//        }
//        return "redirect:/Transaction";
//    }

*/

}
