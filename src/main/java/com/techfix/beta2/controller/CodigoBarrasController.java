package com.techfix.beta2.controller;

import com.techfix.beta2.domain.agregadosProduto.codigoBarras.CodigoBarrasDto;
import com.techfix.beta2.domain.agregadosProduto.codigoBarras.CodigoBarrasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/codigo_barras")
public class CodigoBarrasController {

    @Autowired
    private CodigoBarrasService codigoBarrasService;

    @GetMapping
    public ResponseEntity<List<CodigoBarrasDto>> listarCodigosBarras(){
        List<CodigoBarrasDto> codigoBarrasDto = codigoBarrasService.listarCodigosBarras();
        return ResponseEntity.ok(codigoBarrasDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CodigoBarrasDto> cadastrarCodigoBarras(@RequestBody CodigoBarrasDto dto){
        CodigoBarrasDto codigoBarrasDto = codigoBarrasService.cadastrarCodigoBarras(dto);
        return ResponseEntity.ok(codigoBarrasDto);
    }
}
