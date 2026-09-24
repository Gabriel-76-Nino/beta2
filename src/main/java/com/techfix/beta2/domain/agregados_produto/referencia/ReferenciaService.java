package com.techfix.beta2.domain.agregados_produto.referencia;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import com.techfix.beta2.domain.produto.Produto;
import com.techfix.beta2.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenciaService {

    @Autowired
    private ReferenciaRepository referenciaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public ReferenciaDto cadastrarReferencia(ReferenciaDto dto) {
        Produto produto = produtoRepository.getReferenceById(dto.produto());
        Pessoa fornecedor = pessoaRepository.getReferenceById(dto.fornecedor());
        Referencia referencia = referenciaRepository.save(new Referencia(null, fornecedor, produto, dto.referencia()));
        return new ReferenciaDto(referencia.getId(), referencia.getFornecedor().getId(), referencia.getProduto().getId(),
                referencia.getReferencia());
    }

    public List<ReferenciaDto> listarReferencias() {
        return referenciaRepository.findAll().stream()
                .map(r -> new ReferenciaDto(r.getId(), r.getFornecedor().getId(), r.getProduto().getId(),
                        r.getReferencia())).toList();
    }
}
