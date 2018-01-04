package com.blibli.pos_minimarket.Controller;

import com.blibli.pos_minimarket.Services.ReportService;
import com.blibli.pos_minimarket.Services.TransactionDetailService;
import com.blibli.pos_minimarket.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController {
    private TransactionService transactionService = new TransactionService();
    private TransactionDetailService transactionDetailService = new TransactionDetailService();
    private ReportService reportService = new ReportService();
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

    @RequestMapping(value = "/report-transaction/detail/{transactionId}", method = RequestMethod.GET)
    public String detailCategory(@PathVariable Integer transactionId, Model model){
        model.addAttribute("transactionDetailList", transactionDetailService.showOne(transactionId));
        return "Report-TransactionDetail";
    }

    @RequestMapping(value = "Report-Statistics")
    public String showReportStatistics(Model model) {
        model.addAttribute("reports",reportService.getAll());
        return "Report-Statistics";
    }

    @RequestMapping(value = "/report-transaction/detail",params = "cancel", method = RequestMethod.POST)
    public String cancelReport(){
        return "redirect:/Report-Transaction";
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
