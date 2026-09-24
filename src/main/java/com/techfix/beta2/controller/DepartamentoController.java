package com.techfix.beta2.controller;

import com.techfix.beta2.domain.agregados_produto.departamento.DepartamentoDto;
import com.techfix.beta2.domain.agregados_produto.departamento.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<DepartamentoDto>> listarDepartamentos(){
        List<DepartamentoDto> departamentoDto = departamentoService.listarDepartamentos();
        return ResponseEntity.ok(departamentoDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DepartamentoDto> cadastrarDepartamento(@RequestBody DepartamentoDto dto){
        DepartamentoDto departamentoDto = departamentoService.cadatrarDepartamento(dto);
        return ResponseEntity.ok(departamentoDto);
    }

}
