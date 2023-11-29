package io.puc.projeto.fidelpoints.auth.service;

import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.entity.Lojista;

public interface UserAutenticationService {
    Cliente loadCliente(String username);

    Lojista loadLojista(String username);
}
