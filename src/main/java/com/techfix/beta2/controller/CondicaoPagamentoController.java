package com.techfix.beta2.controller;

import com.techfix.beta2.domain.pagamento.CondicaoPagamentoDto;
import com.techfix.beta2.domain.pagamento.CondicaoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/condicao")
public class CondicaoPagamentoController {

    @Autowired
    private CondicaoPagamentoService condicaoPagamentoService;

    @GetMapping
    public ResponseEntity<List<CondicaoPagamentoDto>> listarCondicoesPagamento(){
        List<CondicaoPagamentoDto> condicoes = condicaoPagamentoService.listarCondicoesPagamento();
        return ResponseEntity.ok(condicoes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CondicaoPagamentoDto> cadastrarCondicaoPagamento(@RequestBody CondicaoPagamentoDto dto){
        CondicaoPagamentoDto condicaoPagamentoDto = condicaoPagamentoService.cadastrarCondicaoPagamento(dto);
        return ResponseEntity.ok(condicaoPagamentoDto);
    }
}
