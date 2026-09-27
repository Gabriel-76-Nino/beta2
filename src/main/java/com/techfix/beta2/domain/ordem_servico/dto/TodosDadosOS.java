package com.techfix.beta2.domain.ordem_servico.dto;

import com.techfix.beta2.domain.aparelho_cliente.AparelhoClienteDto;
import com.techfix.beta2.domain.orcamento.dto.DadosCadastroOrcamento;
import com.techfix.beta2.domain.ordem_servico.StatusOS;

import java.time.LocalDateTime;

public record TodosDadosOS(
        Long id,
        Long idCliente,
        LocalDateTime dataEntrada,
        AparelhoClienteDto aparelhoClienteDto,
        String problemaRelatado,
        String fotoEntrada,
        String acessoriosCliente,
        String atendenteRecebeu,
        String tecnicoResponsavel,
        String diagnosticoTecnico,
        Boolean diagnosticoIgualRelato,
        DadosCadastroOrcamento orcamento,
        StatusOS statusOS,
        String testesRealizados,
        String cpfRetirante,
        String nomeRetirante,
        LocalDateTime dataRetirada
) {
}
