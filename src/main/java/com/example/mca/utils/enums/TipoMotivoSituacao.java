package com.example.mca.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoMotivoSituacao {

    ACOLHIDO(0),
    TRANSFERIDO(1),
    PENDENTE(2),
    DESLIGADO(3);

    private final int desc;
}
