package com.techfix.beta2.domain.agregados_produto.estoque;

import com.techfix.beta2.domain.movimentacao_mercadoria.MovimentacaoMercadoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;


    public void registrarQuantidade(List<MovimentacaoMercadoria> movimentacao){
        for (MovimentacaoMercadoria m : movimentacao){
            int estoqueAtual = m.getProduto().getEstoque().getEstoqueContabil();
            m.getProduto().getEstoque().setEstoqueContabil(estoqueAtual + (m.getQuantidade() * m.getTipoMovimentacao().getAcrecentaDiminui()));
        }
    }
}
