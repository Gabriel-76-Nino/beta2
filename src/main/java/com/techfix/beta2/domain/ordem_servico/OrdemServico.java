package com.techfix.beta2.domain.ordem_servico;

import com.techfix.beta2.domain.aparelhos_cliente.AparelhoCliente;
import com.techfix.beta2.domain.orcamento.Orcamento;
import com.techfix.beta2.domain.ordem_servico.dto.DadosOSCadastro;
import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.produto.Produto;
import com.techfix.beta2.domain.usuario.Usuario;
import com.techfix.beta2.domain.venda.VendaCabecalho;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordens_servicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    private LocalDateTime dataEntrada;

    @ManyToOne
    @JoinColumn(name = "id_aparelho_cliente")
    private AparelhoCliente aparelhoCliente;

    private String problemaRelatado;
    private String fotoEntrada;
    private String acessoriosCliente;

    private Usuario atendenteRecebeu;

    @ManyToOne
    @JoinColumn(name = "nome_usuario")
    private Usuario tecnicoResponsavel;

    private String diagnosticoTecnico;
    private Boolean diagnosticoIgualRelato;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
    private List<Orcamento> orcamento;

    @Enumerated(EnumType.STRING)
    private StatusOS statusOS;

    private String testesRealizados; // terá objeto Testes
    private String cpfRetirante;
    private String nomeRetirante; // se for o cliente que truxe o aparelho, vai puxar automaticamente, se for outro terá que ser digitado.
    private LocalDateTime dataRetirada;

    @OneToOne
    private VendaCabecalho notaVenda;


    public OrdemServico(DadosOSCadastro dto, Pessoa id, Usuario usuario, Produto produto) {
        this.pessoa = id;
        this.dataEntrada = LocalDateTime.now();
        this.aparelhoCliente = new AparelhoCliente(dto, produto, pessoa);
        this.problemaRelatado = dto.problemaRelatado();
        this.acessoriosCliente = dto.acessoriosCliente();
        this.atendenteRecebeu = usuario;
        if (statusOS == null) {
            this.statusOS = StatusOS.AGUARDANDO_ATENDIMENTO;
        }   else {
            this.statusOS = StatusOS.APROVADO_BALCAO;
        }
    }



}
