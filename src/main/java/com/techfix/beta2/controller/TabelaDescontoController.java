package com.techfix.beta2.controller;

import com.techfix.beta2.domain.desconto.TabelaDescontoDto;
import com.techfix.beta2.domain.desconto.TabelaDescontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tabela_desconto")
public class TabelaDescontoController {

    @Autowired
    private TabelaDescontoService tabelaDescontoService;

    public ResponseEntity<List<TabelaDescontoDto>> listarTabelasDesconto(){
        return ResponseEntity.ok(tabelaDescontoService.listarTabelasDesconto());
    }

    public ResponseEntity<TabelaDescontoDto> cadastrarTabelaDesconto(@RequestBody TabelaDescontoDto dto){
        return ResponseEntity.ok(tabelaDescontoService.cadastrarTabelaDesconto(dto));
    }
}
