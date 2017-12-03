package com.blibli.pos_minimarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ReportController {

    @Autowired
    public ReportController() {
    }

    @RequestMapping(value = "Report-Transaction")
    public String showReportTransaction() {
        return "Report-Transaction";
    }
}
