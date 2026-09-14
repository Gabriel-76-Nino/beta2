package com.techfix.beta2.domain.contasreceber;

import com.techfix.beta2.domain.venda.FormaPagamento;
import com.techfix.beta2.domain.venda.VendaCabecalho;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_receber")
@NoArgsConstructor
@AllArgsConstructor
public class ContasReceber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_venda_cabecalho")
    private VendaCabecalho vendaCabecalho;

    @ManyToOne
    @JoinColumn(name = "id_fpg")
    private FormaPagamento fpg;

    private int numeroParcela;
    private BigDecimal valorParcela;
    private LocalDate vencimento;

    @Enumerated(EnumType.STRING)
    private StatusParcela statusParcela;

}
