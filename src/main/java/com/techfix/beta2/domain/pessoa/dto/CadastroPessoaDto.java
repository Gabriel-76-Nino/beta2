package com.techfix.beta2.domain.pessoa.dto;

import java.time.LocalDate;

public record CadastroPessoaDto(
        String nome,
        String razaoSocial,
        String cpfCnpj,
        String email,
        LocalDate nacimentoFundacao,
        String telefoneTitular,
        String logradouro,
        String numero,
        String bairro,
        String complemento,
        String cidade,
        String uf,
        String cep,
        String nomeSegundaPessoa,
        String telefoneSegundaPessoa,
        Boolean cliente,
        Boolean funcionario,
        Boolean fornecedor,
        String siteFornecedor
) {
}
