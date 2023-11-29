package io.puc.projeto.fidelpoints.rest.controller;


import io.puc.projeto.fidelpoints.domain.enums.Role;
import io.puc.projeto.fidelpoints.rest.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.rest.dto.TokenDTO;
import io.puc.projeto.fidelpoints.auth.service.AutenticationService;
import io.puc.projeto.fidelpoints.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class AuthController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AutenticationService autenticationService;


    @PostMapping("/clientes")
    public TokenDTO loginClientes(@RequestBody CredenciaisDTO credenciais) {
        credenciais.setRoleEnum(Role.ROLE_CLIENTE);
        return autenticationService.tokenDTO(credenciais);
    }

    @PostMapping("/lojistas")
    public TokenDTO loginLoJistas(@RequestBody CredenciaisDTO credenciais) {
        credenciais.setRoleEnum(Role.ROLE_LOJISTA);
        return autenticationService.tokenDTO(credenciais);
    }

}
