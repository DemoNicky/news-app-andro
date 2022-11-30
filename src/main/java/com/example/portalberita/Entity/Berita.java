package com.example.portalberita.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tbl_berita")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Berita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "judul", length = 100, nullable = false, unique = true)
    private String judul;

    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDate wktCreateBerita;

    @Column(columnDefinition="TEXT")
    private String isiBerita;

    private boolean trending = Boolean.FALSE;

    @Column(name = "berita_suka")
    private Integer likeBerita;

    @OneToOne(cascade = CascadeType.ALL)
    private Imagega image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private AppUser appUser;
}
