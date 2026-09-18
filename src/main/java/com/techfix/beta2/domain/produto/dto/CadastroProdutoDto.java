package com.techfix.beta2.domain.produto.dto;

import com.techfix.beta2.domain.produto.Tipo;

public record CadastroProdutoDto(
        String descricao,
        String descricaoComercial,
        Tipo tipo,
        String marca,
        String departamento,
        String categoria,
        String subcategoria,
        String fornecedor,
        String codigoBarras,
        String referencia,
        Double margem,
        Integer minimo,
        Integer maximo,
        Long garantia,
        String unidadeMedida,
        String foto
) {
}
