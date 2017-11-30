package com.blibli.pos_minimarket.Model;

public class Pegawai {
    private int id;
    private String password;
    private String nama;
    private int idRole;
    private String role;
    private String email;
////
    public Pegawai() {
    }

    public Pegawai(int id, String password, String nama, int idRole, String email) {
        this.id = id;
        this.password = password;
        this.nama = nama;
        this.idRole = idRole;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
