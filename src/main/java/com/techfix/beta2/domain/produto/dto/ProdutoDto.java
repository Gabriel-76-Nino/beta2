package com.techfix.beta2.domain.produto.dto;

import com.techfix.beta2.domain.produto.Estoque;
import com.techfix.beta2.domain.produto.Tipo;

import java.math.BigDecimal;

public record ProdutoDto(
        Long id,
        String descricao,
        String descricaoComercial,
        Tipo tipo,
        String marca,
        String departamento,
        String categoria,
        String subcategoria,
        String fornecedor,
        Double margem,
        BigDecimal custoMedio,
        BigDecimal precoVenda,
        BigDecimal precoSugestao,
        Integer minimo,
        Integer maximo,
        Integer estoque,
        Long garantia,
        String unidadeMedida,
        String foto,
        Boolean ativo
) {
}

