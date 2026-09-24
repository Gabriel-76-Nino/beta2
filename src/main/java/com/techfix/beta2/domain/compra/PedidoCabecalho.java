package com.techfix.beta2.domain.compra;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.usuario.Usuario;
import com.techfix.beta2.domain.pagamento.FormaPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos_cabecalho")
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCabecalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "pedidoCabecalho")
    @JoinColumn(name = "id_requisicao_cabecalho")
    private RequisicaoCabecalho requisicaoCabecalho;

    @ManyToOne
    @JoinColumn(name = "nome_usuario")
    private Usuario usuario;

    private LocalDateTime dataCriacao;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Pessoa fornecedor;

    @ManyToOne
    @JoinColumn(name = "id_fpg")
    private FormaPagamento fpg;

    private int prazoPagamento;

    @OneToMany(mappedBy = "pedidoCabecalho")
    private List<PedidoCorpo> pedidoCorpo;


}
