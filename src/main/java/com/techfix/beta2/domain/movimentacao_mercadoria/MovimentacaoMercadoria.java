package com.techfix.beta2.domain.movimentacao_mercadoria;

import com.techfix.beta2.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes_mercadorias")
@Getter
@Setter
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
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    private int quantidade;
    private BigDecimal custoUnitario;
    private BigDecimal custoTotal;
    private BigDecimal custoUnitarioMedio;


}
