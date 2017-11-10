package com.blibli.pointofsales.pegawai.service;

import com.blibli.pointofsales.pegawai.model.entity.Pegawai;

import java.util.List;

public interface PegawaiService {
    
    List<Pegawai> findAll(); //ambil semua data di tabel pegawai

    Pegawai create(Pegawai pegawai); //gausa dibuat di rep soalnya create uda dibuatin
}
