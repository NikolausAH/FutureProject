package com.blibli.pointofsales.pegawai.repository;

import com.blibli.pointofsales.pegawai.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//String buat ID(UUID) karena id di db string
//Jparep itu class buat akses db
public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findAll();
}
