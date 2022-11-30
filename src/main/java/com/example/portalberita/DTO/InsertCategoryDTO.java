package com.example.portalberita.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InsertCategoryDTO {

    @NotNull(message = "nama kategori belum di isi")
    private String name;
}
