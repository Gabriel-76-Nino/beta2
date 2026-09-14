package com.techfix.beta2.domain.orcamento.dto;

import com.techfix.beta2.domain.orcamento.StatusItemOrcamento;

import java.math.BigDecimal;

public record DadosCadastroOrcamento(
        Long ordemServico,
        Integer numeroItem,
        Long idProduto,
        String descricao,
        Integer quantidade,
        BigDecimal precoPeca,
        BigDecimal precoMaoObra,
        StatusItemOrcamento statusItemOrcamento,
        String observacoes
) {
}

