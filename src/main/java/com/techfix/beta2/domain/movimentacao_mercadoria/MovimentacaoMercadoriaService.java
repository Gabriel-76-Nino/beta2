package com.techfix.beta2.domain.movimentacao_mercadoria;

import com.techfix.beta2.domain.agregados_produto.estoque.EstoqueService;
import com.techfix.beta2.domain.venda.VendaCorpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoMercadoriaService {

    @Autowired
    private MovimentacaoMercadoriaRepository movimentacaoMercadoriaRepository;
    @Autowired
    private TipoMovimentacaoRepository tipoMovimentacaoRepository;
    @Autowired
    private EstoqueService estoqueService;

    public void registrarSaidaVenda(List<VendaCorpo> vendaCorpo) {
        TipoMovimentacao tipoMovimentacao = tipoMovimentacaoRepository.getReferenceById(1001L);
        List<MovimentacaoMercadoria> movimentacao = vendaCorpo.stream()
                        .map(m -> new MovimentacaoMercadoria(null, LocalDateTime.now(), tipoMovimentacao,
                                m.getProduto(), m.getQuantidade(), null, null, null)).toList();
        movimentacaoMercadoriaRepository.saveAll(movimentacao);
        estoqueService.registrarQuantidade(movimentacao);
    }
}
