package com.example.mca.motivoSituacao.service;

import com.example.mca.motivoSituacao.dto.MotivoSituacaoDTO;
import com.example.mca.motivoSituacao.entity.MotivoSituacao;
import com.example.mca.motivoSituacao.mapper.MotivoSituacaoMapper;
import com.example.mca.motivoSituacao.repository.MotivoSituacaoRepository;
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
public class MotivoSituacaoService {

    private MotivoSituacaoRepository motivoSituacaoRepository;

    private final MotivoSituacaoMapper motivoSituacaoMapper = MotivoSituacaoMapper.INSTANCE;


    public MessageResponseDTO createMotivoSituacao(MotivoSituacaoDTO motivoSituacaoDTO) {
        MotivoSituacao motivoSituacaoToSave = motivoSituacaoMapper.toModel(motivoSituacaoDTO);
        motivoSituacaoToSave.setDataInicio(LocalDate.now());
        motivoSituacaoToSave.setStatus(Status.ATIVO);
        MotivoSituacao savedMotivoSituacao = motivoSituacaoRepository.save(motivoSituacaoToSave);
        return createdMessageResponse(savedMotivoSituacao.getId(), "Sucesso! Registro cadastrado com sucesso!");
    }

    public List<MotivoSituacaoDTO> listAll() {
        List<MotivoSituacao> allMotivoSituacao = motivoSituacaoRepository.findAll();
        return allMotivoSituacao.stream().map(motivoSituacaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MotivoSituacaoDTO findByid(Long id) throws RegistroNotFoundException {
        MotivoSituacao motivoSituacao = verifyIfExist(id);
        return motivoSituacaoMapper.toDTO(motivoSituacao);
    }

    public void delete(Long id) throws RegistroNotFoundException {
        verifyIfExist(id);
        motivoSituacaoRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, MotivoSituacaoDTO motivoSituacaoDTO) throws RegistroNotFoundException {
        verifyIfExist(id);
        MotivoSituacao motivoSituacaoToUpdate = motivoSituacaoMapper.toModel(motivoSituacaoDTO);
        MotivoSituacao updatedmotivoSituacao = motivoSituacaoRepository.save(motivoSituacaoToUpdate);
        return createdMessageResponse(updatedmotivoSituacao.getId(), "Sucesso! Registro atualizado com sucesso!");
    }

    public MessageResponseDTO reativar(Long id,String observacao) throws RegistroNotFoundException {
        MotivoSituacao motivoSituacao = verifyIfExist(id);
        if (motivoSituacao.getStatus() == com.example.mca.utils.enums.Status.ATIVO) {
            return createdMessageResponse(motivoSituacao.getId(), "O Tipo de programa já está status!");
        } else {
            motivoSituacao.setStatus(com.example.mca.utils.enums.Status.ATIVO);
            motivoSituacao.setDataFim(null);
            motivoSituacao.setObservacao(observacao);
            motivoSituacaoRepository.save(motivoSituacao);
            return createdMessageResponse(motivoSituacao.getId(), "Sucesso! Registro reativado com sucesso!");
        }
    }

    public MessageResponseDTO desativar(Long id,String observacao) throws RegistroNotFoundException {
        MotivoSituacao motivoSituacao = verifyIfExist(id);
        if (motivoSituacao.getStatus() == Status.INATIVO) {
            return createdMessageResponse(motivoSituacao.getId(), "O Tipo de programa já está desativado!");
        } else {
            motivoSituacao.setStatus(com.example.mca.utils.enums.Status.INATIVO);
            motivoSituacao.setDataFim(LocalDate.now());
            motivoSituacao.setObservacao(observacao);
            motivoSituacaoRepository.save(motivoSituacao);
            return createdMessageResponse(motivoSituacao.getId(), "Sucesso! Registro inativado com sucesso!");
        }
    }

    private MotivoSituacao verifyIfExist(Long id) throws RegistroNotFoundException {
        return motivoSituacaoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    private MessageResponseDTO createdMessageResponse(Long id, String message) {
        return MessageResponseDTO.builder().message(message).build();
    }
}
