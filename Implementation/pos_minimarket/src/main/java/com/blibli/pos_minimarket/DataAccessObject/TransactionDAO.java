package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Product;
import com.blibli.pos_minimarket.Model.PromoTotal;
import com.blibli.pos_minimarket.Model.Transaction;
import com.sun.corba.se.impl.oa.toa.TOA;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDAO extends ConnectionSettings {
    private GeneralDAO generalDAO = new GeneralDAO();
    private ProductDAO productDAO = new ProductDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

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
        nextId = generalDAO.getNextId(sql,message);
        return nextId;
    }

    public void addToCart(Integer productId, Integer quantity) {
        String sql = "INSERT INTO temp_cart (product_id,quantity) VALUES ('"+productId+"','"+quantity+"');";
        String message = "Error TransactionDAO addToCart";
        generalDAO.executeSet(sql, message);
    }
    public void removeFromCart() {
        String sql = "DELETE FROM temp_cart;";
        String message = "Error TransactionDAO removeFromCart";
        generalDAO.executeSet(sql, message);
    }

    public List<Product> getFromCart(){
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM temp_cart";
        String message = "Error TransactionDAO getFromCart";
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

    public Transaction getById(Integer searchKey) {
        Transaction transaction = new Transaction();
        String sql = "SELECT * FROM transaction WHERE transaction_id = '"+searchKey+"' ORDER BY transaction_id DESC ";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    transaction.setTransactionId(resultSet.getInt("transaction_Id"));
                    transaction.setDateTime(resultSet.getString("date_Time"));
                    transaction.setTax(resultSet.getDouble("tax"));
                    transaction.setDiscount(resultSet.getDouble("discount"));
                    transaction.setTotal(resultSet.getDouble("total"));
                    transaction.setEmployee(employeeDAO.getById(resultSet.getInt("employee_id")));
                }
                resultSet.close();
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error TransactionDAO getById");
            System.out.println(EX.toString());
        }
        return transaction;
    }

    public List<Transaction> getAllTransaction() {
        List<Transaction> transactionList = new ArrayList<>();

        String sql = "SELECT * FROM transaction ORDER BY transaction_id DESC ";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(resultSet.getInt("transaction_Id"));
                    transaction.setDateTime(resultSet.getString("date_Time"));
                    transaction.setTax(resultSet.getDouble("tax"));
                    transaction.setDiscount(resultSet.getDouble("discount"));
                    transaction.setTotal(resultSet.getDouble("total"));
                    transaction.setEmployee(employeeDAO.getById(resultSet.getInt("employee_id")));
                    transactionList.add(transaction);
                }
                resultSet.close();
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error TransactionDAO getAll");
            System.out.println(EX.toString());
        }
        return transactionList;
    }

    public PromoTotal getDiscountTotal(Double total, LocalDateTime dateTime){
        PromoTotal promoTotal = new PromoTotal();
        String sql = "SELECT * FROM promo_total_buy WHERE '"+dateTime+"'>= start_date AND '"+dateTime+"'<=end_date AND '"+total+"' >= buy_min AND status = 'active'";
        String message = "Error TransactionDAO getDiscountTotal";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
//                    promoTotal(resultSet.getInt("p_total_id"));
                    promoTotal.setDiscountPercent(resultSet.getDouble("discount_percent"));
                    promoTotal.setBuyMin(resultSet.getDouble("buy_min"));
                    promoTotal.setStartDate(resultSet.getTimestamp("start_date"));
                    promoTotal.setEndDate(resultSet.getTimestamp("end_date"));
                    promoTotal.setStatus(resultSet.getString("status"));
                }
                resultSet.close();
            }
            this.closeConnection();
        }catch (Exception EX){
            System.out.print(message);
            System.out.print(EX.toString());
        }
        return promoTotal;
    }

}