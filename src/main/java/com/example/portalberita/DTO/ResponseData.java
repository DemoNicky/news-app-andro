package com.example.portalberita.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> {

    private T result;
}
