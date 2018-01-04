package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.LoginDAO;
import com.blibli.pos_minimarket.Model.Employee;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Base64;

@Service
public class LoginService {
    private LoginDAO loginDAO = new LoginDAO();

    public Employee doLogin(Integer id, String password) {
        Employee employee = null;
        try {
            employee = loginDAO.getByIdAndPassword(id, password);
        }
        catch (Exception EX) {
            System.out.println("Error LoginService doLogin");
            System.out.println(EX.toString());
        }
        return employee;
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
