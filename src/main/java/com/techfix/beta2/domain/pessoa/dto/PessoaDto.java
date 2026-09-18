package com.techfix.beta2.domain.pessoa.dto;

public record PessoaDto(
        Long id,
        String nome,
        String cpfCnpj,
        String email,
        Boolean cliente,
        Boolean funcionario,
        Boolean fornecedor
) {
}
