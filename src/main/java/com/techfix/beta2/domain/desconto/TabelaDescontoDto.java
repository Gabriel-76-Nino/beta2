package com.techfix.beta2.domain.desconto;

public record TabelaDescontoDto(
        Long id,
        String usuario,
        Long departamento,
        Long categoria,
        Long subcategoria,
        Long formaPagamento,
        Long produto,
        Double desconto
) {
}
