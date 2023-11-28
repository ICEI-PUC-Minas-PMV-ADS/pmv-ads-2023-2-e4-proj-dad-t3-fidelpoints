package io.puc.projeto.fidelpoints.service;

import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.entity.Pedido;
import io.puc.projeto.fidelpoints.domain.enums.StatusPedido;
import io.puc.projeto.fidelpoints.rest.dto.CredenciaisDTO;
import io.puc.projeto.fidelpoints.rest.dto.PedidoDTO;
import io.puc.projeto.fidelpoints.rest.dto.TokenDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> findClientByEmail(String email);

    Cliente salvarCliente(Cliente cliente);

    void deletarCliente(Integer id);

    void atualizarCliente(Integer id, Cliente cliente);

    Cliente getCliente(Integer id);



}
