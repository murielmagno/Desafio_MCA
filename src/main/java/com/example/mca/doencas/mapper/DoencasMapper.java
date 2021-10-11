package com.example.mca.doencas.mapper;

import com.example.mca.doencas.dto.DoencasDTO;
import com.example.mca.doencas.entity.Doencas;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoencasMapper {
    DoencasMapper INSTANCE = Mappers.getMapper(DoencasMapper.class);
    Doencas toModel(DoencasDTO doencasDTO);
    DoencasDTO toDTO(Doencas doencas);
}
