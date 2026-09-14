package com.techfix.beta2.domain.compra;

import com.techfix.beta2.domain.entrada.EntradaNotaFiscalCorpo;
import com.techfix.beta2.domain.produto.Produto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pedidos_corpo")
public class PedidoCorpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido_cabecalho")
    private PedidoCabecalho pedidoCabecalho;

    private int numeroItem;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private int quantidade;

    @Enumerated(EnumType.STRING)
    private StatusReq statusReq;

    private LocalDate dataEntrega;
    private BigDecimal precoCompra;

    @OneToOne
    private EntradaNotaFiscalCorpo entradaNotaFiscalCorpo;

}
