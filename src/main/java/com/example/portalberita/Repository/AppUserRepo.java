package com.example.portalberita.Repository;

import com.example.portalberita.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByEmail(String email);

}
