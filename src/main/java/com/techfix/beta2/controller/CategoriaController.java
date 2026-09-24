package com.techfix.beta2.controller;

import com.techfix.beta2.domain.agregados_produto.categoria.CategoriaDto;
import com.techfix.beta2.domain.agregados_produto.categoria.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listarCategorias(){
        List<CategoriaDto> categoriaDto = categoriaService.listarCategorias();
        return ResponseEntity.ok(categoriaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrarCategoria(@RequestBody CategoriaDto dto){
        CategoriaDto categoriaDto = categoriaService.cadastrarCategoria(dto);
        return ResponseEntity.ok(categoriaDto);
    }

}
