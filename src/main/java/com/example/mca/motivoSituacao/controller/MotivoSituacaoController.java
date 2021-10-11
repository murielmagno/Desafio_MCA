package com.example.mca.motivoSituacao.controller;

import com.example.mca.motivoSituacao.dto.MotivoSituacaoDTO;
import com.example.mca.motivoSituacao.service.MotivoSituacaoService;
import com.example.mca.utils.dto.MessageResponseDTO;
import com.example.mca.utils.exceptions.RegistroNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/motivoSituacao")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MotivoSituacaoController {

    private MotivoSituacaoService motivoSituacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createMotivoSituacao(@RequestBody @Valid MotivoSituacaoDTO motivoSituacaoDTO) {
        return motivoSituacaoService.createMotivoSituacao(motivoSituacaoDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MotivoSituacaoDTO> listAll() {
        return motivoSituacaoService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MotivoSituacaoDTO findById(@PathVariable Long id) throws RegistroNotFoundException {
        return motivoSituacaoService.findByid(id);
    }

    @GetMapping("desativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO desativar(@PathVariable Long id,@RequestBody String observacao) throws RegistroNotFoundException {
        return motivoSituacaoService.desativar(id,observacao);
    }

    @GetMapping("reativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO reativar(@PathVariable Long id,@RequestBody String observacao) throws RegistroNotFoundException {
        return motivoSituacaoService.reativar(id,observacao);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid MotivoSituacaoDTO motivoSituacaoDTO) throws RegistroNotFoundException {
        return motivoSituacaoService.updateById(id, motivoSituacaoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) throws RegistroNotFoundException {
        motivoSituacaoService.delete(id);
    }
}
