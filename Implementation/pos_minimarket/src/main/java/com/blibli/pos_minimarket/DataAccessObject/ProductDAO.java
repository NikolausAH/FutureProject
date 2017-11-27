package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Product;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO extends ConnectionSettings implements InterfaceDAO<Product,Integer, String> {
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS public.product" +
                "(" +
                "    product_Id SERIAL PRIMARY KEY NOT NULL," +
                "    name VARCHAR(50) NOT NULL ," +
                "    price FLOAT NOT NULL ," +
                "    quantity INTEGER NOT NULL ," +
                "    description VARCHAR(250)," +
                "    categoryId INT NOT NULL," +
                "    status VARCHAR(15) NOT NULL," +
                "    CONSTRAINT category_id FOREIGN KEY (category_Id)" +
                "    REFERENCES public.category(category_id)"+
                ");";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        }catch (Exception EX)
        {
            System.out.println("Error ProductDAO initTable");
            System.out.println(EX.toString());
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY product_id";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            this.closeConnection();
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
            preparedStatement.close();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO getAllProduct");
            System.out.println(EX.toString());
        }
        return productList;
    }

    @Override
    public Product getById(Integer productId) {
        String sql = "SELECT * FROM product WHERE product_id = ? ORDER BY product_id";
        Product product = new Product();
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            this.closeConnection();
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
            preparedStatement.close();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO getById");
            System.out.println(EX.toString());
        }
        return product;
    }

    @Override
    public List<Product> search(String searchKey) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name = ? ORDER BY product_id;";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, searchKey);
            ResultSet resultSet = preparedStatement.executeQuery();
            this.closeConnection();
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
            preparedStatement.close();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO search");
            System.out.println(EX.toString());
        }
        return productList;
    }

    @Override
    public void add(Product product) {
        String sql = "INSERT INTO product (name,price,quantity,description,category_id,status) VALUES (?,?,?,?,?,?);";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, 0);
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setInt(5, product.getCategory().getCategoryId());
            preparedStatement.setString(6, "active");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO Add");
            System.out.println(EX.toString());
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name = ?, price = ?, description = ?, category_id = ? WHERE product_id = ?;";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getCategory().getCategoryId());
            preparedStatement.setInt(5, product.getProductId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO Update");
            System.out.println(EX.toString());
        }
    }

    @Override
    public void delete(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = ? ;";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO Delete");
            System.out.println(EX.toString());
        }
    }

    @Override
    public void softDelete(Integer productId) {
        String sql = "UPDATE product SET status = ? WHERE product_id = ?;";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, "not active");
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO softDelete");
            System.out.println(EX.toString());
        }
    }

    public void updateQuantity(Product product, Integer oldQuantity) {
        String sql = "UPDATE product SET quantity = ? WHERE product_id = ?;";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,product.getQuantity() + oldQuantity);
            preparedStatement.setInt(2,product.getProductId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error ProductDAO UpdateQuantity");
            System.out.println(EX.toString());
        }
    }
}
