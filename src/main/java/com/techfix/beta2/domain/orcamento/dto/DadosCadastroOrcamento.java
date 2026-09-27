package com.techfix.beta2.domain.orcamento.dto;

import java.util.List;

public record DadosCadastroOrcamento(
        Long ordemServico,
        List<ListaProdutosCadastroDto> listaItens
) {
}

