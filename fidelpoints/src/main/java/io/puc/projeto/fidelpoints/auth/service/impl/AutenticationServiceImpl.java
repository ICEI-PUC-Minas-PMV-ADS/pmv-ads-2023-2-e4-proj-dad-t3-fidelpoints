package io.puc.projeto.fidelpoints.auth.service.impl;

import io.puc.projeto.fidelpoints.auth.config.JwtService;
import io.puc.projeto.fidelpoints.auth.service.AutenticationService;
import io.puc.projeto.fidelpoints.auth.service.UserAutenticationService;
import io.puc.projeto.fidelpoints.entity.Cliente;
import io.puc.projeto.fidelpoints.entity.Lojista;
import io.puc.projeto.fidelpoints.enums.Role;
import io.puc.projeto.fidelpoints.exception.erros.ClienteNotFoundException;
import io.puc.projeto.fidelpoints.exception.erros.LojistaNotFoundException;
import io.puc.projeto.fidelpoints.exception.erros.SenhaInvalidaException;
import io.puc.projeto.fidelpoints.controller.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.controller.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AutenticationServiceImpl implements AutenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserAutenticationService userAutenticationService;

    @Autowired
    private JwtService jwtService;

    @Override
    public TokenDTO createTokenJWT(CredenciaisDTO credenciais) {
        try {

            String token;

            if(credenciais.getRole() == Role.ROLE_CLIENTE){
                token = gerarTokenCliente(credenciais);
            }else{
                token = gerarTokenLojista(credenciais);
            }

            return new TokenDTO(credenciais.getLogin(), token);

        } catch (ClienteNotFoundException | LojistaNotFoundException | SenhaInvalidaException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());

        }
    }

    private String gerarTokenCliente(CredenciaisDTO credenciais) {
        Cliente cliente = userAutenticationService.loadCliente(credenciais.getLogin());

        validarCredenciais(credenciais.getSenha(), cliente.getSenha());

        return jwtService.gerarTokenCliente(cliente);
    }

    private String gerarTokenLojista(CredenciaisDTO credenciais) {
        Lojista lojista = userAutenticationService.loadLojista(credenciais.getLogin());

        validarCredenciais(credenciais.getSenha(), lojista.getSenha());

        return jwtService.gerarTokenLojista(lojista);
    }

    public void validarCredenciais(String senhaDigitada, String senhaArmazenada) {
        boolean senhasBatem = passwordEncoder.matches(senhaDigitada, senhaArmazenada);

        if(!senhasBatem){
            throw new SenhaInvalidaException();
        }

    }

}
