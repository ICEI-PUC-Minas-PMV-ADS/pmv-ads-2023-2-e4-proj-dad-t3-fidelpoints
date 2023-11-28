package io.puc.projeto.fidelpoints.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.repository.ClientesRepository;
import io.puc.projeto.fidelpoints.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;


@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientesRepository clientesRepository;

    @Override
    @Transactional
    public Cliente salvarCliente(Cliente cliente){

        String senhaCriptografada = passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(senhaCriptografada);

        return clientesRepository.save(cliente);
    }

    @Override
    public void deletarCliente(Integer id) {
        clientesRepository.findById(id)
                .map( cliente -> {
                    clientesRepository.delete( cliente );
                    return cliente;
                })
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Cliente não encontrado" ));
    }

    @Override
    public void atualizarCliente(Integer id, Cliente cliente) {
        clientesRepository
                .findById(id)
                .map( clienteExistente ->{
                    cliente.setId( clienteExistente.getId() );
                    clientesRepository.save(cliente);
                    return clienteExistente;
                }).orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Cliente não encontrado"));
    }


    @Override
    public Cliente getCliente(Integer id) {
        return clientesRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }



    @Override
    public List<Cliente> findClientByEmail(String email) {

        Cliente cliente = clientesRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(" Cliente não encontrado. "));

        return Collections.singletonList(cliente);

    }
}
