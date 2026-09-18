package com.techfix.beta2.controller;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import com.techfix.beta2.domain.pessoa.dto.CadastroPessoaDto;
import com.techfix.beta2.domain.pessoa.dto.PessoaDto;
import com.techfix.beta2.domain.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaDto>> listarTodasPessoas(){
        List<PessoaDto> pessoasDto = pessoaService.obterTodasPessoas();
        return ResponseEntity.ok(pessoasDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Pessoa> cadastroPessoa(@RequestBody CadastroPessoaDto dto){

        Pessoa pessoa = new Pessoa(dto);
        pessoaRepository.save(pessoa);
        return ResponseEntity.ok(pessoa);

    }

}
