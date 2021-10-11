package com.example.mca.doencas.service;

import com.example.mca.doencas.dto.DoencasDTO;
import com.example.mca.doencas.entity.Doencas;
import com.example.mca.doencas.mapper.DoencasMapper;
import com.example.mca.doencas.repository.DoencasRepository;
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
public class DoencasService {
    private DoencasRepository doencasRepository;

    private final DoencasMapper doencasMapper = DoencasMapper.INSTANCE;

    public MessageResponseDTO createDoenca(DoencasDTO doencasDTO) {
        Doencas doencasToSave = doencasMapper.toModel(doencasDTO);
        doencasToSave.setDataInicio(LocalDate.now());
        doencasToSave.setStatus(Status.ATIVO);
        Doencas savedDoenca = doencasRepository.save(doencasToSave);
        return createdMessageResponse(savedDoenca.getId(), "Sucesso! Registro cadastrado com sucesso!");
    }

    public List<DoencasDTO> listAll() {
        List<Doencas> allDoencas = doencasRepository.findAll();
        return allDoencas.stream().map(doencasMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DoencasDTO findByid(Long id) throws RegistroNotFoundException {
        Doencas doencas = verifyIfExist(id);
        return doencasMapper.toDTO(doencas);
    }

    public void delete(Long id) throws RegistroNotFoundException {
        verifyIfExist(id);
        doencasRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, DoencasDTO doencasDTO) throws RegistroNotFoundException {
        verifyIfExist(id);
        Doencas doencasToUpdate = doencasMapper.toModel(doencasDTO);
        Doencas updatedDoencas = doencasRepository.save(doencasToUpdate);
        return createdMessageResponse(updatedDoencas.getId(), "Sucesso! Registro atualizado com sucesso!");
    }

    public MessageResponseDTO reativar(Long id, String observacao) throws RegistroNotFoundException {
        Doencas doencas = verifyIfExist(id);
        if (doencas.getStatus() == com.example.mca.utils.enums.Status.ATIVO) {
            return createdMessageResponse(doencas.getId(), "O Tipo de programa já está status!");
        } else {
            doencas.setStatus(com.example.mca.utils.enums.Status.ATIVO);
            doencas.setDataFim(null);
            doencas.setObservacao(observacao);
            doencasRepository.save(doencas);
            return createdMessageResponse(doencas.getId(), "Sucesso! Registro reativado com sucesso!");
        }
    }

    public MessageResponseDTO desativar(Long id,String observacao) throws RegistroNotFoundException {
        Doencas doencas = verifyIfExist(id);
        if (doencas.getStatus() == Status.INATIVO) {
            return createdMessageResponse(doencas.getId(), "O Tipo de programa já está desativado!");
        } else {
            doencas.setStatus(com.example.mca.utils.enums.Status.INATIVO);
            doencas.setDataFim(LocalDate.now());
            doencas.setObservacao(observacao);
            doencasRepository.save(doencas);
            return createdMessageResponse(doencas.getId(), "Sucesso! Registro inativado com sucesso!");
        }
    }

    private Doencas verifyIfExist(Long id) throws RegistroNotFoundException {
        return doencasRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    private MessageResponseDTO createdMessageResponse(Long id, String message) {
        return MessageResponseDTO.builder().message(message).build();
    }

}
