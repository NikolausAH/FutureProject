package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.MyConnection;
import com.blibli.pos_minimarket.DataAccessObject.TransactionDetailDAO;
import com.blibli.pos_minimarket.Model.TransactionDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionDetailService {
    private MyConnection myConnection = new MyConnection();
    private TransactionDetailDAO transactionDetailDAO = new TransactionDetailDAO();

    public List<TransactionDetail> showAll() {
        myConnection.makeConnection();
        List<TransactionDetail> transactionDetailList = transactionDetailDAO.getAllTransactionDetail();
        myConnection.closeConnection();
        return transactionDetailList;
    }
}
