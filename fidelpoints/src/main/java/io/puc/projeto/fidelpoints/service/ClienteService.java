package io.puc.projeto.fidelpoints.service;

import io.puc.projeto.fidelpoints.entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> findClientByEmail(String email);

    Cliente salvarCliente(Cliente cliente);

    void deletarCliente(Integer id);

    void atualizarCliente(Integer id, Cliente cliente);

    Cliente getCliente(Integer id);



}
