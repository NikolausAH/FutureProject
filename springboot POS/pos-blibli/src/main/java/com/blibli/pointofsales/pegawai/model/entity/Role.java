package com.blibli.pointofsales.pegawai.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(columnDefinition = "char(36")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(name = "nama_role")
    @NotBlank
    private String namaRole;
}
