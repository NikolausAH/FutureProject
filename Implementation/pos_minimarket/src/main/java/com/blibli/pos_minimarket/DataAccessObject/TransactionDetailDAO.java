package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.TransactionDetail;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDetailDAO extends ConnectionSettings {
/*
    public List<TransactionDetail> getAllTransactionDetail() {
        List<TransactionDetail> transactionDetailList = new ArrayList<>();

        String sql = "select * from TransactionDetail";
        try {
            //c koneksi global dari extends myConn
            Statement state = c.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    TransactionDetail transactionDetail = new TransactionDetail();

                    transactionDetail.setDetailId(rs.getInt("detailId"));
                    transactionDetail.setQuantity(rs.getInt("quantity"));
                    transactionDetail.setPrice(rs.getDouble("price"));
                    transactionDetail.setDiscount(rs.getDouble("discount"));
                    transactionDetail.setProductId(rs.getInt("productId"));
                    transactionDetail.setTransactionId(rs.getInt("transactionId"));
                    transactionDetail.setDiscountPId(rs.getInt("discountPId"));
                    transactionDetail.setDiscountPxy(rs.getInt("discountPxy"));
                    transactionDetailList.add(transactionDetail);
                }
            }
            rs.close();
            state.close();
        } catch (Exception EX) {
            //System.out.println("Error TransactionDetailDAO getAllTransactionDetail");
            System.out.println(EX.toString());
        }
        return transactionDetailList;
    }
    */
}
