package com.blibli.pos_minimarket.DataAccessObject;

import java.util.List;

public interface InterfaceDAO<objectType,idType,keyType> {
    List<objectType> getAll();
    List<objectType> search(keyType key);
   // public objectType getOne(idType idType);
    void add(objectType t);
    void update(objectType t);
    void delete(idType id);
    void softDelete(idType idType);
//
}
