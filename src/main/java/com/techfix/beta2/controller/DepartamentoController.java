package com.techfix.beta2.controller;

import com.techfix.beta2.domain.produto.Departamento;
import com.techfix.beta2.domain.produto.DepartamentoDto;
import com.techfix.beta2.domain.produto.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public ResponseEntity<List<DepartamentoDto>> listarDepartamentos(){
        List<DepartamentoDto> departamentoDto = departamentoRepository.findAll().stream()
                .map(d -> new DepartamentoDto(d.getId(), d.getNomeDepartamento())).toList();
        return ResponseEntity.ok(departamentoDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DepartamentoDto> cadastrarDepartamento(@RequestBody DepartamentoDto dto){
        departamentoRepository.save(new Departamento(null, dto.nomeDepartamento(), null, null));
        return ResponseEntity.ok(dto);
    }

}
