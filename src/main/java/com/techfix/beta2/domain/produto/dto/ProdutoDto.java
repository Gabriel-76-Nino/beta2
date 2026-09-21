package com.techfix.beta2.domain.produto.dto;

import com.techfix.beta2.domain.produto.Tipo;

import java.math.BigDecimal;

public record ProdutoDto(
        Long id,
        String descricao,
        String descricaoComercial,
        Tipo tipo,
        Long marca,
        Long departamento,
        Long categoria,
        Long subcategoria,
        Long fornecedor,
        Double margem,
        BigDecimal custoMedio,
        BigDecimal precoVenda,
        BigDecimal precoSugestao,
        Integer minimo,
        Integer maximo,
        Integer estoque,
        Long garantia,
        Long unidadeMedida,
        String foto,
        Boolean ativo
) {
}

