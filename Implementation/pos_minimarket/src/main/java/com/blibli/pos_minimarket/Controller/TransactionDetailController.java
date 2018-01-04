package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Services.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TransactionDetailController {

    @Autowired
    private TransactionDetailService transactionDetailService;

/*
    @RequestMapping("/Transaction")
    public String showAllTransactionDetail(Model model) {
        model.addAttribute("transactionDetail", transactionDetailService.showAll());
        return "Transaction";
    }
    */
}
