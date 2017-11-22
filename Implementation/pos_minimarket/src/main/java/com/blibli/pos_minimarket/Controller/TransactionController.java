package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Transaction;
import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping("/Transaction")
    public String initialTransaction(Model model) {
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

    @PostMapping(value = "/Transaction/Add")
    public ModelAndView addCategory(@ModelAttribute("transaction") Transaction transaction){
        ModelAndView mav = new ModelAndView();
        transactionService.add(transaction);
        mav.setViewName("redirect:/Transaction");
        return mav;
    }

}
