package com.techfix.beta2.domain.compra;

import com.techfix.beta2.domain.ordem_servico.OrdemServico;
import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "requisicoes_cabecalho)")
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoCabecalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_os")
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "nome_usuario")
    private Usuario usuario;

    private LocalDateTime dataCriacao;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Pessoa fornecedor;

    @OneToOne
    private PedidoCabecalho pedidoCabecalho;


}
