package com.example.mca.doencas.controller;

import com.example.mca.doencas.dto.DoencasDTO;
import com.example.mca.doencas.service.DoencasService;
import com.example.mca.utils.dto.MessageResponseDTO;
import com.example.mca.utils.exceptions.RegistroNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doencas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DoencasController {

    private DoencasService doencasService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createDoenca(@RequestBody @Valid DoencasDTO doencasDTO) {
        return doencasService.createDoenca(doencasDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DoencasDTO> listAll() {
        return doencasService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoencasDTO findById(@PathVariable Long id) throws RegistroNotFoundException {
        return doencasService.findByid(id);
    }

    @GetMapping("desativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO desativar(@PathVariable Long id, @RequestBody String observacao) throws RegistroNotFoundException {
        return doencasService.desativar(id,observacao);
    }

    @GetMapping("reativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO reativar(@PathVariable Long id, @RequestBody String observacao) throws RegistroNotFoundException {
        return doencasService.reativar(id,observacao);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid DoencasDTO doencasDTO) throws RegistroNotFoundException {
        return doencasService.updateById(id, doencasDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) throws RegistroNotFoundException {
        doencasService.delete(id);
    }
}
