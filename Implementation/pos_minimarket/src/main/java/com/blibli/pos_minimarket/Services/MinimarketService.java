package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.ConnectionSettings;
import com.blibli.pos_minimarket.DataAccessObject.MinimarketDAO;
import com.blibli.pos_minimarket.Model.Minimarket;
import org.springframework.stereotype.Service;

@Service
public class MinimarketService {
    private ConnectionSettings myConnection = new ConnectionSettings();
    private MinimarketDAO minimarketDAO = new MinimarketDAO();

    public void updateMinimarket(Minimarket minimarket) {
        myConnection.makeConnection(); //connection antar dao dan db
        minimarketDAO.updateMinimarket(minimarket);
        myConnection.closeConnection();//nutup
    }
}
