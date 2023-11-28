package io.puc.projeto.fidelpoints.service.impl;

import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import io.puc.projeto.fidelpoints.domain.repository.ClientesRepository;
import io.puc.projeto.fidelpoints.domain.repository.LojistaRepository;
import io.puc.projeto.fidelpoints.rest.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.service.UserAutenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Cliente loadCliente(CredenciaisDTO credenciais) {

        return clientesRepository
                .findByEmail(credenciais.getLogin())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }


    @Override
    public Lojista loadLojista(CredenciaisDTO credenciais) {

        return lojistaRepository
                .findByLogin(credenciais.getLogin())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Lojista não encontrado"));


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clientesRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(" Cliente não encontrado. "));
        boolean roles = cliente.isUser();

        return User
                .builder()
                .username(cliente.getEmail())
                .password(cliente.getSenha())
                .roles("USER")
                .build();

    }


}
