package io.puc.projeto.fidelpoints.service;

import io.puc.projeto.fidelpoints.domain.entity.Lojista;
import org.springframework.transaction.annotation.Transactional;

public interface LojistaService {

    @Transactional
    Lojista salvarLojista(Lojista lojista);
}
