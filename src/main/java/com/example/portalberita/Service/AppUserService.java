package com.example.portalberita.Service;

import com.example.portalberita.DTO.LoginUserDto;
import com.example.portalberita.Entity.AppUser;
import org.springframework.http.ResponseEntity;

public interface AppUserService {
    void registerUser(String username, String email, String password);

    AppUser findusr(LoginUserDto loginUserDto);

    void changePass(String newpass, String email);

    void changeEmail(String oldEmail, String newEmail);

    void changeUsername(String email, String username);
}
