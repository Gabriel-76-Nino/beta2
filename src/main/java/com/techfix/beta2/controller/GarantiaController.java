package com.techfix.beta2.controller;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import com.techfix.beta2.domain.produto.GarantiaDto;
import com.techfix.beta2.domain.produto.GarantiaRepository;
import com.techfix.beta2.domain.produto.Garatia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garantia")
public class GarantiaController {

    @Autowired
    private GarantiaRepository garantiaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<List<GarantiaDto>> listarGarantias(){
        List<GarantiaDto> garantiaDto = garantiaRepository.findAll().stream()
                .map(g -> new GarantiaDto(g.getId(), g.getFornecedor().getNome(),
                        g.getDetalhesGarantia(), g.getTempoGarantiaDias())).toList();
        return ResponseEntity.ok(garantiaDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<GarantiaDto> cadastrarGarantia(@RequestBody GarantiaDto dto){
        Pessoa fornecedor = pessoaRepository.getReferenceByNome(dto.fornecedor());
        garantiaRepository.save(new Garatia(null, fornecedor, dto.detalhesGarantia(), dto.tempoGarantiaDias()));
        return ResponseEntity.ok(dto);
    }
}
