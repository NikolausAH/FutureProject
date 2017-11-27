package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ConnectionSettings;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDetailDAO;
import com.blibli.pos_minimarket.Model.TransactionDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionDetailService {
    private ConnectionSettings connectionSettings = new ConnectionSettings();
    private TransactionDetailDAO transactionDetailDAO = new TransactionDetailDAO();
    public TransactionDetailService() {
    }
/*
    public List<TransactionDetail> showAll() {
        connectionSettings.makeConnection();
        List<TransactionDetail> transactionDetailList = transactionDetailDAO.getAllTransactionDetail();
        connectionSettings.closeConnection();
        return transactionDetailList;
    }
    */
}
