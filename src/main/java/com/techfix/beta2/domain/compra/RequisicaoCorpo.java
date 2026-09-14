package com.techfix.beta2.domain.compra;


import com.techfix.beta2.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "requisicaoes_corpo")
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoCorpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_requisicao_cabecalho")
    private RequisicaoCabecalho requisicaoCabecalho;

    private int numeroItem;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private String descricao;
    private int quantidade;

    @Enumerated(EnumType.STRING)
    private StatusReq statusReq;

    private LocalDate dataEntrega;
    private BigDecimal precoPretendido;

}
