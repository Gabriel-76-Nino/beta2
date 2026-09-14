package com.techfix.beta2.domain.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "departamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomeDepartamento;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Categoria> categoria;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Subcategoria> subcategoria;

}
