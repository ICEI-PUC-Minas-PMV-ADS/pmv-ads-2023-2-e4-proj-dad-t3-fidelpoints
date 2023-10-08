package io.puc.projeto.fidelpoints.rest.controller;


import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.repository.ClientesRepository;
import io.puc.projeto.fidelpoints.exception.RegraNegocioException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesRepository clientesRepository;

    public ClienteController( ClientesRepository clientesRepository ) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id ) {

       return clientesRepository
               .findById(id)
               .orElseThrow(() -> new ResponseStatusException(
                       HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public Cliente salvar ( @RequestBody @Valid Cliente cliente ){
        if( cliente.getNome() == null ||  cliente.getNome() == ""){
            throw new RegraNegocioException("O nome do cliente nao foi informado");
        }
        return clientesRepository.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    public  void  deletar ( @PathVariable Integer id ){

        clientesRepository.findById(id)
                .map( cliente -> {
                    clientesRepository.delete( cliente );
                    return cliente;
                })
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Cliente não encontrado" ));
    }

    @PutMapping("{id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    public void atualizar ( @PathVariable Integer id,
                           @RequestBody @Valid Cliente cliente ){

        clientesRepository
                .findById(id)
                .map( clienteExistente ->{
                    cliente.setId( clienteExistente.getId() );
                    clientesRepository.save( cliente );
                    return clienteExistente;
                }).orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Cliente não encontrado"));
        }

        @GetMapping
        public List<Cliente> listar ( Cliente filtro ){

            ExampleMatcher matcher = ExampleMatcher
                                        .matching()
                                        .withIgnoreCase()
                                        .withStringMatcher
                                                (ExampleMatcher.StringMatcher.CONTAINING);

            Example example = Example.of( filtro, matcher );
            return clientesRepository.findAll( example );
        }


    }
