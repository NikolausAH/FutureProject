package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.EmployeeDAO;
import com.blibli.pos_minimarket.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //menandakan class ini service
public class EmployeeService {
    private EmployeeDAO EmployeeDAO = new EmployeeDAO();

    public List<Employee> showAll() {
        List<Employee> listCategory = new ArrayList<>();
        try {
            listCategory = EmployeeDAO.getAll();
        }
        catch (Exception EX){
            System.out.println("Error CategoryServices showAll");
            System.out.println(EX.toString());
        }
        return listCategory;
    }
    public void add(Employee Employee) {
        try {
            EmployeeDAO.add(Employee);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService Add");
            System.out.println(EX.toString());
        }
    }
    public void update(Employee Employee){
        try{
            EmployeeDAO.update(Employee);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService Update");
            System.out.println(EX.toString());
        }
    }
    public void delete(Integer id){
        try {
            EmployeeDAO.delete(id);
        }
        catch (Exception EX){
            System.out.println("Error EmployeeService Delete");
            System.out.println(EX.toString());
        }
    }
}
