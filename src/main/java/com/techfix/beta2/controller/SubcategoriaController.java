package com.techfix.beta2.controller;

import com.techfix.beta2.domain.agregadosProduto.subcategoria.SubcategoriaDto;
import com.techfix.beta2.domain.agregadosProduto.subcategoria.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategoria")
public class SubcategoriaController {

    @Autowired
    private SubcategoriaService subcategoriaService;

    @GetMapping
    public ResponseEntity<List<SubcategoriaDto>> listarSubcategorias(){
        List<SubcategoriaDto> subcategoriaDto = subcategoriaService.listarSubcategorias();
        return ResponseEntity.ok(subcategoriaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<SubcategoriaDto> cadastraSubcategoria(@RequestBody SubcategoriaDto dto){
        SubcategoriaDto subcategoriaDto = subcategoriaService.cadastrarSubcategoria(dto);
        return ResponseEntity.ok(subcategoriaDto);
    }
}
