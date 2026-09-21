package com.techfix.beta2.controller;

import com.techfix.beta2.domain.agregadosProduto.referencia.ReferenciaDto;
import com.techfix.beta2.domain.agregadosProduto.referencia.ReferenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referencia")
public class ReferenciaController {

    @Autowired
    private ReferenciaService referenciaService;

    @GetMapping
    public ResponseEntity<List<ReferenciaDto>> listarReferencias(){
        List<ReferenciaDto> referenciaDto = referenciaService.listarReferencias();
        return ResponseEntity.ok(referenciaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReferenciaDto> cadastrarReferencias(@RequestBody ReferenciaDto dto){
        ReferenciaDto referenciaDto = referenciaService.cadastrarReferencia(dto);
        return ResponseEntity.ok(referenciaDto);
    }

}
