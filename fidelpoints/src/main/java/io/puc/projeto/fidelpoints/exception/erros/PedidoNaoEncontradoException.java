package io.puc.projeto.fidelpoints.exception.erros;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException( ) {
        super("Pedido não encontrado.");
    }
}
