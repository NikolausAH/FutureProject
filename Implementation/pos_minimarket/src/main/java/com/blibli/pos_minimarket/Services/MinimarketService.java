package com.blibli.pos_minimarket.Services;
import com.blibli.pos_minimarket.DataAccessObject.MinimarketDAO;
import com.blibli.pos_minimarket.Model.Minimarket;
import org.springframework.stereotype.Service;

@Service
public class MinimarketService {
    private MinimarketDAO minimarketDAO = new MinimarketDAO();

    public void updateMinimarket(Minimarket minimarket) {
        try {
            minimarketDAO.updateMinimarket(minimarket);
        }catch (Exception EX){
            System.out.println("Error MinimarketService updateMinimarket");
            System.out.println(EX);
        }
    }
    public Minimarket showMinimarket() {
        Minimarket minimarket = new Minimarket();
        try {
            minimarket = minimarketDAO.show();
        }catch (Exception EX){
            System.out.println("Error MinimarketService updateMinimarket");
            System.out.println(EX);
        }
        return minimarket;
    }
    public Double getTax() {
        Double tax = 0.0;
        try {
            tax = minimarketDAO.getTax();
        }catch (Exception EX){
            System.out.println("Error MinimarketService getTax");
            System.out.println(EX);
        }
        return tax;
    }
}
