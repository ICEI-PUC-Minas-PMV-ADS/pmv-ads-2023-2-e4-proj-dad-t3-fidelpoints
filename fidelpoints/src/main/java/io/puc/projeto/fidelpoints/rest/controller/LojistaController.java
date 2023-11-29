package io.puc.projeto.fidelpoints.rest.controller;


import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import io.puc.projeto.fidelpoints.jwt.JwtService;
import io.puc.projeto.fidelpoints.service.AutenticationService;
import io.puc.projeto.fidelpoints.service.impl.LojistaServiceImpl;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/lojistas")
@RequiredArgsConstructor
@Builder
public class LojistaController {

    private final LojistaServiceImpl lojistaService;
    private final JwtService jwtService;

    private final AutenticationService autenticationService;


    @GetMapping("/teste")
    @ResponseStatus( HttpStatus.OK )
    public String getClienteById() {

        return new String("Sucesso");
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Lojista salvar(@RequestBody @Valid Lojista lojista) {

        return lojistaService.salvarLojista(lojista);
    }
}