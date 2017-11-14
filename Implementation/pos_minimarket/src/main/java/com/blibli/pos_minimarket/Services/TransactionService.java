package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.MyConnection;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDAO;
import com.blibli.pos_minimarket.Model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private MyConnection myConnection = new MyConnection();
    private TransactionDAO transactionDAO = new TransactionDAO();

    public List<Transaction> showAllTransaction() {
        myConnection.makeConnection();
        List<Transaction> transactionList = transactionDAO.getAllTransaction();
        myConnection.closeConnection();
        return transactionList;
    }
}
