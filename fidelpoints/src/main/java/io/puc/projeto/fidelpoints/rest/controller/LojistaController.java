package io.puc.projeto.fidelpoints.rest.controller;


import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import io.puc.projeto.fidelpoints.exception.RegraNegocioException;
import io.puc.projeto.fidelpoints.exception.SenhaInvalidaException;
import io.puc.projeto.fidelpoints.jwt.JwtService;
import io.puc.projeto.fidelpoints.rest.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.rest.dto.TokenDTO;
import io.puc.projeto.fidelpoints.service.impl.LojistaServiceImpl;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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


    @PostMapping
    @ResponseStatus(CREATED)
    public Lojista salvar(@RequestBody @Valid Lojista lojista) {
//        if( lojista.getLogin() == null ||  lojista.getLogin() == ""){
//            throw new RegraNegocioException("O nome do cliente nao foi informado");
//        }
        String senhaCriptografada = passwordEncoder.encode(lojista.getSenha());
        lojista.setSenha(senhaCriptografada);
        return lojistaService.salvar(lojista);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
        try {
            Lojista lojista = Lojista.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();

            UserDetails lojistaAutenticado = lojistaService.autenticar(lojista);
            String token = jwtService.gerarToken(lojista);
            return new TokenDTO(lojista.getLogin(), token);

        } catch (UsernameNotFoundException | SenhaInvalidaException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());


        }


    }
}