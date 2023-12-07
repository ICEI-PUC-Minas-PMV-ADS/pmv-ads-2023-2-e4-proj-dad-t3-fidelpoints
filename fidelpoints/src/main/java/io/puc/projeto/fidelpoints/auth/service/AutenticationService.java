package io.puc.projeto.fidelpoints.auth.service;

import io.puc.projeto.fidelpoints.controller.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.controller.dto.TokenDTO;

public interface AutenticationService {
    TokenDTO createTokenJWT(CredenciaisDTO credenciais);
}
