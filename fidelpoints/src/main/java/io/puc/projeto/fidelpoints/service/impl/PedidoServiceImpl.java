package io.puc.projeto.fidelpoints.service.impl;


import io.puc.projeto.fidelpoints.entity.Cliente;
import io.puc.projeto.fidelpoints.entity.ItemPedido;
import io.puc.projeto.fidelpoints.entity.Pedido;
import io.puc.projeto.fidelpoints.entity.Produto;
import io.puc.projeto.fidelpoints.enums.StatusPedido;
import io.puc.projeto.fidelpoints.repository.ClientesRepository;
import io.puc.projeto.fidelpoints.repository.ItemsPedidoRepository;
import io.puc.projeto.fidelpoints.repository.PedidosRepository;
import io.puc.projeto.fidelpoints.repository.ProdutosRepository;
import io.puc.projeto.fidelpoints.exception.erros.PedidoNaoEncontradoException;
import io.puc.projeto.fidelpoints.exception.erros.RegraNegocioException;
import io.puc.projeto.fidelpoints.controller.dto.ItemPedidoDTO;
import io.puc.projeto.fidelpoints.controller.dto.PedidoDTO;
import io.puc.projeto.fidelpoints.service.PedidoService;
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
