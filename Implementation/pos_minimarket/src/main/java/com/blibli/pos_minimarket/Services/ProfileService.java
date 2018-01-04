package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.EmployeeDAO;
import com.blibli.pos_minimarket.DataAccessObject.ProfileDAO;
import com.blibli.pos_minimarket.Model.Employee;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.SplittableRandom;

@Service
public class ProfileService {
    private ProfileDAO profileDAO = new ProfileDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public void updateProfile(Employee employee) {
        try {
            profileDAO.update(employee);
        } catch (Exception EX) {
            System.out.println("Error ProfileService updateProfile");
            System.out.println(EX);
        }
    }

    public void show(Employee employee) {
        try {
            profileDAO.show(employee);
        } catch (Exception EX) {
            System.out.println("Error ProfileService show");
            System.out.println(EX);
        }
    }
    public String  getPasswordById(Integer id){
        String password = null;
        try {
            password = employeeDAO.getPasswordById(id);
        }
        catch (Exception EX){
            System.out.println("Error ProfileService getPasswordById");
            System.out.println(EX);
        }
        return password;
    }
    public void  changePassword(Integer id, String passsword){
        try{
            employeeDAO.updatePassword(id,passsword);
        }
        catch (Exception EX){
            System.out.println("Error ProfileService updatePassword");
            System.out.println("EX");
        }
    }

    public String encrypt(String password) {
        byte[] bytesOfMessage = password.getBytes();
        byte[] arr = new byte[0];
        try{
        MessageDigest md = MessageDigest.getInstance("MD5");
        arr = md.digest(bytesOfMessage);}
        catch (Exception EX){
            System.out.println(EX.toString());
            System.out.println("Error ProfileService encrypt");
        }
        return Base64.getEncoder().encodeToString(arr);
    }
}
