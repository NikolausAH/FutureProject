package com.blibli.pos_minimarket.DataAccessObject;

import java.util.List;

public interface InterfaceDAO<objectType,idType> {
    public List<objectType> getAll();
   // public objectType getOne(idType idType);
    public void add(objectType t);
    public void update(objectType t);
    public void delete(idType id);
    //public void softDelete(idType id);

}
