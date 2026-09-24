package com.techfix.beta2.controller;

import com.techfix.beta2.domain.agregados_produto.unidadeMedida.UnidadeMedidaDto;
import com.techfix.beta2.domain.agregados_produto.unidadeMedida.UnidadeMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidade_medida")
public class UnidadeMedidaController {

    @Autowired
    private UnidadeMedidaService unidadeMedidaService;

    @GetMapping
    public ResponseEntity<List<UnidadeMedidaDto>> listarUnidadesMedidas(){
        List<UnidadeMedidaDto> unidadeMedidaDto = unidadeMedidaService.listarUnidadesMedidas();
        return ResponseEntity.ok(unidadeMedidaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UnidadeMedidaDto> cadastrarUnidadeMedida(@RequestBody UnidadeMedidaDto dto){
        UnidadeMedidaDto unidadeMedidaDto = unidadeMedidaService.cadastrarUnidadeMedida(dto);
        return ResponseEntity.ok(unidadeMedidaDto);
    }


}
