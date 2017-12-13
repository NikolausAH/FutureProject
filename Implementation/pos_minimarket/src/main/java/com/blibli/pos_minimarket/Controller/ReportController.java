package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Model.TransactionDetail;
import com.blibli.pos_minimarket.Services.TransactionDetailService;
import com.blibli.pos_minimarket.Services.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {
    private TransactionService transactionService = new TransactionService();
    private TransactionDetailService transactionDetailService = new TransactionDetailService();
    @Autowired
    public ReportController() {
    }

    @RequestMapping(value = "Report-Transaction")
    public String showReportTransaction(Model model,@ModelAttribute("searchKey") String searchKey) {
        model.addAttribute("transactionList",transactionService.showAllTransaction());
        if(!searchKey.equals("")) {
           model.addAttribute("transactionDetailList", transactionDetailService.showOne(Integer.parseInt(searchKey)));

        }return "Report-Transaction";

    }
    @RequestMapping(value = "Report-Statistics")
    public String showReportStatistics(Model model,@ModelAttribute("searchKey") String searchKey) {
        model.addAttribute("transactionList",transactionService.showAllTransaction());
        if(!searchKey.equals("")) {
            model.addAttribute("transactionDetailList", transactionDetailService.showOne(Integer.parseInt(searchKey)));

        }return "Report-Transaction";

    }

//    @RequestMapping(value = "Report-Transaction")
//    public String showReportTransaction(Model model) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<TransactionDetail> transactionDetailList = new ArrayList<>();
//        try{
//        transactionDetailList =  transactionDetailService.showOne(27);
//        String json = objectMapper.writeValueAsString(transactionDetailList);
//            model.addAttribute("transactionList",json);}
//        catch (Exception EX){
//            System.out.println("error show reportTransaction" + EX.toString());
//        }
////        model.addAttribute("transactionList", transactionService.showAllTransaction());
//
//        return "Report-Transaction";
//
//    }

//    @RequestMapping(value = "Report-Transaction/Detail")
//    public ModelAndView showDetailTransaction(@ModelAttribute("searchKey") Integer searchKey){
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("transactionDetailList",transactionDetailService.showOne(searchKey));
//        mav.setViewName("Report-Transaction");
//        return mav;
//    }
}
