package com.example.portalberita.Service.Impl;

import com.example.portalberita.DTO.GetBerita;
import com.example.portalberita.DTO.InsertBeritaDto;
import com.example.portalberita.Entity.AppUser;
import com.example.portalberita.Entity.Berita;
import com.example.portalberita.Entity.Category;
import com.example.portalberita.Entity.Imagega;
import com.example.portalberita.Repository.AppUserRepo;
import com.example.portalberita.Repository.BeritaRepo;
import com.example.portalberita.Repository.CategoryRepo;
import com.example.portalberita.Repository.ImagegaRepo;
import com.example.portalberita.Service.BeritaService;
import com.example.portalberita.Util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BeritaServiceImpl implements BeritaService {

    @Autowired
    private BeritaRepo beritaRepo;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ImagegaRepo imagegaRepo;

    @Override
    public void saveBerita(String email, String judul, String isi, String trending, String category, MultipartFile file) throws IOException {
        AppUser appUser = appUserRepo.findByEmail(email).get();
        Category cate = categoryRepo.findByName(category);
        Berita berita = new Berita();
        berita.setJudul(judul);
        berita.setWktCreateBerita(LocalDate.now());
        berita.setIsiBerita(isi);
        if (trending.equals("benar")){
            berita.setTrending(true);
        }
        Imagega imagegass = Imagega.builder()
                .namePho(file.getOriginalFilename())
                .type(file.getContentType())
                .pict(ImageUtils.compressImage(file.getBytes())).build();

        berita.setImage(imagegass);

        berita.setCategory(cate);
        berita.setAppUser(appUser);
        beritaRepo.save(berita);
    }

    @Override
    public List<Berita> getAllBerita(Pageable pageable) {
        Page<Berita> berita = beritaRepo.findAll(pageable);
        List<Berita> res = berita.stream().map((p) -> {
            Berita berita1 = new Berita();
            Imagega imagega = new Imagega();
            berita1.setId(p.getId());
            berita1.setJudul(p.getJudul());
            berita1.setIsiBerita(p.getIsiBerita());
            berita1.setWktCreateBerita(p.getWktCreateBerita());
            berita1.setTrending(p.isTrending());
            berita1.setLikeBerita(p.getLikeBerita());
            byte[] img = ImageUtils.decompressImage(p.getImage().getPict());
            imagega.setId(p.getImage().getId());
            imagega.setNamePho(p.getImage().getNamePho());
            imagega.setType(p.getImage().getType());
            imagega.setPict(img);
            berita1.setImage(imagega);
            berita1.setCategory(p.getCategory());
            berita1.setAppUser(p.getAppUser());
            return berita1;
        }).collect(Collectors.toList());
        return res;
    }

    @Override
    public byte[] download(String id) {
        Optional<Imagega> image = imagegaRepo.findById(id);
        byte[] img = ImageUtils.decompressImage(image.get().getPict());
        return img;
    }

    @Override
    public List<GetBerita> getBerita() {
        List<Berita> beritas = beritaRepo.findAll();
        List<GetBerita> beritas1 = beritas.stream().map((p) -> {
            GetBerita getBerita = new GetBerita();
            getBerita.setId(p.getId());
            getBerita.setJudul(p.getJudul());
            getBerita.setWktCreateBerita(p.getWktCreateBerita());
            getBerita.setIsiBerita(p.getIsiBerita());
            return getBerita;
        }).collect(Collectors.toList());

        return beritas1;
    }

//    @Override
//    public ResponseEntity<List<GetBerita>> getBerita() {
//        List<Berita> beritas = beritaRepo.findAll();
//        List<GetBerita> beritas1 = beritas.stream().map((p) -> {
//            GetBerita getBerita = new GetBerita();
//            getBerita.setId(p.getId());
//            getBerita.setJudul(p.getJudul());
//            getBerita.setWktCreateBerita(p.getWktCreateBerita());
//            getBerita.setIsiBerita(p.getIsiBerita());
//            return getBerita;
//        }).collect(Collectors.toList());
//
//        return beritas1;
//    }
}
