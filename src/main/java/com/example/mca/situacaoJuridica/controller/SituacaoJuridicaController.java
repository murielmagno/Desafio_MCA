package com.example.mca.situacaoJuridica.controller;

import com.example.mca.situacaoJuridica.dto.SituacaoJuridicaDTO;
import com.example.mca.situacaoJuridica.service.SituacaoJuridicaService;
import com.example.mca.utils.dto.MessageResponseDTO;
import com.example.mca.utils.exceptions.RegistroNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/situacaoJuridica")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SituacaoJuridicaController {

    private SituacaoJuridicaService situacaoJuridicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createSituacaoJuridica(@RequestBody @Valid SituacaoJuridicaDTO situacaoJuridicaDTO) {
        return situacaoJuridicaService.createSituacaoJuridica(situacaoJuridicaDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SituacaoJuridicaDTO> listAll() {
        return situacaoJuridicaService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SituacaoJuridicaDTO findById(@PathVariable Long id) throws RegistroNotFoundException {
        return situacaoJuridicaService.findByid(id);
    }

    @GetMapping("desativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO desativar(@PathVariable Long id, @RequestBody String observacao) throws RegistroNotFoundException {
        return situacaoJuridicaService.desativar(id,observacao);
    }

    @GetMapping("reativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO reativar(@PathVariable Long id,@RequestBody String observacao) throws RegistroNotFoundException {
        return situacaoJuridicaService.reativar(id,observacao);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid SituacaoJuridicaDTO situacaoJuridicaDTO) throws RegistroNotFoundException {
        return situacaoJuridicaService.updateById(id, situacaoJuridicaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) throws RegistroNotFoundException {
        situacaoJuridicaService.delete(id);
    }
}
