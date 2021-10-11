package com.example.mca.tipoPrograma.controller;

import com.example.mca.utils.dto.MessageResponseDTO;
import com.example.mca.tipoPrograma.dto.TipoProgramaDTO;
import com.example.mca.utils.exceptions.RegistroNotFoundException;
import com.example.mca.tipoPrograma.service.TipoProgramaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tipoPrograma")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TipoProgramaController {

    private TipoProgramaService tipoProgramaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createTipoPrograma(@RequestBody @Valid TipoProgramaDTO tipoProgramaDTO){
        return tipoProgramaService.createTipoPrograma(tipoProgramaDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TipoProgramaDTO> listAll() {
        return tipoProgramaService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProgramaDTO findById(@PathVariable Long id) throws RegistroNotFoundException {
        return tipoProgramaService.findByid(id);
    }

    @GetMapping("desativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO desativar(@PathVariable Long id,@RequestBody String observacao) throws RegistroNotFoundException {
        return tipoProgramaService.desativar(id,observacao);
    }

    @GetMapping("reativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO reativar(@PathVariable Long id,@RequestBody String observacao) throws RegistroNotFoundException {
        return tipoProgramaService.reativar(id,observacao);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid TipoProgramaDTO tipoProgramaDTO) throws RegistroNotFoundException {
        return tipoProgramaService.updateById(id,tipoProgramaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) throws RegistroNotFoundException {
        tipoProgramaService.delete(id);
    }

}
