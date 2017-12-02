package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Product;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class ProductDAO extends ConnectionSettings implements InterfaceDAO<Product,Integer, String> {
    private CategoryDAO categoryDAO = new CategoryDAO();
    private GeneralDAO generalDAO = new GeneralDAO();

    public ProductDAO() {
    }

    @Override
    public void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS public.product" +
                "(" +
                "    product_id SERIAL PRIMARY KEY NOT NULL," +
                "    name VARCHAR(25) NOT NULL," +
                "    price DOUBLE PRECISION NOT NULL," +
                "    quantity INTEGER," +
                "    description VARCHAR(250)," +
                "    status VARCHAR(15) NOT NULL," +
                "    category_id INTEGER NOT NULL," +
                "    CONSTRAINT category_id___fk FOREIGN KEY (category_id) REFERENCES category (category_id)" +
                ");";
        String message = "Error ProductDAO initTable";
        generalDAO.executeSet(sql,message);
    }

    public Integer getNextId(){
        Integer nextId = 1;
        String sql = "SELECT product_product_id_seq.last_value FROM product_product_id_seq;";
        String message = "Error ProductDAO getNextId";
        nextId = generalDAO.getNextId(sql,message);
        return nextId;
    }

    @Override
    public void add(Product product) {
        product.setQuantity(0);
        String sql = "INSERT INTO product (name,price,quantity,description,category_id,status)" +
                "VALUES ('"+product.getName()+"','"+product.getPrice()+"','"+product.getQuantity()+"'"+
                ",'"+product.getDescription()+"','"+product.getCategory().getCategoryId()+"','active');";
        String message = "Error ProductDAO Add";
        generalDAO.executeSet(sql,message);
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name = '"+product.getName()+"', price = '"+product.getPrice()+"',"+
                " description = '"+product.getDescription()+"', category_id = '"+product.getCategory().getCategoryId()+"'"+
                " WHERE product_id = '"+product.getProductId()+"';";
        String message = "Error ProductDAO Update";
        generalDAO.executeSet(sql,message);
    }

    @Override
    public void delete(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = '"+productId+"' ;";
        String message = "Error ProductDAO Delete";
        generalDAO.executeSet(sql,message);
    }

    @Override
    public void softDelete(Integer productId) {
        String sql = "UPDATE product SET status = 'not active' WHERE product_id = '"+productId+"';";
        String message = "Error ProductDAO softDelete";
        generalDAO.executeSet(sql,message);
    }

    public void updateQuantity(Product product, Integer oldQuantity) {
        Integer quantity = product.getQuantity()+oldQuantity;
        String sql = "UPDATE product SET quantity = '"+quantity+"' WHERE product_id = '"+product.getProductId()+"';";
        String message = "Error ProductDAO UpdateQuantity";
        generalDAO.executeSet(sql,message);
    }

    @Override
    public Product getById(Integer productId) {
        Product product = new Product();
        String sql = "SELECT * FROM product WHERE product_id ='"+productId+"';";
        String message = "Error ProductDAO getById";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    product.setProductId(resultSet.getInt("product_Id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    product.setDescription(resultSet.getString("description"));
                    product.setCategory(categoryDAO.getById(resultSet.getInt("category_Id")));
                    product.setStatus(resultSet.getString("status"));
                }
                resultSet.close();
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY product_id";
        String message = "Error ProductDAO getAllProduct";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getInt("product_Id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    product.setDescription(resultSet.getString("description"));
                    product.setCategory(categoryDAO.getById(resultSet.getInt("category_Id")));
                    product.setStatus(resultSet.getString("status"));
                    productList.add(product);
                }
                resultSet.close();
            }
            this.closeConnection();
        }catch (Exception EX){
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return productList;
    }

    @Override
    public List<Product> search(String searchKey) {
        List<Product> productList = new ArrayList<>();
        Scanner scanner = new Scanner(searchKey);

        String sqlString = "SELECT * FROM product WHERE name LIKE '%"+searchKey+"%' ORDER BY product_id;";
        String sqlInteger = "SELECT * FROM product WHERE product_id = '"+searchKey+"' OR name LIKE '%"+searchKey+"%' ORDER BY product_id;";
        String message = "Error ProductDAO search";
        try {
            this.makeConnection();
            ResultSet resultSet;
            if (scanner.hasNextInt()){
                PreparedStatement preparedStatement = this.connection.prepareStatement(sqlInteger);
                resultSet = preparedStatement.executeQuery();
            }else {
                PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
                resultSet = preparedStatement.executeQuery();
            }
            if (resultSet != null) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getInt("product_Id"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setName(resultSet.getString("name"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    product.setDescription(resultSet.getString("description"));
                    product.setCategory(categoryDAO.getById(resultSet.getInt("category_Id")));
                    System.out.println(product.getCategory().getName());
                    product.setStatus(resultSet.getString("status"));
                    productList.add(product);
                }
                resultSet.close();
            }
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println(message);
            System.out.println(EX.toString());
        }
        return productList;
    }
}
