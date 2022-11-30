package com.example.portalberita.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tbl_image")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Imagega {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(name = "nm_pho")
    private String namePho;

    private String type;

    @Column(name = "picture", length = 50000000)
    private byte[] pict;

}
