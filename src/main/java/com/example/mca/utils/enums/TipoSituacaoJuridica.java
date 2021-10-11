package com.example.mca.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoSituacaoJuridica {

    PROCESSO(0),
    PROCEDIMENTO(1);

    private final int desc;

}
