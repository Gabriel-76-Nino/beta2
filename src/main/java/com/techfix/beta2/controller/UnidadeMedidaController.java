package com.techfix.beta2.controller;

import com.techfix.beta2.domain.produto.UnidadeMedida;
import com.techfix.beta2.domain.produto.UnidadeMedidaDto;
import com.techfix.beta2.domain.produto.UnidadeMedidaRepository;
import jdk.javadoc.doclet.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidade_medida")
public class UnidadeMedidaController {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    @GetMapping
    public ResponseEntity<List<UnidadeMedidaDto>> listarUnidadesMedidas(){
        List<UnidadeMedidaDto> unidadeMedidaDto = unidadeMedidaRepository.findAll().stream()
                .map(u -> new UnidadeMedidaDto(u.getId(),
                        u.getCodigo(), u.getDescricao(), u.getMultiplicador())).toList();
        return ResponseEntity.ok(unidadeMedidaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UnidadeMedidaDto> cadastrarUnidadeMedida(@RequestBody UnidadeMedidaDto dto){
        unidadeMedidaRepository.save(new UnidadeMedida(null, dto.codigo(), dto.descricao(), dto.multiplicador()));
        return ResponseEntity.ok(dto);
    }
}
