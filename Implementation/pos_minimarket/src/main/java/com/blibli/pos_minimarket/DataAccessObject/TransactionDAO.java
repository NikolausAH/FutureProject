package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Model.Transaction;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDAO extends ConnectionSettings {
    private GeneralDAO generalDAO = new GeneralDAO();
    private ProductDAO productDAO = new ProductDAO();

    public TransactionDAO() {
    }

    public void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS public.transaction" +
                "(" +
                "    transaction_id SERIAL PRIMARY KEY NOT NULL," +
                "    date_time VARCHAR(50) NOT NULL," +
                "    tax DOUBLE PRECISION NOT NULL," +
                "    discount DOUBLE PRECISION," +
                "    total DOUBLE PRECISION NOT NULL," +
                "    p_total_id INTEGER," +
                "    employee_id INTEGER NOT NULL," +
                "    CONSTRAINT p_total_id___fk FOREIGN KEY (p_total_id) REFERENCES promo_total_buy (p_total_id)," +
                "    CONSTRAINT employee_id___fk FOREIGN KEY (employee_id) REFERENCES employees (employee_id)" +
                ");";
        String message = "Error TransactionDAO initTable";

        generalDAO.executeSet(sql,message);
    }

        public void add(Transaction transaction) {
        String sql = "INSERT INTO transaction (date_time,tax,discount,total,p_total_id,employee_id) VALUES (?,?,?,?,?,?);";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getDateTime());
            preparedStatement.setDouble(2,transaction.getTax());
            preparedStatement.setDouble(3, transaction.getDiscount());
            preparedStatement.setDouble(4, transaction.getTotal());
            preparedStatement.setInt(5,1);
            preparedStatement.setInt(6,1);
            preparedStatement.executeUpdate();
            System.out.println("Success TransactionDAO Add");
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error TransactionDAO Add");
            System.out.println(EX.toString());
        }
    }

    public Integer getNextId(){
        Integer nextId = 1;
        String sql = "SELECT transaction_transaction_id_seq.last_value FROM transaction_transaction_id_seq;";
        String message = "Error TransactionDAO getNextId";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null)  {
                while (resultSet.next()){
                    nextId+=resultSet.getInt("last_value");
                }
            }
            this.closeConnection();
        }
        catch (Exception EX){
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return nextId;
    }

    public void addToCart(Integer productId, Integer quantity) {
        System.out.println(productId+quantity);
        String sql = "INSERT INTO temp_cart (product_id,quantity) VALUES ('"+productId+"','"+quantity+"');";
        String message = "Error Transaction DAO addToCart";
        generalDAO.executeSet(sql, message);
    }

    public List<Product> getFromCart(){
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM temp_cart";
        String message = "Error Transaction DAO getFromCart";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getInt("product_id"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    productList.add(product);
                }
                resultSet.close();
            }
            this.closeConnection();
        }catch (Exception EX){
            System.out.print(message);
            System.out.print(EX.toString());
        }
        return productList;
    }

//    public List<Transaction> getAll() {
//        List<Transaction> transactionList = new ArrayList<>();
//
//        String sql = "SELECT * FROM transaction";
//        try {
//            this.makeConnection();
//            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            this.closeConnection();
//            if (resultSet != null) {
//                while (resultSet.next()) {
//                    Transaction transaction = new Transaction();
//                    transaction.setTransactionId(resultSet.getInt("transactionId"));
//                    transaction.setDateTime(resultSet.getString("dateTime"));
//                    transaction.setTax(resultSet.getDouble("tax"));
//                    transaction.setDiscount(resultSet.getDouble("discount"));
//                    transaction.setTotal(resultSet.getDouble("total"));
//                    transactionList.add(transaction);
//                }
//                resultSet.close();
//            }
//            preparedStatement.close();
//        } catch (Exception EX) {
//            System.out.println("Error TransactionDAO getAll");
//            System.out.println(EX.toString());
//        }
//        return transactionList;
//    }


}