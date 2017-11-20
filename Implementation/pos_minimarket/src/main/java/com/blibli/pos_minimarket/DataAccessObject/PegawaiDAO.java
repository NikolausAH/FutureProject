package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Pegawai;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PegawaiDAO extends ConnectionSettings implements InterfaceDAO<Pegawai,Integer,String>{
    @Override
    public List<Pegawai> getAll() {
        return null;
    }

    @Override
    public List<Pegawai> search(String key) {
        return null;
    }

    @Override
    public void add(Pegawai t) {

    }

    @Override
    public void update(Pegawai t) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void softDelete(Integer integer) {

    }
}
