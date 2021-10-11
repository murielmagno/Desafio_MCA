package com.example.mca.situacaoJuridica.mapper;

import com.example.mca.situacaoJuridica.dto.SituacaoJuridicaDTO;
import com.example.mca.situacaoJuridica.entity.SituacaoJuridica;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SituacaoJuridicaMapper {

    SituacaoJuridicaMapper INSTANCE = Mappers.getMapper(SituacaoJuridicaMapper.class);

    SituacaoJuridica toModel(SituacaoJuridicaDTO situacaoJuridicaDTO);

    SituacaoJuridicaDTO toDTO(SituacaoJuridica situacaoJuridica);
}
