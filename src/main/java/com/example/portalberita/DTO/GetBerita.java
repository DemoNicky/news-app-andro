package com.example.portalberita.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class GetBerita {

    private Long id;

    private String judul;

    private LocalDate wktCreateBerita;

    private String isiBerita;
}
