package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ConnectionSettings;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDAO;
import com.blibli.pos_minimarket.Model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private ConnectionSettings connectionSettings = new ConnectionSettings();
    private TransactionDAO transactionDAO = new TransactionDAO();
/*
    public List<Transaction> showAllTransaction() {
        connectionSettings.makeConnection();
        List<Transaction> transactionList = transactionDAO.getAllTransaction();
        connectionSettings.closeConnection();
        return transactionList;
    }
    */
}
