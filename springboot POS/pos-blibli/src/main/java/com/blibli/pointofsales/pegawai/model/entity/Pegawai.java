package com.blibli.pointofsales.pegawai.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter //bikin fungsi getter
@Setter
@Entity //masukin context(serv, cont, entity, repo) biar bisa dipanggil di repo
@Table(name = "pegawai") //nama tablenya di db yang mau di findall()
public class Pegawai {

    @Id //annotation langsung generate uuid
    @Column(columnDefinition = "char(36")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pegawai_id")
    @NotNull //4 anotasi buat autoinc
    private int pegawaiId;

    @Column(name = "password")
    private String password;

    @Column(name = "nama_pegawai")
    private String namaPegawai;

    @Column(name = "email")
    private String email;

    @OneToOne //foreign key harus ada
    @JoinColumn(name = "role_id", nullable = false) //buat foreign key di kolum mana
    private Role role;
}
