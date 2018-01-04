package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.TransactionDetailDAO;
import com.blibli.pos_minimarket.Model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionDetailService {
    private TransactionDetailDAO transactionDetailDAO = new TransactionDetailDAO();
    public TransactionDetailService() {
    }


    public List<TransactionDetail> showOne(Integer searchKey) {
        List<TransactionDetail> transactionDetailList = new ArrayList<>();
        try {
            transactionDetailList = transactionDetailDAO.getByIdTransaction(searchKey);
        }catch (Exception EX){
            System.out.println("Error TransactionDetailService showOne");
            System.out.println(EX.toString());
        }
        return transactionDetailList;
    }

}
