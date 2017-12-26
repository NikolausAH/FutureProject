package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping("/Transaction")
    public String initialTransaction(HttpServletRequest request, Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null) {
            return "Login";
        } else if (employee.getRole().getName().equals("Kasir")) {
            transactionService.initTable();
            List<Product> productList;
            try {
                Double tax = transactionService.updateTax(transactionService.updateTotal());
                Double finalTotal = transactionService.updateTotal() + tax;
                productList = transactionService.getFromCart();
                model.addAttribute("productList", productList);
                model.addAttribute("date_Time", transactionService.initDate());
                model.addAttribute("transaction_nextId", transactionService.getNextId());
                model.addAttribute("tax", tax);
                model.addAttribute("total", finalTotal);
            } catch (Exception EX) {
                System.out.println("Error TransactionController initialTransaction");
            }
            return "Cashier";
        } else {
            transactionService.initTable();
            List<Product> productList;
            try {
                Double tax = transactionService.updateTax(transactionService.updateTotal());
                Double finalTotal = transactionService.updateTotal() + tax;
                productList = transactionService.getFromCart();
                model.addAttribute("productList", productList);
                model.addAttribute("date_Time", transactionService.initDate());
                model.addAttribute("transaction_nextId", transactionService.getNextId());
                model.addAttribute("tax", tax);
                model.addAttribute("total", finalTotal);
            } catch (Exception EX) {
                System.out.println("Error TransactionController initialTransaction");
            }
            return "Transaction";
        }
    }


    @PostMapping(value = "Transaction/addToCart")
    public ModelAndView addToCart(@ModelAttribute("productId") Integer productId, @ModelAttribute("quantity") Integer quantity) {
        ModelAndView mav = new ModelAndView();
        transactionService.addToCart(productId, quantity);
        mav.setViewName("redirect:/Transaction");
        return mav;
    }

    @PostMapping(value = "Transaction/Payment")
    public ModelAndView payment(@ModelAttribute("date_Time") String date_Time) {
        ModelAndView mav = new ModelAndView();
        transactionService.addTransaction(date_Time);
        mav.setViewName("redirect:/Transaction");
        return mav;
    }
}
