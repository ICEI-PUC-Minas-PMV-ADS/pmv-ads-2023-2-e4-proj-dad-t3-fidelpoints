package io.puc.projeto.fidelpoints.service.impl;

import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import io.puc.projeto.fidelpoints.domain.enums.RoleEnum;
import io.puc.projeto.fidelpoints.exception.SenhaInvalidaException;
import io.puc.projeto.fidelpoints.jwt.JwtService;
import io.puc.projeto.fidelpoints.rest.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.rest.dto.TokenDTO;
import io.puc.projeto.fidelpoints.service.AutenticationService;
import io.puc.projeto.fidelpoints.service.UserAutenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
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
    public TokenDTO tokenDTO(CredenciaisDTO credenciais) {
        try {

            String token;

            if(credenciais.getRoleEnum() == RoleEnum.CLIENTE){
                token = gerarTokenCliente(credenciais);
            }else{
                token = gerarTokenCliente(credenciais);
            }

            return new TokenDTO(credenciais.getLogin(), token);

        } catch (UsernameNotFoundException | SenhaInvalidaException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());

        }
    }

    private String gerarTokenCliente(CredenciaisDTO credenciais) {
        Cliente cliente = userAutenticationService.loadCliente(credenciais);
        return jwtService.gerarTokenCliente(cliente);
    }

    private String gerarTokenLojista(CredenciaisDTO credenciais) {
        Lojista lojista = userAutenticationService.loadLojista(credenciais);
        return jwtService.gerarTokenLojista(lojista);
    }

    public UserDetails validarCredenciais(CredenciaisDTO credenciais, UserDetails user) {
        boolean senhasBatem = passwordEncoder.matches(credenciais.getSenha(), user.getPassword());

        if(!senhasBatem){
            throw new SenhaInvalidaException();
        }

        return user;
    }

}
