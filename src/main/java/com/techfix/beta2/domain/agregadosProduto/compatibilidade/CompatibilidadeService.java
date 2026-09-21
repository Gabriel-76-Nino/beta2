package com.techfix.beta2.domain.agregadosProduto.compatibilidade;

import com.techfix.beta2.domain.produto.Produto;
import com.techfix.beta2.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilidadeService {

    @Autowired
    private CompatibilidadeRepository compatibilidadeRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public CompatibilidadeDto cadastrarCompatibilidade(CompatibilidadeDto dto) {
        Produto produto = produtoRepository.getReferenceById(dto.produto());
        Compatibilidade compatibilidade = compatibilidadeRepository.save(new Compatibilidade(null, produto, dto.compativel()));
        return new CompatibilidadeDto(compatibilidade.getId(), compatibilidade.getProduto().getId(), compatibilidade.getCompativel());
    }

    public List<CompatibilidadeDto> listarCompatibilidades() {
        return compatibilidadeRepository.findAll().stream()
                .map(c -> new CompatibilidadeDto(c.getId(), c.getProduto().getId(), c.getCompativel())).toList();
    }
}
