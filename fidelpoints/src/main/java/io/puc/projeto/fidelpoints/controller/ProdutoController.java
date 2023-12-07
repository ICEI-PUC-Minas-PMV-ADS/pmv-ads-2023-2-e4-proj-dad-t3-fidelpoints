package io.puc.projeto.fidelpoints.controller;


import io.puc.projeto.fidelpoints.entity.Produto;
import io.puc.projeto.fidelpoints.repository.ProdutosRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutosRepository produtosRepository;

    public ProdutoController( ProdutosRepository produtosRepository ){
        this.produtosRepository = produtosRepository;
    }

    @GetMapping("/teste")
    @ResponseStatus( HttpStatus.OK )
    public String getClienteById() {

        return new String("Sucesso");
    }

    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id ){

        return produtosRepository
                .findById(id)
                .orElseThrow(() ->new ResponseStatusException
                        ( HttpStatus.NOT_FOUND, "Produto não encontrado" ));
    }

    @PostMapping
    @ResponseStatus ( CREATED )
    public Produto salvar ( @RequestBody @Valid Produto produto ){
        return produtosRepository.save(produto);
    }

    @DeleteMapping ("{id}")
    @ResponseStatus ( NO_CONTENT )
    public void deletar ( @PathVariable Integer id ){

        produtosRepository
                .findById(id)
                .map( produto -> {
                    produtosRepository.delete( produto );
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException
                        ( HttpStatus.NOT_FOUND, "Produto não encontrado" ));

    }


    @PutMapping("{id}")
    @ResponseStatus ( NO_CONTENT )
    public void atualizar ( @PathVariable Integer id,
                            @RequestBody  @Valid Produto produto ) {

        produtosRepository
                .findById(id)
                .map( produtoExistente -> {
                    produto.setId( produtoExistente.getId() );
                    produtosRepository.save( produto );
                    return produtoExistente;
                }).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado" ));
    }

    @GetMapping
    public List<Produto> listar ( Produto filtro ){

        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of( filtro, matcher );
        return produtosRepository.findAll( example );




    }



}
