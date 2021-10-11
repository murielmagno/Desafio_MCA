package com.example.mca.situacaoJuridica.service;

import com.example.mca.situacaoJuridica.entity.SituacaoJuridica;
import com.example.mca.situacaoJuridica.mapper.SituacaoJuridicaMapper;
import com.example.mca.situacaoJuridica.repository.SituacaoJuridicaRepository;
import com.example.mca.situacaoJuridica.dto.SituacaoJuridicaDTO;
import com.example.mca.utils.dto.MessageResponseDTO;
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
public class SituacaoJuridicaService {

    private SituacaoJuridicaRepository situacaoJuridicaRepository;

    private final SituacaoJuridicaMapper situacaoJuridicaMapper = SituacaoJuridicaMapper.INSTANCE;

    public MessageResponseDTO createSituacaoJuridica(SituacaoJuridicaDTO situacaoJuridicaDTO) {
        SituacaoJuridica situacaoJuridicaToSave = situacaoJuridicaMapper.toModel(situacaoJuridicaDTO);
        situacaoJuridicaToSave.setDataInicio(LocalDate.now());
        situacaoJuridicaToSave.setStatus(Status.ATIVO);
        SituacaoJuridica savedSituacaoJuridica = situacaoJuridicaRepository.save(situacaoJuridicaToSave);
        return createdMessageResponse(savedSituacaoJuridica.getId(), "Sucesso! Registro cadastrado com sucesso!");
    }

    public List<SituacaoJuridicaDTO> listAll() {
        List<SituacaoJuridica> allSituacaoJuridica = situacaoJuridicaRepository.findAll();
        return allSituacaoJuridica.stream().map(situacaoJuridicaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SituacaoJuridicaDTO findByid(Long id) throws RegistroNotFoundException {
        SituacaoJuridica situacaoJuridica = verifyIfExist(id);
        return situacaoJuridicaMapper.toDTO(situacaoJuridica);
    }

    public void delete(Long id) throws RegistroNotFoundException {
        verifyIfExist(id);
        situacaoJuridicaRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, SituacaoJuridicaDTO situacaoJuridicaDTO) throws RegistroNotFoundException {
        verifyIfExist(id);
        SituacaoJuridica situacaoJuridicaToUpdate = situacaoJuridicaMapper.toModel(situacaoJuridicaDTO);
        SituacaoJuridica updatedSituacaoJuridica = situacaoJuridicaRepository.save(situacaoJuridicaToUpdate);
        return createdMessageResponse(updatedSituacaoJuridica.getId(), "Sucesso! Registro atualizado com sucesso!");
    }

    public MessageResponseDTO reativar(Long id,String observacao) throws RegistroNotFoundException {
        SituacaoJuridica situacaoJuridica = verifyIfExist(id);
        if (situacaoJuridica.getStatus() == com.example.mca.utils.enums.Status.ATIVO) {
            return createdMessageResponse(situacaoJuridica.getId(), "O Tipo de programa já está status!");
        } else {
            situacaoJuridica.setStatus(com.example.mca.utils.enums.Status.ATIVO);
            situacaoJuridica.setDataFim(null);
            situacaoJuridica.setObservacao(observacao);
            situacaoJuridicaRepository.save(situacaoJuridica);
            return createdMessageResponse(situacaoJuridica.getId(), "Sucesso! Registro reativado com sucesso!");
        }
    }

    public MessageResponseDTO desativar(Long id,String observacao) throws RegistroNotFoundException {
        SituacaoJuridica situacaoJuridica = verifyIfExist(id);
        if (situacaoJuridica.getStatus() == Status.INATIVO) {
            return createdMessageResponse(situacaoJuridica.getId(), "O Tipo de programa já está desativado!");
        } else {
            situacaoJuridica.setStatus(com.example.mca.utils.enums.Status.INATIVO);
            situacaoJuridica.setDataFim(LocalDate.now());
            situacaoJuridica.setObservacao(observacao);
            situacaoJuridicaRepository.save(situacaoJuridica);
            return createdMessageResponse(situacaoJuridica.getId(), "Sucesso! Registro inativado com sucesso!");
        }
    }

    private SituacaoJuridica verifyIfExist(Long id) throws RegistroNotFoundException {
        return situacaoJuridicaRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    private MessageResponseDTO createdMessageResponse(Long id, String message) {
        return MessageResponseDTO.builder().message(message).build();
    }
}
