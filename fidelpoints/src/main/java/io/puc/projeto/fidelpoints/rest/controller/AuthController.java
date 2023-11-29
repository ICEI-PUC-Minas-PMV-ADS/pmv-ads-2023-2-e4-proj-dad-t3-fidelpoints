package io.puc.projeto.fidelpoints.rest.controller;


import io.puc.projeto.fidelpoints.domain.enums.Role;
import io.puc.projeto.fidelpoints.rest.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.rest.dto.TokenDTO;
import io.puc.projeto.fidelpoints.service.AutenticationService;
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
        credenciais.setRoleEnum(Role.CLIENTE);
        return autenticationService.tokenDTO(credenciais);
    }

    @PostMapping("/lojistas")
    public TokenDTO loginLoJistas(@RequestBody CredenciaisDTO credenciais) {
        credenciais.setRoleEnum(Role.LOJISTA);
        return autenticationService.tokenDTO(credenciais);
    }

}
