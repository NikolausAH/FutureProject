package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.CategoryDAO;
import com.blibli.pos_minimarket.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();

    public List<Category> showAll() {
        List<Category> listCategory = new ArrayList<>();
        try {
            listCategory = categoryDAO.getAll();
        }
        catch (Exception EX){
            System.out.println("Error CategoryServices showAll");
            System.out.println(EX.toString());
        }
        return listCategory;
    }

    public void add(Category category) {
        try {
            categoryDAO.add(category);
        }
        catch (Exception EX){
            System.out.println("Error CategoryServices Add");
            System.out.println(EX.toString());
        }
    }

    public void update(Category category){
        try {
            categoryDAO.update(category);
        }
        catch (Exception EX){
            System.out.println("Error CategoryServices Update");
            System.out.println(EX.toString());
        }
    }

    public void delete(Integer kode){
        try {
            categoryDAO.delete(kode);
        }
        catch (Exception EX){
            System.out.println("Error CategoryServices Delete");
            System.out.println(EX.toString());
        }
    }
}
