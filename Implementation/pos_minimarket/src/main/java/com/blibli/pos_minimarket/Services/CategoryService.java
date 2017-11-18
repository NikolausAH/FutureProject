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
            System.out.println("Error CategoryService showAll");
            System.out.println(EX.toString());
        }
        return listCategory;
    }

    public void add(Category category) {
        try {
            categoryDAO.add(category);
        }
        catch (Exception EX){
            System.out.println("Error CategoryService Add");
            System.out.println(EX.toString());
        }
    }

    public void update(Category category){
        try {
            categoryDAO.update(category);
        }
        catch (Exception EX){
            System.out.println("Error CategoryService Update");
            System.out.println(EX.toString());
        }
    }

    public void delete(Integer categoryId){
        try {
            categoryDAO.delete(categoryId);
        }
        catch (Exception EX){
            System.out.println("Error CategoryService Delete");
            System.out.println(EX.toString());
        }
    }

    public void softDelete(Integer categoryId){
        try {
            categoryDAO.softDelete(categoryId);
        }
        catch (Exception EX){
            System.out.println("Error CategoryService softDelete");
            System.out.println(EX.toString());
        }
    }

    public List<Category> search(String searchKey) {
        List<Category> listCategory = new ArrayList<>();

        try {
            listCategory = categoryDAO.search(searchKey);
        } catch (Exception EX) {
            System.out.println("Error CategoryServices search");
            System.out.println(EX.toString());
        }
        return listCategory;
    }
}
