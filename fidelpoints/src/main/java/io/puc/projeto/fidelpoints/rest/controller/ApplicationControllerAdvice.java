package io.puc.projeto.fidelpoints.rest.controller;


import io.puc.projeto.fidelpoints.exception.PedidoNaoEncontradoException;
import io.puc.projeto.fidelpoints.exception.RegraNegocioException;
import io.puc.projeto.fidelpoints.rest.ApiErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler( RegraNegocioException.class )
    @ResponseStatus ( BAD_REQUEST )
    public ApiErrors handleRegraNegocioException (RegraNegocioException exception ){
        String mensagemErro = exception.getMessage();
        return new ApiErrors( mensagemErro );
    }

    @ExceptionHandler( PedidoNaoEncontradoException.class )
    @ResponseStatus ( NOT_FOUND )
    public ApiErrors hendlePedidoNotFoundException( PedidoNaoEncontradoException exception ){
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    @ResponseStatus( BAD_REQUEST )
    public ApiErrors handleMethodNotValidException( MethodArgumentNotValidException exception ){
        List<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }



}
