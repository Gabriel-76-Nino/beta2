package com.techfix.beta2.domain.movimentacao_mercadoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes_mercadorias")
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoMercadoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataMovimentacao;
    @ManyToOne
    @JoinColumn(name = "id_tipo_movimentacao")
    private TipoMovimentacao tipoMovimentacao;
    private int quantidade;
    private BigDecimal custoUnitario;
    private BigDecimal custoTotal;
    private BigDecimal custoUnitarioMedio;


}
