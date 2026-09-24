package com.techfix.beta2.domain.movimentacao_mercadoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipos_movimentacoes")
@NoArgsConstructor
@AllArgsConstructor
public class TipoMovimentacao {

    @Id
    private Long codigoMovimentacao;
    private String descricaoMovimentacao;

    @Enumerated(EnumType.STRING)
    private Operacao operacao;
    private int acrecentaDiminui;

}
