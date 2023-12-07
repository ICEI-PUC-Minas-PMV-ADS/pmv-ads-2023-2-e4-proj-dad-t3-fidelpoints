package io.puc.projeto.fidelpoints.controller;


import io.puc.projeto.fidelpoints.enums.Role;
import io.puc.projeto.fidelpoints.controller.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.controller.dto.TokenDTO;
import io.puc.projeto.fidelpoints.auth.service.AutenticationService;
import io.puc.projeto.fidelpoints.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AutenticationService autenticationService;


    @PostMapping("/clientes")
    public TokenDTO loginClientes(@RequestBody CredenciaisDTO credenciais) {
        credenciais.setRole(Role.ROLE_CLIENTE);
        return autenticationService.createTokenJWT(credenciais);
    }

    @PostMapping("/lojistas")
    public TokenDTO loginLoJistas(@RequestBody CredenciaisDTO credenciais) {
        credenciais.setRole(Role.ROLE_LOJISTA);
        return autenticationService.createTokenJWT(credenciais);
    }

}
