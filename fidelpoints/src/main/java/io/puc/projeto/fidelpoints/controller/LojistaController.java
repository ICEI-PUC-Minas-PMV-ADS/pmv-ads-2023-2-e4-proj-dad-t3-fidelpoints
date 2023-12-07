package io.puc.projeto.fidelpoints.controller;


import io.puc.projeto.fidelpoints.entity.Lojista;
import io.puc.projeto.fidelpoints.auth.config.JwtService;
import io.puc.projeto.fidelpoints.auth.service.AutenticationService;
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