package io.puc.projeto.fidelpoints.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lojista")
public class Lojista {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "{campo.login.obrigatorio}")
    @Column
    @Email
    private String login;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    @Column
    private String senha;

    @Column
    private boolean admin;
}
