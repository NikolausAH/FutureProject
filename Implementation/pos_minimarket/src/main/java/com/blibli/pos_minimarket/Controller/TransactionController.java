package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
