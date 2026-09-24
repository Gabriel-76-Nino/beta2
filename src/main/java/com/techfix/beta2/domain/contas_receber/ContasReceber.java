package com.techfix.beta2.domain.contas_receber;

import com.techfix.beta2.domain.pagamento.CondicaoPagamento;
import com.techfix.beta2.domain.pagamento.FormaPagamento;
import com.techfix.beta2.domain.venda.VendaCabecalho;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contas_receber")
@Setter
@Getter
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

    @ManyToOne
    @JoinColumn(name = "id_condicao")
    private CondicaoPagamento condicaoPagamento;

    private int numeroParcela;
    private Double valorParcela;
    private LocalDateTime vencimento;


    @Enumerated(EnumType.STRING)
    private StatusParcela statusParcela;

}
