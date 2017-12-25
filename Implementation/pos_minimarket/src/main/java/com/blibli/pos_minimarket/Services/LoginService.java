package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.LoginDAO;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    LoginDAO loginDAO = new LoginDAO();

    public void Login(String name, String password){
        try{
            if(loginDAO.getRole(name,password) !=null){
                System.out.println("Login berhasil");
            }
            else {
                System.out.println("Login gagal");
            }
    }
        catch (Exception EX){
            System.out.println("Error LoginService");
            System.out.println(EX.toString());
        }
    }
}
