package com.techfix.beta2.domain.ordemservico.dto;

import com.techfix.beta2.domain.ordemservico.StatusOS;

public record DadosOSCadastro(
        Long pessoaId,
        String aparelhoCliente,
        String problemaRelatado,
        String acessoriosCliente,
        String atendenteRecebeu,
        StatusOS statusOS){

}
