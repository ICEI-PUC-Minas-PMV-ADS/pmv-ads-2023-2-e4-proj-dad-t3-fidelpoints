package io.puc.projeto.fidelpoints.auth.service;

import io.puc.projeto.fidelpoints.rest.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.rest.dto.TokenDTO;

public interface AutenticationService {
    TokenDTO tokenDTO(CredenciaisDTO credenciais);
}
