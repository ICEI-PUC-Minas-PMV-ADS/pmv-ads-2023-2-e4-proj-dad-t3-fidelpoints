package io.puc.projeto.fidelpoints.exception.erros;

public class LojistaNotFoundException extends RuntimeException {
    public LojistaNotFoundException( ) {
        super("Lojista não encontrado.");
    }
}
