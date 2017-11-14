package com.blibli.pos_minimarket.Model;

public class Product {

    private Integer kode,stok;
    private Integer kategoriId;
    private String nama,deskripsi;
    private Integer harga;
    public Product() {}
    public Product(Integer kode, String nama, Integer kategoriId, String deskripsi, Integer stok, Integer harga){
        this.kode=kode;
        this.nama=nama;
        this.kategoriId=kategoriId;
        this.deskripsi=deskripsi;
        this.stok=stok;
        this.harga=harga;
    }

    public Integer getKode() {
        return kode;
    }

    public void setKode(Integer kode) {
        this.kode = kode;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Integer getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Integer kategoriId) {
        this.kategoriId = kategoriId;
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

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}
