package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.Employee;
import com.blibli.pos_minimarket.Model.TransactionDetail;
import com.blibli.pos_minimarket.Services.TransactionDetailService;
import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionDetailService transactionDetailService =  new TransactionDetailService();

    @RequestMapping("/Transaction")
    public String initialTransaction(HttpServletRequest request,Model model) {
        Employee employee = (Employee) request.getSession().getAttribute("pegawai");
        model.addAttribute("pegawai", employee);
        if (employee == null) {
            return "Login";
        }
        else if(employee.getRole().getName().equals("Kasir")){
        transactionService.initTable();
        List<TransactionDetail> transactionDetailList;
        try {
            transactionDetailList = transactionService.getAllFromCart();
            Double total = transactionService.getTotal(transactionDetailList);
            Double discountTotal = transactionService.getDiscountTotal(total,transactionService.initDate());
            Double tax = transactionService.getTax(total-discountTotal);
            model.addAttribute("transactionDetailList", transactionDetailList);
            model.addAttribute("date_Time", transactionService.initDate());
            model.addAttribute("transaction_nextId", transactionService.getNextId());
            model.addAttribute("tax", tax);
            model.addAttribute("total",total);
//            model.addAttribute("discountTotal",discountTotal);
        } catch (Exception EX) {
            System.out.println("Error TransactionController initialTransaction");
        }
        return "Cashier";}
        else{
            transactionService.initTable();
            List<TransactionDetail> transactionDetailList;
            try {
                transactionDetailList = transactionService.getAllFromCart();
                Double total = transactionService.getTotal(transactionDetailList);
                Double discountTotal = transactionService.getDiscountTotal(total,transactionService.initDate());
                Double tax = transactionService.getTax(total-discountTotal);
                model.addAttribute("transactionDetailList", transactionDetailList);
                model.addAttribute("date_Time", transactionService.initDate());
                model.addAttribute("transaction_nextId", transactionService.getNextId());
                model.addAttribute("tax", tax);
                model.addAttribute("total",total);
//            model.addAttribute("discountTotal",discountTotal);
            } catch (Exception EX) {
                System.out.println("Error TransactionController initialTransaction");
            }
            return "Transaction";}
    }

    @PostMapping(value = "Transaction/addToCart")
    public ModelAndView addToCart(@ModelAttribute("searchKey") String searchKey, @ModelAttribute("quantity") Integer quantity){
        ModelAndView mav = new ModelAndView();
        transactionService.addToCart(searchKey,quantity);
        mav.setViewName("redirect:/Transaction");
        return mav;
    }

    @RequestMapping(value = "Transaction/Payment", method = RequestMethod.POST)
    public String payment(@ModelAttribute("date_Time") String date_Time, @ModelAttribute("total") Double total, @ModelAttribute("tax") Double tax, @ModelAttribute("payNominal") Double payNominal, Model model){
        transactionService.addTransaction(date_Time,total,tax);
        model.addAttribute("payBack",payNominal - total);
        model.addAttribute("transactionDetailList", transactionDetailService.showOne(transactionService.getNextId()-1));
        model.addAttribute("transaction", transactionService.getById(transactionService.getNextId()-1));
        return "Receipt";
    }

    @RequestMapping(value = "Transaction/addToCart",params = "cancel", method = RequestMethod.POST)
    public String cancelTransaction(){
        transactionService.removeAllFromCart();
        return "redirect:/Transaction";
    }

//    @RequestMapping(value = "Transaction/addToCart", params = "batal", method = RequestMethod.POST)
//    public ModelAndView updateProduct(){
//        ModelAndView mav = new ModelAndView();
//        transactionService.removeAllFromCart();
//        mav.setViewName("redirect: Transaction");
//        return mav;
//    }

    @RequestMapping(value = "/Receipt/{transactionId}", method = RequestMethod.GET)
    public String showReceipt(@PathVariable Integer transactionId, Model model){
        model.addAttribute("transactionDetailList", transactionDetailService.showOne(transactionId));
        model.addAttribute("transaction", transactionService.getById(transactionId));
        return "Receipt";
    }

    @RequestMapping(value = "/Receipt", method = RequestMethod.GET)
    public String Receipt(){
        return "redirect:/Transaction";
    }
}
