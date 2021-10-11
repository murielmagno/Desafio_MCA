package com.example.mca.doencas.dto;

import com.example.mca.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoencasDTO {

    private long id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Status status;
}
