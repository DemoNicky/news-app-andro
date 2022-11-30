package com.example.portalberita.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginUserDto {

    @NotNull(message = "email is null")
    private String email;

    @NotNull(message = "password is null")
    private String password;
}
