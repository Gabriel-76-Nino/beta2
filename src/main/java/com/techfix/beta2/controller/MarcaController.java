package com.techfix.beta2.controller;

import com.techfix.beta2.domain.produto.Marca;
import com.techfix.beta2.domain.produto.MarcaDto;
import com.techfix.beta2.domain.produto.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public ResponseEntity<List<MarcaDto>> listarMarcas(){
        List<MarcaDto> marcaDto = marcaRepository.findAll().stream()
                .map(m -> new MarcaDto(m.getId(), m.getNomeMarca())).toList();
        return ResponseEntity.ok(marcaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MarcaDto> cadastrarMarca(@RequestBody MarcaDto dto){
        marcaRepository.save(new Marca(null, dto.nomeMarca()));
        return ResponseEntity.ok(dto);
    }
}
