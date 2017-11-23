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

    public List<Pegawai> showAll() {
        List<Pegawai> listCategory = new ArrayList<>();
        try {
            listCategory = pegawaiDAO.getAll();
        }
        catch (Exception EX){
            System.out.println("Error CategoryServices showAll");
            System.out.println(EX.toString());
        }
        return listCategory;
    }
    public void add(Pegawai pegawai) {
        try {
            pegawaiDAO.add(pegawai);
        }
        catch (Exception EX){
            System.out.println("Error CategoryServices Add");
            System.out.println(EX.toString());
        }
    }
}
