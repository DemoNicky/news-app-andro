package com.example.portalberita.Service;

import com.example.portalberita.DTO.GetBerita;
import com.example.portalberita.DTO.InsertBeritaDto;
import com.example.portalberita.Entity.Berita;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BeritaService {

    void saveBerita(String email, String judul, String isi, String trending, String category, MultipartFile file) throws IOException;

    List<Berita> getAllBerita(Pageable pageable);

    byte[] download(String id);

    List<GetBerita> getBerita();
}
