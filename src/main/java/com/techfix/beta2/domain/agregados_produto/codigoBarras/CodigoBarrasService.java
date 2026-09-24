package com.techfix.beta2.domain.agregados_produto.codigoBarras;

import com.techfix.beta2.domain.produto.Produto;
import com.techfix.beta2.domain.produto.ProdutoRepository;
import com.techfix.beta2.domain.agregados_produto.unidadeMedida.UnidadeMedida;
import com.techfix.beta2.domain.agregados_produto.unidadeMedida.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodigoBarrasService {

    @Autowired
    private CodigoBarrasRepository codigoBarrasRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;


    public CodigoBarrasDto cadastrarCodigoBarras(CodigoBarrasDto dto) {
        Produto produto = produtoRepository.getReferenceById(dto.produto());
        UnidadeMedida unidadeMedida = unidadeMedidaRepository.getReferenceByCodigo(dto.unidadeMedida());
        CodigoBarras codigoBarras = codigoBarrasRepository.save(new CodigoBarras(null, produto, dto.gtin(), unidadeMedida));
        return new CodigoBarrasDto(codigoBarras.getId(), codigoBarras.getProduto().getId(), codigoBarras.getGtin(), codigoBarras.getUnidadeMedida().getCodigo());
    }

    public List<CodigoBarrasDto> listarCodigosBarras() {
        return codigoBarrasRepository.findAll().stream()
                .map(c -> new CodigoBarrasDto(c.getId(), c.getProduto().getId(), c.getGtin(),
                        c.getUnidadeMedida().getCodigo())).toList();
    }
}
