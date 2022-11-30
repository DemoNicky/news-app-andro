package com.example.portalberita.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_komentar")
@Data
public class Komentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isi_komentar", length = 1000)
    private String isiKomentar;

    @ManyToOne
    @JoinTable(name = "user_id")
    private AppUser appUser;

    @ManyToOne
    private Berita berita;
}
