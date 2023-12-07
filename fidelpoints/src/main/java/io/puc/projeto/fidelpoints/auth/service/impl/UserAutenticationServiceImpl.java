package io.puc.projeto.fidelpoints.auth.service.impl;

import io.puc.projeto.fidelpoints.auth.service.UserAutenticationService;
import io.puc.projeto.fidelpoints.entity.Cliente;
import io.puc.projeto.fidelpoints.entity.Lojista;
import io.puc.projeto.fidelpoints.exception.erros.ClienteNotFoundException;
import io.puc.projeto.fidelpoints.exception.erros.LojistaNotFoundException;
import io.puc.projeto.fidelpoints.repository.ClientesRepository;
import io.puc.projeto.fidelpoints.repository.LojistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserAutenticationServiceImpl implements UserDetailsService, UserAutenticationService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private LojistaRepository lojistaRepository;

    @Override
    public Cliente loadCliente(String username) {

        return clientesRepository
                .findByEmail(username)
                .orElseThrow(() -> new ClienteNotFoundException());

    }

    @Override
    public Lojista loadLojista(String username) {

        return lojistaRepository
                .findByLogin(username)
                .orElseThrow(() -> new LojistaNotFoundException());


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clientesRepository
                .findByEmail(username)
                .orElseThrow(() -> new ClienteNotFoundException());

        return User
                .builder()
                .username(cliente.getEmail())
                .password(cliente.getSenha())
                .roles(String.valueOf(new SimpleGrantedAuthority(cliente.getRole().name())))
                .build();

    }


}
