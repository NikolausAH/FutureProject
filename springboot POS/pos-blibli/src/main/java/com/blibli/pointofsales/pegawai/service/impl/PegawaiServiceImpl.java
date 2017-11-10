package com.blibli.pointofsales.pegawai.service.impl;

import com.blibli.pointofsales.pegawai.model.entity.Pegawai;
import com.blibli.pointofsales.pegawai.repository.PegawaiRepository;
import com.blibli.pointofsales.pegawai.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PegawaiServiceImpl implements PegawaiService {
    
    @Autowired //buat langsung mengisi variabel repo (yang bisa repository, service, component &controller)
    private PegawaiRepository repository;
    
    @Override
    public List<Pegawai> findAll(){
        return repository.findAll();
    }

    @Override
    public Pegawai create(Pegawai pegawai){
        pegawai.setPassword("password");
        return repository.save(pegawai); //save pegawai ke db
    }
}
