package com.techfix.beta2.domain.contaspagar;

import com.techfix.beta2.domain.contasreceber.StatusParcela;
import com.techfix.beta2.domain.entrada.EntradaNotaFiscalCabecalho;
import com.techfix.beta2.domain.entrada.EntradaNotaFiscalCorpo;
import com.techfix.beta2.domain.venda.FormaPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_pagar")
public class ContasPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_entrada_cabecalho")
    private EntradaNotaFiscalCabecalho entradaNotaFiscalCabecalho;

    @ManyToOne
    @JoinColumn(name = "id_fpg")
    private FormaPagamento fpg;

    private LocalDate vencimento;
    private BigDecimal valor;
    private int numeroParcela;

    @Enumerated(EnumType.STRING)
    private StatusParcela statusParcela;

}
