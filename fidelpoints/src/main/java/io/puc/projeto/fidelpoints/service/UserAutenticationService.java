package io.puc.projeto.fidelpoints.service;

import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import io.puc.projeto.fidelpoints.rest.dto.CredenciaisDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAutenticationService {
    Cliente loadCliente(CredenciaisDTO credenciais);

    Lojista loadLojista(CredenciaisDTO credenciais);
}
