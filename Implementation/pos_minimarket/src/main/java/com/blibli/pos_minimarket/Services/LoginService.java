package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.LoginDAO;
import com.blibli.pos_minimarket.Model.Employee;
import org.springframework.stereotype.Service;

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
}
