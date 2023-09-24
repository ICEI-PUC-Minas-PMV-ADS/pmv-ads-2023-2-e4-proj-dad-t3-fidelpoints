package io.projeto.backend.fidelpoints.service;

import io.stephanie.backend.fidelpoints.domain.entity.Pedido;
import io.stephanie.backend.fidelpoints.domain.enums.StatusPedido;
import io.stephanie.backend.fidelpoints.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar (PedidoDTO pedidoDTO );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);



}
