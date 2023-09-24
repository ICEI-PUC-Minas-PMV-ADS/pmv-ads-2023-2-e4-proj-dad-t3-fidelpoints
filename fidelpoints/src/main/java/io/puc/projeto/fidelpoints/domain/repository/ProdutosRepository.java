package io.puc.projeto.fidelpoints.domain.repository;

import io.puc.projeto.fidelpoints.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository <Produto, Integer>{

}
