package com.techfix.beta2.controller;

import com.techfix.beta2.domain.produto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategoria")
public class SubcategoriaController {


    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @GetMapping
    public ResponseEntity<List<SubcategoriaDto>> listarSubcategorias(){
        List<SubcategoriaDto> subcategoriaDto = subcategoriaRepository.findAll().stream()
                .map(s -> new SubcategoriaDto(s.getId(), s.getNomeSubcategoria(),
                        s.getDepartamento().getNomeDepartamento(), s.getCategoria().getNomeCategoria())).toList();
        return ResponseEntity.ok(subcategoriaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<SubcategoriaDto> cadastraSubcategoria(@RequestBody SubcategoriaDto dto){
        Departamento departamento = departamentoRepository.getReferenceByNomeDepartamento(dto.departamento());
        Categoria categoria = categoriaRepository.getReferenceByNomeCategoria(dto.categoria());
        subcategoriaRepository.save(new Subcategoria(null, dto.nomeSubcategoria(), departamento, categoria));
        return ResponseEntity.ok(dto);
    }
}
