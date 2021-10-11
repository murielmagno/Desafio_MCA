package com.example.mca.motivoSituacao.dto;

import com.example.mca.utils.enums.Status;
import com.example.mca.utils.enums.TipoMotivoSituacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotivoSituacaoDTO {
    private long id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Status status;
    private TipoMotivoSituacao tipoMotivoSituacao;
    private String observacao;
}
