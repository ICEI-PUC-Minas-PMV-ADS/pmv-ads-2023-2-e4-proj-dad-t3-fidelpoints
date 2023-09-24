package io.projeto.backend.fidelpoints.service.impl;


import io.stephanie.backend.fidelpoints.domain.entity.Cliente;
import io.stephanie.backend.fidelpoints.domain.entity.ItemPedido;
import io.stephanie.backend.fidelpoints.domain.entity.Pedido;
import io.stephanie.backend.fidelpoints.domain.entity.Produto;
import io.stephanie.backend.fidelpoints.domain.enums.StatusPedido;
import io.stephanie.backend.fidelpoints.domain.repository.ClientesRepository;
import io.stephanie.backend.fidelpoints.domain.repository.ItemsPedidoRepository;
import io.stephanie.backend.fidelpoints.domain.repository.PedidosRepository;
import io.stephanie.backend.fidelpoints.domain.repository.ProdutosRepository;
import io.stephanie.backend.fidelpoints.exception.PedidoNaoEncontradoException;
import io.stephanie.backend.fidelpoints.exception.RegraNegocioException;
import io.stephanie.backend.fidelpoints.rest.dto.ItemPedidoDTO;
import io.stephanie.backend.fidelpoints.rest.dto.PedidoDTO;
import io.stephanie.backend.fidelpoints.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItemsPedidoRepository itemsPedidoRepository;



    @Override
    @Transactional
    public Pedido salvar (PedidoDTO pedidoDTO ){

        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente =  clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new
                        RegraNegocioException("Código de Cliente Inválido."));

        Pedido pedido = new Pedido();
        pedido.setDataPedido( LocalDate.now() );
        pedido.setCliente( cliente );
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setStatus(StatusPedido.CONFIRMADO);

        List<ItemPedido> itemsPedido = converterItems( pedido, pedidoDTO.getItems() );
        pedidosRepository.save( pedido );
        itemsPedidoRepository.saveAll( itemsPedido );
        pedido.setItens( itemsPedido );
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto( Integer id ) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus( Integer id, StatusPedido statusPedido ) {
         pedidosRepository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidosRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items ){
        if (items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items");

        }
        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: " + idProduto )
                            );

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade( dto.getQuantidade() );
                    itemPedido.setPedido( pedido );
                    itemPedido.setProduto(produto );
                    return itemPedido;


                }).collect( Collectors.toList() );

    }
}
