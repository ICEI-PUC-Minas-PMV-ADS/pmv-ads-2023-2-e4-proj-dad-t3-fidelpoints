package io.puc.projeto.fidelpoints.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.puc.projeto.fidelpoints.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cliente")
public class Cliente implements UserDetails {
    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty( message = "{campo.nome.obrigatorio}" )
    private String nome;

    @Column(name = "celular", length = 11)
    @NotEmpty( message = "{campo.celular.obrigatorio}")
    @Pattern(regexp = "\\d{10,11}")
    private String celular;


    @Column(name = "Email")
    @NotEmpty(message = "{campo.email.obrigatorio}")
    @Email
    private String email;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    @Column(name = "senha")
    private String senha;

    //@JsonIgnore
    @OneToMany( mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Pedido> pedidos;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
