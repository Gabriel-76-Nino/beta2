package com.techfix.beta2.domain.agregadosProduto.categoria;

import com.techfix.beta2.domain.agregadosProduto.departamento.Departamento;
import com.techfix.beta2.domain.agregadosProduto.subcategoria.Subcategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomeCategoria;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Subcategoria> subcategoria;

}
