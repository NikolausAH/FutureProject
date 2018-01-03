package com.blibli.pos_minimarket.Services;


import com.blibli.pos_minimarket.DataAccessObject.ProductDAO;
import com.blibli.pos_minimarket.DataAccessObject.ReportDAO;
import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Model.ReportStatistic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    private ProductDAO productDAO = new ProductDAO();
    private ReportDAO reportDAO = new ReportDAO();
    public ReportService(){}

    public List<ReportStatistic> getAll(){
        Integer counter = 0;
        List<ReportStatistic> reportStatisticList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        Integer transactionAmount;

        try{
            productList=productDAO.getAll();
            for (Product ProductList : productList) {
                counter++;
                ReportStatistic reportStatistic = new ReportStatistic();
                transactionAmount = reportDAO.getTransactionAmount(ProductList.getProductId());
                if(transactionAmount!=0){
                    reportStatistic.setTransactionAmount(transactionAmount);
                    reportStatistic.setNumber(counter);
                    reportStatistic.setProduct(ProductList);
                    reportStatistic.setQuantityAmount(reportDAO.getQuantityAmount(ProductList.getProductId()));
                    reportStatisticList.add(reportStatistic);
                }
            }
        }catch (Exception EX){
            System.out.println("Error ReportService getAll");
        }
        return reportStatisticList;
    }
}
