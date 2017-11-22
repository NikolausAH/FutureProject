package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.TransactionDAO;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private TransactionDAO transactionDAO = new TransactionDAO();
/*
    public List<Transaction> showAllTransaction() {
        connectionSettings.makeConnection();
        List<Transaction> transactionList = transactionDAO.getAllTransaction();
        connectionSettings.closeConnection();
        return transactionList;
    }
    */

    public LocalDateTime setDate(){
        LocalDateTime localDateTime = null;
        try {
            localDateTime=LocalDateTime.now();
        }catch (Exception EX){
            System.out.println("Error TransactionService setDate");
        }
        return localDateTime;
    }

    public Integer setNextTransactionId(){
        Integer nextId = 1;
        try{
            nextId = transactionDAO.getNextId();
        }catch (Exception EX)
        {
            System.out.println("Error TransactionService setNextTransactionId");
        }
        return nextId;
    }

    public Double setTax(){
        return 0.0;
    }

    public Double setTotal(){
        return 0.0;
    }
}
