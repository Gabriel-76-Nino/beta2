package com.techfix.beta2.controller;

import com.techfix.beta2.domain.venda.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaCabecalhoService vendaCabecalhoService;

    @PostMapping
    @Transactional
    public ResponseEntity<VendaCabecalhoDto> cadastrarVenda(@RequestBody VendaCabecalhoDto dto){
        return ResponseEntity.ok(vendaCabecalhoService.cadastrarVenda(dto));
    }

}
