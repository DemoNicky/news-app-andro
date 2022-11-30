package com.example.portalberita.DTO;

import com.example.portalberita.Entity.Category;
import com.example.portalberita.Entity.Imagega;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InsertBeritaDto {

    @NotNull(message = "judul is empty")
    private String judul;

    @NotNull(message = "isiBerita is empty")
    private String isierita;

    @NotNull(message = "trending is empty")
    private String trending;

    private String category;
}
