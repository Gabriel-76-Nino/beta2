package com.techfix.beta2.domain.venda;

import com.techfix.beta2.domain.pagamento.FormaPagamentoECondicaoDto;

import java.time.LocalDateTime;
import java.util.List;

public record VendaCabecalhoDto(
        Long id,
        Long numeroNota,
        Long cliente,
        LocalDateTime dataVenda,
        String nomeUsuario,
        Long os,
        List<FormaPagamentoECondicaoDto> fpgs,
        List<VendaCorpoDto> itens
) {
}
