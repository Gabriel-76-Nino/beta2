package com.techfix.beta2.domain.aparelho;

import com.techfix.beta2.domain.produto.Produto;
import com.techfix.beta2.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AparelhoService {

    @Autowired
    private AparelhoRepository aparelhoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<AparelhoDto> listarAparelhos() {
        return aparelhoRepository.findAll().stream()
                .map(a -> new AparelhoDto(a.getId(),
                        a.getProduto().getId(), a.getTipoAparelho(),
                        a.getCondicaoAparelho(), a.getEspecificacoes(),
                        a.getImei(), a.getObservacoes(), a.getPecasTrocadas(),
                        a.getStatusConservacao())).toList();

    }

    public AparelhoDto cadastrarAparelho(AparelhoDto dto){
        Produto produto = produtoRepository.getReferenceById(dto.produto());
        Aparelho aparelho = aparelhoRepository.save(new Aparelho(null, produto,
                dto.tipoAparelho(), dto.condicaoAparelho(), dto.especificacoes(),
                dto.imei(), dto.observacoes(), dto.pecasTrocadas(), dto.statusConservacao()));
        return new AparelhoDto(aparelho.getId(), aparelho.getProduto().getId(),
                aparelho.getTipoAparelho(), aparelho.getCondicaoAparelho(),
                aparelho.getEspecificacoes(), aparelho.getImei(), aparelho.getObservacoes(),
                aparelho.getPecasTrocadas(), aparelho.getStatusConservacao());
    }
}
