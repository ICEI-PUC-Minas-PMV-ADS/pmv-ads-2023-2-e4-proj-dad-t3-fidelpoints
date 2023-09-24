package io.projeto.backend.fidelpoints.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {

    private Integer codigo;
    private String celular;
    private String nomeCliente;
    private Integer total;
    private String dataPedido;
    private String status;


    private List<InformacaoItemPedidoDTO> items;

}
