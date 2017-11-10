package com.blibli.pointofsales.pegawai.repository;


import com.blibli.pointofsales.pegawai.model.entity.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PegawaiRepository extends JpaRepository <Pegawai, String> {
    List<Pegawai> findAll();
}
