package com.example.portalberita.Controller;

import com.example.portalberita.DTO.LoginUserDto;
import com.example.portalberita.Entity.AppUser;
import com.example.portalberita.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class AppUserController {


    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("v1/register")
    public ResponseEntity<Void> registerUser(@RequestParam("username") String username,
                                             @RequestParam("email") String email,
                                             @RequestParam("password") String password){

        appUserService.registerUser(username, email, password);

        return ResponseEntity.created(URI.create("/v1/register")).build();
    }

    @PostMapping("v1/login")
    public ResponseEntity<AppUser> loginUser(@RequestBody @Valid LoginUserDto loginUserDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginUserDto.getEmail(), loginUserDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (!authentication.isAuthenticated()){
            return new ResponseEntity("error", HttpStatus.BAD_REQUEST);
        }else {
            AppUser user = appUserService.findusr(loginUserDto);
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

    @PutMapping("v1/changepass")
    public ResponseEntity<AppUser> changePassword(@RequestParam("oldpassword")String oldpass,
                                                  @RequestParam("newpassword")String newpass,
                                                  @RequestParam("email")String email){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email, oldpass
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (!authentication.isAuthenticated()){
            return new ResponseEntity("error", HttpStatus.BAD_REQUEST);
        }else {
            appUserService.changePass(newpass, email);
            return new ResponseEntity("success", HttpStatus.OK);
        }
    }

    @PutMapping("v1/changeemail")
    public void changeEmail(@RequestParam("oldemail")String oldEmail,
                               @RequestParam("newemail")String newEmail){
        appUserService.changeEmail(oldEmail, newEmail);
    }

    @PutMapping("v1/changeusername")
    public void changeUsername(@RequestParam("email")String email,
                            @RequestParam("username")String username){
        appUserService.changeUsername(email, username);
    }
}
