package com.techfix.beta2.domain.pessoa.service;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import com.techfix.beta2.domain.pessoa.dto.PessoaDto;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaDto> obterTodasPessoas() {

        List<Pessoa> pessoas = pessoaRepository.findAll();
        List<PessoaDto> pessoasDto = pessoas.stream().map(p -> new PessoaDto(p.getId(),
                p.getNome(), p.getCpfCnpj(), p.getEmail(), p.getFornecedor(),
                p.getFuncionario(), p.getCliente())).toList();

        return pessoasDto;

    }
}
