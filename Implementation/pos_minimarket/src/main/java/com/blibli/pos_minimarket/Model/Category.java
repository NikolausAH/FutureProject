package com.blibli.pos_minimarket.Model;

public class Category {
    private Integer kode;
    private String nama,deskripsi;
    public Category() {}

    public Category(Integer kode, String nama, String deskripsi){
        this.kode=kode;
        this.nama=nama;
        this.deskripsi=deskripsi;
    }
    public int getKode() {
        return kode;
    }

    public void setKode(Integer kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
