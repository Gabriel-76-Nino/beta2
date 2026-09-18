package com.techfix.beta2.controller;

import com.techfix.beta2.domain.produto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listarCategorias(){
        List<CategoriaDto> categoriaDto = categoriaRepository.findAll().stream()
                .map(c -> new CategoriaDto(c.getId(), c.getNomeCategoria(),
                        c.getDepartamento().getNomeDepartamento())).toList();
        return ResponseEntity.ok(categoriaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrarCategoria(@RequestBody CategoriaDto dto){
        Departamento departamento = departamentoRepository.getReferenceByNomeDepartamento(dto.departamento());
        categoriaRepository.save(new Categoria(null, dto.nomeCategoria(), departamento, null));
        return ResponseEntity.ok(dto);
    }

}
