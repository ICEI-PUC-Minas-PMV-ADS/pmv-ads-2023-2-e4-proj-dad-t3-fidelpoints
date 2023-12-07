package io.puc.projeto.fidelpoints.controller;


import io.puc.projeto.fidelpoints.entity.Cliente;
import io.puc.projeto.fidelpoints.auth.service.AutenticationService;
import io.puc.projeto.fidelpoints.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import java.util.List;


@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AutenticationService autenticationService;

    @GetMapping("/teste")
    @ResponseStatus( HttpStatus.OK )
    public String getClienteById() {

        return new String("Sucesso");
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id ) {

       return clienteService.getCliente(id);
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public Cliente salvar ( @RequestBody @Valid Cliente cliente ){

        return clienteService.salvarCliente(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    public  void  deletar ( @PathVariable Integer id ){

         clienteService.deletarCliente(id);
    }


    @PutMapping("{id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    public void atualizar ( @PathVariable Integer id,
                           @RequestBody @Valid Cliente cliente ){

        clienteService.atualizarCliente(id, cliente);
    }



       /* @GetMapping
        public List<Cliente> listar ( Cliente filtro ){

            ExampleMatcher matcher = ExampleMatcher
                                        .matching()
                                        .withIgnoreCase()
                                        .withStringMatcher
                                                (ExampleMatcher.StringMatcher.CONTAINING);

            Example example = Example.of( filtro, matcher );
            return clientesRepository.findAll( example );
        }*/


    @GetMapping
    public List<Cliente> listarClientes ( @Email @RequestParam(required=false, name="email") String email ){

        return clienteService.findClientByEmail( email );
    }

}
