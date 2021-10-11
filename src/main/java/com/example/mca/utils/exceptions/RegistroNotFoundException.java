package com.example.mca.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroNotFoundException extends Exception {

    public RegistroNotFoundException(Long id){
        super("Registro não encontrado!");
    }
}
