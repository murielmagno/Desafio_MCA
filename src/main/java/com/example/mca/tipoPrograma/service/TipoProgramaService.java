package com.example.mca.tipoPrograma.service;

import com.example.mca.utils.dto.MessageResponseDTO;
import com.example.mca.tipoPrograma.dto.TipoProgramaDTO;
import com.example.mca.tipoPrograma.entity.TipoPrograma;
import com.example.mca.tipoPrograma.mapper.TipoProgramaMapper;
import com.example.mca.tipoPrograma.repository.TipoProgramaRepository;
import com.example.mca.utils.enums.Status;
import com.example.mca.utils.exceptions.RegistroNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TipoProgramaService {

    private TipoProgramaRepository tipoProgramaRepository;

    private final TipoProgramaMapper tipoProgramaMapper = TipoProgramaMapper.INSTANCE;

    public MessageResponseDTO createTipoPrograma(TipoProgramaDTO tipoProgramaDTO) {
        TipoPrograma tipoProgramaToSave = tipoProgramaMapper.toModel(tipoProgramaDTO);
        tipoProgramaToSave.setDataInicio(LocalDate.now());
        tipoProgramaToSave.setStatus(Status.ATIVO);
        TipoPrograma savedTipoPrograma = tipoProgramaRepository.save(tipoProgramaToSave);
        return createdMessageResponse(savedTipoPrograma.getId(), "Sucesso! Registro cadastrado com sucesso!");
    }

    public List<TipoProgramaDTO> listAll() {
        List<TipoPrograma> allTipoPrograma = tipoProgramaRepository.findAll();
        return allTipoPrograma.stream().map(tipoProgramaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TipoProgramaDTO findByid(Long id) throws RegistroNotFoundException {
        TipoPrograma tipoPrograma = verifyIfExist(id);
        return tipoProgramaMapper.toDTO(tipoPrograma);
    }

    public void delete(Long id) throws RegistroNotFoundException {
        verifyIfExist(id);
        tipoProgramaRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, TipoProgramaDTO tipoProgramaDTO) throws RegistroNotFoundException {
        verifyIfExist(id);
        TipoPrograma tipoProgramaToUpdate = tipoProgramaMapper.toModel(tipoProgramaDTO);
        TipoPrograma updatedTipoPrograma = tipoProgramaRepository.save(tipoProgramaToUpdate);
        return createdMessageResponse(updatedTipoPrograma.getId(), "Sucesso! Registro atualizado com sucesso!");
    }

    public MessageResponseDTO reativar(Long id,String observacao) throws RegistroNotFoundException {
        TipoPrograma tipoPrograma = verifyIfExist(id);
        if (tipoPrograma.getStatus() == com.example.mca.utils.enums.Status.ATIVO) {
            return createdMessageResponse(tipoPrograma.getId(), "O Tipo de programa já está status!");
        } else {
            tipoPrograma.setStatus(com.example.mca.utils.enums.Status.ATIVO);
            tipoPrograma.setDataFim(null);
            tipoPrograma.setObservacao(observacao);
            tipoProgramaRepository.save(tipoPrograma);
            return createdMessageResponse(tipoPrograma.getId(), "Sucesso! Registro reativado com sucesso!");
        }
    }

    public MessageResponseDTO desativar(Long id, String observacao) throws RegistroNotFoundException {
        TipoPrograma tipoPrograma = verifyIfExist(id);
        if (tipoPrograma.getStatus() == Status.INATIVO) {
            return createdMessageResponse(tipoPrograma.getId(), "O Tipo de programa já está desativado!");
        } else {
            tipoPrograma.setStatus(com.example.mca.utils.enums.Status.INATIVO);
            tipoPrograma.setDataFim(LocalDate.now());
            tipoPrograma.setObservacao(observacao);
            tipoProgramaRepository.save(tipoPrograma);
            return createdMessageResponse(tipoPrograma.getId(), "Sucesso! Registro inativado com sucesso!");
        }
    }

    private TipoPrograma verifyIfExist(Long id) throws RegistroNotFoundException {
        return tipoProgramaRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }


    private MessageResponseDTO createdMessageResponse(Long id, String message) {
        return MessageResponseDTO.builder().message(message).build();
    }
}
