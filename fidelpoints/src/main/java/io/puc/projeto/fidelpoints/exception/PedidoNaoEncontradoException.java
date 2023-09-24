package io.projeto.backend.fidelpoints.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException( ) {
        super("Pedido não encontrado.");
    }
}
