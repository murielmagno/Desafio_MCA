package com.example.mca.tipoPrograma.mapper;

import com.example.mca.tipoPrograma.dto.TipoProgramaDTO;
import com.example.mca.tipoPrograma.entity.TipoPrograma;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoProgramaMapper {

    TipoProgramaMapper INSTANCE = Mappers.getMapper(TipoProgramaMapper.class);

    TipoPrograma toModel(TipoProgramaDTO tipoProgramaDTO);

    TipoProgramaDTO toDTO(TipoPrograma tipoPrograma);
}
