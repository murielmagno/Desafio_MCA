package com.example.mca.motivoSituacao.mapper;

import com.example.mca.motivoSituacao.dto.MotivoSituacaoDTO;
import com.example.mca.motivoSituacao.entity.MotivoSituacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MotivoSituacaoMapper {

    MotivoSituacaoMapper INSTANCE = Mappers.getMapper(MotivoSituacaoMapper.class);
    MotivoSituacao toModel(MotivoSituacaoDTO motivoSituacaoDTO);
    MotivoSituacaoDTO toDTO(MotivoSituacao motivoSituacao);
}
