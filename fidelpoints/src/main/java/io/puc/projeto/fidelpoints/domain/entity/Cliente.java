package io.puc.projeto.fidelpoints.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty( message = "{campo.nome.obrigatorio}" )
    private String nome;

    @Column(name = "celular", length = 11)
    @NotEmpty( message = "{campo.celular.obrigatorio}")
    @Pattern(regexp = "\\d{10,11}")
    private String celular;


    @Column(name = "Email")
    @NotEmpty(message = "{campo.email.obrigatório}")
    @Email
    private String email;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    @Column(name = "senha")
    private String senha;


    @JsonIgnore
    @OneToMany( mappedBy = "cliente", fetch = FetchType.LAZY )
    private Set<Pedido> pedidos;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Column
    private boolean User;


}
