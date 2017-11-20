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

//    @RequestMapping("/Transaction")
//    public String showAllTransaction(Model model) {
//        model.addAttribute("transaction", transactionService.showAllTransaction());
//        return "Transaction";
//    }
}
