package com.techfix.beta2.domain.aparelho_cliente;

import com.techfix.beta2.domain.aparelho.TipoAparelho;

import java.util.Map;

public record AparelhoClienteDto(
        Long id,
        Long produto,
        TipoAparelho tipoAparelho,
        Map<String, Object> especificacoes,
        String imei,
        Long cliente
) {
}
