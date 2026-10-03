package com.techfix.beta2.domain.aparelho;

import java.util.Map;

public record AparelhoDto(
        Long id,
        Long produto,
        TipoAparelho tipoAparelho,
        CondicaoAparelho condicaoAparelho,
        Map<String, Object> especificacoes,
        String imei,
        String observacoes,
        String pecasTrocadas,
        String statusConservacao
) {
}
