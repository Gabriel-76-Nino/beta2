package com.techfix.beta2.controller;

import com.techfix.beta2.domain.movimentacao_mercadoria.TipoMovimentacaoDto;
import com.techfix.beta2.domain.movimentacao_mercadoria.TipoMovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacao")
public class TipoMovimentacaoController {

    @Autowired
    private TipoMovimentacaoService tipoMovimentacaoService;

    @GetMapping
    public ResponseEntity<List<TipoMovimentacaoDto>> listarTiposMovimentacao(){
        return ResponseEntity.ok(tipoMovimentacaoService.listarTiposMovimentacao());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TipoMovimentacaoDto> cadastrarTipoMovimentacao(@RequestBody TipoMovimentacaoDto dto){
        return ResponseEntity.ok(tipoMovimentacaoService.cadastrarTiposMovimentacao(dto));
    }
}
