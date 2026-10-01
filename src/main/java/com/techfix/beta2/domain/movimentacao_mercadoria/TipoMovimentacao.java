package com.techfix.beta2.domain.movimentacao_mercadoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipos_movimentacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoMovimentacao {

    @Id
    private Long codigoMovimentacao;
    private String descricaoMovimentacao;

    @Enumerated(EnumType.STRING)
    private Operacao operacao;
    private int acrecentaDiminui;

    public TipoMovimentacao(TipoMovimentacaoDto dto) {
        this.codigoMovimentacao = dto.codigoMovimentacao();
        this.descricaoMovimentacao = dto.descricaoMovimentacao();
        this.operacao = dto.operacao();
        if (dto.operacao() == Operacao.ENTRADA){
            this.acrecentaDiminui = 1;
        } else {
            this.acrecentaDiminui = -1;
        }
    }
}
