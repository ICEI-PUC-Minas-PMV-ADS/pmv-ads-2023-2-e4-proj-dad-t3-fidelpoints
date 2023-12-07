package io.puc.projeto.fidelpoints.repository;

import io.puc.projeto.fidelpoints.entity.Lojista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LojistaRepository extends JpaRepository<Lojista, Integer>{
    Optional<Lojista> findByLogin(String login);
}
