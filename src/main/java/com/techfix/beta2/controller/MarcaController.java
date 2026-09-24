package com.techfix.beta2.controller;

import com.techfix.beta2.domain.agregados_produto.marca.MarcaDto;
import com.techfix.beta2.domain.agregados_produto.marca.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaDto>> listarMarcas(){
        List<MarcaDto> marcaDto = marcaService.listarMarcas();
        return ResponseEntity.ok(marcaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MarcaDto> cadastrarMarca(@RequestBody MarcaDto dto){
        MarcaDto marcaDto = marcaService.cadastrarMarca(dto);
        return ResponseEntity.ok(marcaDto);
    }
}
