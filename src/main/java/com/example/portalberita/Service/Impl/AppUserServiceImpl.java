package com.example.portalberita.Service.Impl;

import com.example.portalberita.DTO.LoginUserDto;
import com.example.portalberita.Entity.AppUser;
import com.example.portalberita.Repository.AppUserRepo;
import com.example.portalberita.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService, UserDetailsService{

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user not found")));
    }

    @Override
    public void registerUser(String username, String email, String password) {
        boolean ada = appUserRepo.findByEmail(email).isPresent();
        if (ada){
            throw new RuntimeException(String.format("user with this email already exists"));
        }

        String pass = bCryptPasswordEncoder.encode(password);

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setEmail(email);
        appUser.setPassword(pass);
        appUserRepo.save(appUser);
    }

    @Override
    public AppUser findusr(LoginUserDto loginUserDto) {
        return appUserRepo.findByEmail(loginUserDto.getEmail()).get();
    }

    @Override
    public void changePass(String newpass, String email) {
        AppUser appUser = appUserRepo.findByEmail(email).get();
        String newpassword = bCryptPasswordEncoder.encode(newpass);
        appUser.setPassword(newpassword);
        appUserRepo.save(appUser);
    }

    @Override
    public void changeEmail(String oldEmail, String newEmail) {
        AppUser appUser = appUserRepo.findByEmail(oldEmail).orElseThrow(() -> new UsernameNotFoundException("email notfound"));
        appUser.setEmail(newEmail);
        appUserRepo.save(appUser);
    }

    @Override
    public void changeUsername(String email, String username) {
        AppUser appUser = appUserRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("username notfound"));
        appUser.setUsername(username);
        appUserRepo.save(appUser);
    }


}
