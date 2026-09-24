package com.techfix.beta2.domain.venda;

import java.math.BigDecimal;

public record VendaCorpoDto(
        Long id,
        Long idVendaCabecalho,
        Integer numeroItem,
        Long produto,
        Integer quantidade,
        BigDecimal precoVenda,
        BigDecimal valorDesconto,
        Double percentualDesconto,
        BigDecimal custoMedioVenda,
        Double margemVenda
) {
}
