package io.puc.projeto.fidelpoints.service;

import io.puc.projeto.fidelpoints.domain.entity.Pedido;
import io.puc.projeto.fidelpoints.domain.enums.StatusPedido;
import io.puc.projeto.fidelpoints.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar (PedidoDTO pedidoDTO );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);



}
