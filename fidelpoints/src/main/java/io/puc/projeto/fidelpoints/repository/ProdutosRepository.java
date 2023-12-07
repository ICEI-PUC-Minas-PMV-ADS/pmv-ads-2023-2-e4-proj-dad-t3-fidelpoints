package io.puc.projeto.fidelpoints.repository;

import io.puc.projeto.fidelpoints.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository <Produto, Integer>{

}
