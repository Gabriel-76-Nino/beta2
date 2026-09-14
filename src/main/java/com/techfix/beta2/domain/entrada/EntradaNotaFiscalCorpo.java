package com.techfix.beta2.domain.entrada;

import com.techfix.beta2.domain.compra.PedidoCorpo;
import com.techfix.beta2.domain.produto.Produto;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "entradas_notas_fiscal_corpo")
public class EntradaNotaFiscalCorpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_nota_cabecalho")
    private EntradaNotaFiscalCabecalho notaCabecalho;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private int quantidade;
    private BigDecimal precoCompra;

    @OneToOne(mappedBy = "entradaNotaFiscalCorpo")
    @JoinColumn(name = "id_pedido_corpo")
    private PedidoCorpo pedidoCorpo;

}
