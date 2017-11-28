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
            productList = transactionService.getFromCart();
            model.addAttribute("productList", productList);
            model.addAttribute("dateTime", transactionService.initDate());
            model.addAttribute("transactionId", transactionService.initTransactionId());
            model.addAttribute("tax", transactionService.initTax());
            model.addAttribute("total", transactionService.initTotal());
        } catch (Exception EX) {
            System.out.println("Error TransactionController initialTransaction");
        }
        return "Transaction";
    }

}
