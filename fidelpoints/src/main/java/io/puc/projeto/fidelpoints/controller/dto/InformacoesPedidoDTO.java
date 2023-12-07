package io.puc.projeto.fidelpoints.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {

    private Integer codigo;

    @Pattern(regexp = "\\d{10,11}")
    private String celular;

    private String nomeCliente;

    private Integer total;

    private String dataPedido;

    private String status;


    private List<InformacaoItemPedidoDTO> items;

}
