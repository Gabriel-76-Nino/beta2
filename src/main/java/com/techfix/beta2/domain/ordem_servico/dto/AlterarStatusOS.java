package com.techfix.beta2.domain.ordem_servico.dto;

import com.techfix.beta2.domain.ordem_servico.StatusOS;

public record AlterarStatusOS(
        Long id,
        StatusOS statusOS
) {
}
