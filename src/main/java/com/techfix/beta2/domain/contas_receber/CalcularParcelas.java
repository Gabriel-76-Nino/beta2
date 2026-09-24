package com.techfix.beta2.domain.contas_receber;

public record CalcularParcelas(
        Long idFpg,
        Long idCondicao,
        Integer numeroParcela,
        Double valorParcela,
        java.time.LocalDateTime vencimento
) {
}
