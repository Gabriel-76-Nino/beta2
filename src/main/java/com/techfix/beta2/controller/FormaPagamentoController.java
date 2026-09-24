package com.techfix.beta2.controller;

import com.techfix.beta2.domain.pagamento.FormaPagamentoDto;
import com.techfix.beta2.domain.pagamento.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forma_pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDto>> listarFormasPagamento(){
        return ResponseEntity.ok(formaPagamentoService.listarFormasPagamento());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FormaPagamentoDto> cadasdastrarFormaPagamento(@RequestBody FormaPagamentoDto dto){
        return ResponseEntity.ok(formaPagamentoService.cadastrarFormaPagamento(dto));
    }
}
