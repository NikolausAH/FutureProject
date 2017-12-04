package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.TransactionDetail;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TransactionDetailDAO extends ConnectionSettings {
    private GeneralDAO generalDAO = new GeneralDAO();
    private ProductDAO productDAO = new ProductDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    public TransactionDetailDAO() {
    }

    public void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS public.transactionDetail" +
                "(" +
                "    detailId SERIAL PRIMARY KEY NOT NULL," +
                "    quantity INT," +
                "    price FLOAT," +
                "    discount FLOAT," +
                "    productId INT," +
                "    discountPid INT," +
                "    discountPxy INT," +
                "    column_8 INT," +
                "    CONSTRAINT transactionDetail_product_productid_fk FOREIGN KEY (productId) REFERENCES product (product_id)," +
                "    CONSTRAINT transactionDetail_discountProduct_discountPid_fk FOREIGN KEY (discountPid) REFERENCES discountProduct (discountPid),"+
                "    CONSTRAINT transactionDetail_discountProductXY_productPxy_fk FOREIGN KEY (discountPxy) REFERENCES discountProductXY (discountpxyid)"+
                ");";
        String message = "Error TransactionDetailDAO initTable";
        generalDAO.executeSet(sql,message);
    }

    public void add(TransactionDetail transactionDetail){
        System.out.println(transactionDetail.getDiscount());
        System.out.println(transactionDetail.getPrice());
        System.out.println(transactionDetail.getQuantity());
        System.out.println(transactionDetail.getProduct().getProductId());
        System.out.println(transactionDetail.getTransaction().getTransactionId());
        String sql = "INSERT INTO transaction_detail (quantity, price, discount, product_id, transaction_id) "+
                "VALUES("+
                "   '"+transactionDetail.getQuantity()+"',"+
                "   '"+transactionDetail.getPrice()+"',"+
                "   '"+transactionDetail.getDiscount()+"',"+
                "   '"+transactionDetail.getProduct().getProductId()+"',"+
                "   '"+transactionDetail.getTransaction().getTransactionId()+"'"+
                ");";
        String message = "Error TransactionDetailDAO add";
        generalDAO.executeSet(sql,message);
    }

    public List<TransactionDetail> getByIdTransaction(Integer searchKey) {
        List<TransactionDetail> transactionDetailList = new ArrayList<>();
        String sql = "SELECT * FROM TransactionDetail WHERE transaction_id = '"+searchKey+"';";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    TransactionDetail transactionDetail = new TransactionDetail();
                    transactionDetail.setDetail_Id(resultSet.getInt("detail_Id"));
                    transactionDetail.setQuantity(resultSet.getInt("quantity"));
                    transactionDetail.setPrice(resultSet.getDouble("price"));
                    transactionDetail.setDiscount(resultSet.getDouble("discount"));
                    transactionDetail.setProduct(productDAO.getById(resultSet.getInt("product_Id")));
//                    transactionDetail.setTransaction(transactionDAOrs.getInt("transactionId"));
//                    transactionDetail.setDiscountPId(rs.getInt("discountPId"));
//                    transactionDetail.setDiscountPxy(rs.getInt("discountPxy"));
                    transactionDetailList.add(transactionDetail);
                }
            }resultSet.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error TransactionDetailDAO getByIdTransaction");
            System.out.println(EX.toString());
        }
        return transactionDetailList;
    }
}
