package io.projeto.backend.fidelpoints.domain.repository;

import io.stephanie.backend.fidelpoints.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository <Produto, Integer>{

}
