package io.puc.projeto.fidelpoints.exception.erros;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException( ) {
        super("Cliente não encontrado.");
    }
}
