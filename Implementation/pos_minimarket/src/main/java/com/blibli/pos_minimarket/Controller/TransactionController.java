package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.TransactionDetail;
import com.blibli.pos_minimarket.Services.TransactionDetailService;
import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionDetailService transactionDetailService =  new TransactionDetailService();
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping("/Transaction")
    public String initialTransaction(Model model) {
        transactionService.initTable();
        List<TransactionDetail> transactionDetailList;
        try {
            transactionDetailList = transactionService.getAllFromCart();
            Double total = transactionService.getTotal(transactionDetailList);
            Double discountTotal = transactionService.getDiscountTotal(total,transactionService.initDate());
            Double tax = transactionService.getTax(total-discountTotal);
            Double finalTotal = total-discountTotal + tax;
            model.addAttribute("transactionDetailList", transactionDetailList);
            model.addAttribute("date_Time", transactionService.initDate());
            model.addAttribute("transaction_nextId", transactionService.getNextId());
            model.addAttribute("tax", tax);
            model.addAttribute("total",finalTotal);
            model.addAttribute("discountTotal",discountTotal);
            //System.out.println(discountTotal+"sjahdjgahdgjagdadasdadasd");
        } catch (Exception EX) {
            System.out.println("Error TransactionController initialTransaction");
        }
        return "Transaction";
    }

    @PostMapping(value = "Transaction/addToCart")
    public ModelAndView addToCart(@ModelAttribute("productId") Integer productId, @ModelAttribute("quantity") Integer quantity){
        ModelAndView mav = new ModelAndView();
        transactionService.addToCart(productId,quantity);
        mav.setViewName("redirect:/Transaction");
        return mav;
    }

    @PostMapping(value = "Transaction/Payment")
    public ModelAndView payment(@ModelAttribute("date_Time") String date_Time, @ModelAttribute("total") Double total, @ModelAttribute("tax") Double tax){
        ModelAndView mav = new ModelAndView();
        //transactionService.addTransaction(date_Time,total,tax);
        mav.setViewName("redirect:/Transaction");
        return mav;
    }

    @RequestMapping(value = "/Receipt/{transactionId}", method = RequestMethod.GET)
    public String showReceipt(@PathVariable Integer transactionId, Model model){
        model.addAttribute("transactionDetailList", transactionDetailService.showOne(transactionId));
        return "Receipt";
    }
}
