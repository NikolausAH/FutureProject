package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Services.TransactionDetailService;
import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportController {
    private TransactionService transactionService = new TransactionService();
    private TransactionDetailService transactionDetailService = new TransactionDetailService();
    @Autowired
    public ReportController() {
    }

    @RequestMapping(value = "Report-Transaction")
    public String showReportTransaction(Model model) {
        model.addAttribute("transactionList", transactionService.showAllTransaction());
        return "Report-Transaction";
    }

    @RequestMapping(value = "Report-Transaction/Detail")
    public String showDetailTransaction(@ModelAttribute("searchKey") Integer searchKey, Model model){
        model.addAttribute("transactionDetailList", transactionDetailService.showOne(searchKey));
        return "Report-Transaction";
    }
}
