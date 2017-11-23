package com.blibli.pos_minimarket.Services;

import com.blibli.pos_minimarket.DataAccessObject.MyConnection;
import com.blibli.pos_minimarket.DataAccessObject.PegawaiDAO;
import com.blibli.pos_minimarket.Model.Pegawai;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //menandakan class ini service
public class PegawaiService {
    private PegawaiDAO pegawaiDAO = new PegawaiDAO();
    private MyConnection myConnection = new MyConnection();

    public List<Pegawai> showAll() {
        myConnection.makeConnection();
        List<Pegawai> listCategory = new ArrayList<>();
        try {
            listCategory = pegawaiDAO.getAll();
            myConnection.makeConnection();
        }
        catch (Exception EX){
            System.out.println("Error CategoryServices showAll");
            System.out.println(EX.toString());
        }
        return listCategory;
    }
}
