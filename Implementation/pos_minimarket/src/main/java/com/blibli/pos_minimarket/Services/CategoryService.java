package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.CategoryDAO;
import com.blibli.pos_minimarket.DataAccessObject.MyConnection;
import com.blibli.pos_minimarket.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private MyConnection myConnection = new MyConnection();
    private CategoryDAO categoryDAO = new CategoryDAO();

    public List<Category> showAllCategory() {
        myConnection.makeConnection();
        List<Category> listCategory = categoryDAO.getAllCategory();
        myConnection.closeConnection();
        return listCategory;
    }

    public void createCategory(Category category) {
        myConnection.makeConnection();
        categoryDAO.createCategory(category);
        myConnection.closeConnection();
    }

    public void updateCategory(Category category){
        myConnection.makeConnection();
        categoryDAO.updateCategory(category);
        myConnection.closeConnection();
    }

    public void deleteCategory(Integer kode){
        myConnection.makeConnection();
        categoryDAO.deleteCategory(kode);
        myConnection.closeConnection();
    }
}
