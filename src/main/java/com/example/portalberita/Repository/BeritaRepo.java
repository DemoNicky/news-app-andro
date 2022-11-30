package com.example.portalberita.Repository;

import com.example.portalberita.Entity.Berita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeritaRepo extends JpaRepository<Berita, Long> {

    Page<Berita> findAll(Pageable pageable);
}
