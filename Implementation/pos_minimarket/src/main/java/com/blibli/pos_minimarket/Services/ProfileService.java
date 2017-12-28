package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ProfileDAO;
import com.blibli.pos_minimarket.Model.Employee;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private ProfileDAO profileDAO = new ProfileDAO();

    public void updateProfile(Employee employee){
        try{
            profileDAO.update(employee);
        }
        catch (Exception EX){
            System.out.println("Error ProfileService updateProfile");
            System.out.println(EX);
        }
    }
}
