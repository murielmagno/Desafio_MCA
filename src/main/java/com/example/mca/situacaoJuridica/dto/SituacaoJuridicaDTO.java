package com.example.mca.situacaoJuridica.dto;

import com.example.mca.utils.enums.Status;
import com.example.mca.utils.enums.TipoSituacaoJuridica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SituacaoJuridicaDTO {

    private long id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Status status;
    private TipoSituacaoJuridica tipoSituacaoJuridica;
    private String observacao;
}
