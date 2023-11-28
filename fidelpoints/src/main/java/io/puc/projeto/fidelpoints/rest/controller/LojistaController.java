package io.puc.projeto.fidelpoints.rest.controller;


import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import io.puc.projeto.fidelpoints.domain.enums.RoleEnum;
import io.puc.projeto.fidelpoints.jwt.JwtService;
import io.puc.projeto.fidelpoints.rest.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.rest.dto.TokenDTO;
import io.puc.projeto.fidelpoints.service.AutenticationService;
import io.puc.projeto.fidelpoints.service.impl.LojistaServiceImpl;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Builder
public class LojistaController {

    private final LojistaServiceImpl lojistaService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AutenticationService autenticationService;


    @PostMapping
    @ResponseStatus(CREATED)
    public Lojista salvar(@RequestBody @Valid Lojista lojista) {

        return lojistaService.salvarLojista(lojista);
    }



    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
        credenciais.setRoleEnum(RoleEnum.LOJISTA);
       return autenticationService.tokenDTO(credenciais);
    }
}