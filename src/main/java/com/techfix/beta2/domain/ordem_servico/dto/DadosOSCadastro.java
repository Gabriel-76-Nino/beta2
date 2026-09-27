package com.techfix.beta2.domain.ordem_servico.dto;

import com.techfix.beta2.domain.aparelho_cliente.AparelhoClienteDto;
import com.techfix.beta2.domain.ordem_servico.StatusOS;

public record DadosOSCadastro(
        Long id,
        Long pessoa,
        Long idAparelhoCliente,
        AparelhoClienteDto aparelhoClienteDto,
        String problemaRelatado,
        String acessoriosCliente,
        String atendenteRecebeu,
        StatusOS statusOS
){
}
