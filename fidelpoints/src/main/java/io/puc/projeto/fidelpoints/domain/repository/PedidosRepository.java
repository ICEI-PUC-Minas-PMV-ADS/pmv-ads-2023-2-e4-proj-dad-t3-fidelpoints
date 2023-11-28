package io.puc.projeto.fidelpoints.domain.repository;

import io.puc.projeto.fidelpoints.domain.entity.Cliente;
import io.puc.projeto.fidelpoints.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id")
    Optional< Pedido > findByIdFetchItens ( @Param("id") Integer id );

    //@Query(" select p.idPedido, p.idCliente, p.dataPedido, p.status from pedido p on ip.idPedido = p.idPedido where p.id_loja =:idloja")
    //Optional< Pedido > findPedidoByIdLoja ( @Param("id") Integer idLoja );
}
