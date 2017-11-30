package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.PromoTotal;
import org.springframework.stereotype.Repository;

import java.util.List;
///
@Repository
public class PromoTotalDAO extends ConnectionSettings implements  InterfaceDAO<PromoTotal, Integer, String >{

    @Override
    public List<PromoTotal> getAll() {
        return null;
    }
//
    @Override
    public List<PromoTotal> search(String key) {
        return null;
    }

    @Override
    public void add(PromoTotal t) {

    }

    @Override
    public void update(PromoTotal t) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void softDelete(Integer integer) {

    }
}
