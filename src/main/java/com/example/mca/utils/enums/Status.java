package com.example.mca.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    ATIVO(0),
    INATIVO(1);

    private final int desc;
}
