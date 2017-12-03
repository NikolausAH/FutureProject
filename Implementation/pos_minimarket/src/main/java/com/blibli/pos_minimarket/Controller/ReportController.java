package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ReportController {
    private TransactionService transactionService = new TransactionService();
    @Autowired
    public ReportController() {
    }

    @RequestMapping(value = "Report-Transaction")
    public String showReportTransaction(Model model) {
        model.addAttribute("transactionList", transactionService.showAllTransaction());
        return "Report-Transaction";
    }
}
