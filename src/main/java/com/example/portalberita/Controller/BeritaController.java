package com.example.portalberita.Controller;

import com.example.portalberita.DTO.GetBerita;
import com.example.portalberita.DTO.InsertBeritaDto;
import com.example.portalberita.Entity.Berita;
import com.example.portalberita.Entity.Category;
import com.example.portalberita.Entity.Imagega;
import com.example.portalberita.Service.BeritaService;
import com.example.portalberita.Service.Impl.BeritaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/")
public class BeritaController {

    @Autowired
    private BeritaService beritaService;

//    @RequestParam("judul")String judul,
//    @RequestParam("isiberita")String isiBerita,
//    @RequestParam("trending")String trending,
//    @RequestParam("category")String category,
//    @RequestParam("image")MultipartFile multipartFile

    @PostMapping(value = {"/v1/berita/insert"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    private ResponseEntity insertBerita(@RequestParam("email")String email,
                                        @RequestParam("judul")String judul,
                                        @RequestParam("isiberita")String isi,
                                        @RequestParam("trending")String trending,
                                        @RequestParam("category")String category,
                                        @RequestParam("image")MultipartFile file) throws IOException {

        beritaService.saveBerita(email, judul, isi, trending, category, file);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @GetMapping("/v1/berita/get")
//    @PathVariable("page")int page
    private Map<String, Berita[]> getPageBerita(){
        Map<String, Berita[]> ress = new HashMap<>();
        Pageable pageable = PageRequest.of(0, 10);
        List<Berita> beritas = beritaService.getAllBerita(pageable);
        Berita[] strings = beritas.toArray(Berita[]::new);
        ress.put("beritaa", strings);
        return ress;

    }

    @GetMapping("v1/download/{id}")
    private ResponseEntity<?> downloadImage(@PathVariable String id){
        byte[] image = beritaService.download(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @GetMapping("v1/getberita")
    public List<GetBerita> getAll(){
        return beritaService.getBerita();
    }

}
