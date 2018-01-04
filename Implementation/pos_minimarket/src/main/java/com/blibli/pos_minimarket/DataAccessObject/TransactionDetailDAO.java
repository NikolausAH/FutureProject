package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.PromoProduct;
import com.blibli.pos_minimarket.Model.PromoXY;
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
    private PromoProductDAO promoProductDAO = new PromoProductDAO();
    private PromoTotalDAO promoTotalDAO = new PromoTotalDAO();
    private PromoXYDAO promoXYDAO = new PromoXYDAO();
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
        String sql1 = "INSERT INTO transaction_detail (quantity, price, discount, product_id, transaction_id) "+
                "VALUES("+
                "   '"+transactionDetail.getQuantity()+"',"+
                "   '"+transactionDetail.getPrice()+"',"+
                "   '"+transactionDetail.getDiscount()+"',"+
                "   '"+transactionDetail.getProduct().getProductId()+"',"+
                "   '"+transactionDetail.getTransaction().getTransactionId()+"'"+
                ");";
        String sql2 = "INSERT INTO transaction_detail (quantity, price, discount, product_id, transaction_id,p_discount_id) "+
                "VALUES("+
                "   '"+transactionDetail.getQuantity()+"',"+
                "   '"+transactionDetail.getPrice()+"',"+
                "   '"+transactionDetail.getDiscount()+"',"+
                "   '"+transactionDetail.getProduct().getProductId()+"',"+
                "   '"+transactionDetail.getTransaction().getTransactionId()+"',"+
                "   '"+transactionDetail.getPromoProduct().getId()+"'"+
                ");";
        String sql3 = "INSERT INTO transaction_detail (quantity, price, discount, product_id, transaction_id,p_bxgy_id) "+
                "VALUES("+
                "   '"+transactionDetail.getQuantity()+"',"+
                "   '"+transactionDetail.getPrice()+"',"+
                "   '"+transactionDetail.getDiscount()+"',"+
                "   '"+transactionDetail.getProduct().getProductId()+"',"+
                "   '"+transactionDetail.getTransaction().getTransactionId()+"',"+
                "   '"+transactionDetail.getPromoXY().getId()+"'"+
                ");";
        String sql4 = "INSERT INTO transaction_detail (quantity, price, discount, product_id, transaction_id,p_discount_id,p_bxgy_id) "+
                "VALUES("+
                "   '"+transactionDetail.getQuantity()+"',"+
                "   '"+transactionDetail.getPrice()+"',"+
                "   '"+transactionDetail.getDiscount()+"',"+
                "   '"+transactionDetail.getProduct().getProductId()+"',"+
                "   '"+transactionDetail.getTransaction().getTransactionId()+"',"+
                "   '"+transactionDetail.getPromoProduct().getId()+"',"+
                "   '"+transactionDetail.getPromoXY().getId()+"'"+
                ");";
        String message = "Error TransactionDetailDAO add";
        if(transactionDetail.getPromoXY().getId()!=0 && transactionDetail.getPromoProduct().getProductId()!=0){
            generalDAO.executeSet(sql4,message);
        }else if(transactionDetail.getPromoXY().getId()!=0){
            generalDAO.executeSet(sql3,message);
        }else if(transactionDetail.getPromoProduct().getId()!=0){
            generalDAO.executeSet(sql2,message);
        }else
            generalDAO.executeSet(sql1,message);
    }

    public List<TransactionDetail> getByIdTransaction(Integer searchKey) {
        List<TransactionDetail> transactionDetailList = new ArrayList<>();
        String sql = "SELECT * FROM transaction_detail WHERE transaction_id = '"+searchKey+"';";
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

                    PromoProduct promoProduct = new PromoProduct();
                    promoProduct.setProductId(0);
                    PromoXY promoXY = new PromoXY();
                    promoXY.setProductYId(0);
                    if(resultSet.getInt("p_discount_id") !=0) {
                        transactionDetail.setPromoProduct(promoProductDAO.getById(resultSet.getInt("p_discount_id")));
                    }else transactionDetail.setPromoProduct(promoProduct);
                    if(resultSet.getInt("p_bxgy_id") != 0) {
                        transactionDetail.setPromoXY(promoXYDAO.getById(resultSet.getInt("p_bxgy_id")));
                    }else transactionDetail.setPromoXY(promoXY);
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

    public TransactionDetail getOne(Integer searchKey) {
        TransactionDetail transactionDetail = new TransactionDetail();
        String sql = "SELECT * FROM TransactionDetail WHERE  detail_id= '"+searchKey+"';";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    transactionDetail.setDetail_Id(resultSet.getInt("detail_Id"));
                    transactionDetail.setQuantity(resultSet.getInt("quantity"));
                    transactionDetail.setPrice(resultSet.getDouble("price"));
                    transactionDetail.setDiscount(resultSet.getDouble("discount"));
                    transactionDetail.setProduct(productDAO.getById(resultSet.getInt("product_Id")));
//                    transactionDetail.setTransaction(transactionDAO.s.getInt("transactionId"));
                    transactionDetail.setPromoProduct(promoProductDAO.getById(resultSet.getInt("discountPId")));
                    transactionDetail.setPromoXY(promoXYDAO.getById(resultSet.getInt("discountPxy")));
                }
            }resultSet.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error TransactionDetailDAO getByIdTransaction");
            System.out.println(EX.toString());
        }
        return transactionDetail;
    }
}
