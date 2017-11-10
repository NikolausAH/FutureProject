package com.blibli.pointofsales.pegawai.service.impl;

import com.blibli.pointofsales.pegawai.model.entity.Role;
import com.blibli.pointofsales.pegawai.repository.RoleRepository;
import com.blibli.pointofsales.pegawai.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //biar bisa dipake di controller
public class RoleServiceImpl implements RoleService{

    @Autowired //buat langsung mengisi variabel repo (yang bisa repository, service, component &controller)
    private RoleRepository repository;

    @Override
    public List<Role> findAll(){
        return repository.findAll();
    }
}

