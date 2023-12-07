package io.puc.projeto.fidelpoints.repository;


import io.puc.projeto.fidelpoints.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

   Optional<Cliente> findByEmail (String email);


   @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
   List<Cliente> encontrarPorNome (@Param("nome") String nome);

   @Query(value = "select * from cliente c where c c.email = '%:email%' ", nativeQuery = true)
   List<Cliente> encontrarPorEmail (@Param("email") String email);

   @Query(value = "select * from cliente c where c.celular like '%:celular%' ", nativeQuery = true)
   List<Cliente> encontrarPorCelular (@Param("celular") String celular);

   @Query("delete from Cliente c where c.nome =:nome")
   @Modifying
   void deleteByNome(String nome);

   boolean existsByNome(String nome);

   @Query(" select c from Cliente c left join fetch c.pedidos where c.id =:id")
   Cliente findClienteFetchPedidos(@Param("id")  Integer id);

   //TODO ADICIONAR QUERY PARA ASSOCIAR CLIENTE AOS PONTOS NA LOJA EXPECIFICA

}

