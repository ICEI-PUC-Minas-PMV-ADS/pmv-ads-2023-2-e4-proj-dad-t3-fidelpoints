package io.puc.projeto.fidelpoints.controller;

import io.puc.projeto.fidelpoints.entity.ItemPedido;
import io.puc.projeto.fidelpoints.entity.Pedido;
import io.puc.projeto.fidelpoints.enums.StatusPedido;
import io.puc.projeto.fidelpoints.controller.dto.AtualizacaoStatusPedidoDTO;
import io.puc.projeto.fidelpoints.controller.dto.InformacaoItemPedidoDTO;
import io.puc.projeto.fidelpoints.controller.dto.InformacoesPedidoDTO;
import io.puc.projeto.fidelpoints.controller.dto.PedidoDTO;
import io.puc.projeto.fidelpoints.service.PedidoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Getter@Setter
@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/teste")
    @ResponseStatus( HttpStatus.OK )
    public String getClienteById() {

        return new String("Sucesso");
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer salvar (@RequestBody @Valid PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();

    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return pedidoService
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping ("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus( @PathVariable Integer id,
                              @RequestBody AtualizacaoStatusPedidoDTO atualizaçãoStatusPedidoDTO ){
        String novoStatus = atualizaçãoStatusPedidoDTO.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus) );

    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .celular(pedido.getCliente().getCelular())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itemPedidos) {
        if (CollectionUtils.isEmpty(itemPedidos)) {
            return Collections.emptyList();
        }
        return itemPedidos.stream().map(
                itemPedido -> InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(itemPedido.getProduto().getDescricao())
                        .pontosUnitarios(itemPedido.getProduto().getPontos())
                        .quantidade(itemPedido.getQuantidade())
                        .build()
        ).collect(Collectors.toList());

    }

}

