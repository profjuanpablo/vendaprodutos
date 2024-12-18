package com.example.vendaproduto.repository;

import com.example.vendaproduto.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT v FROM Venda v WHERE "
         + "(:produtoId IS NULL OR v.produto.id = :produtoId) AND "
         + "(:vendedorId IS NULL OR v.vendedor.id = :vendedorId) AND "
         + "(:dataInicio IS NULL OR v.dataVenda >= :dataInicio) AND "
         + "(:dataFim IS NULL OR v.dataVenda <= :dataFim)")
    List<Venda> filtrarVendas(
            @Param("produtoId") Long produtoId,
            @Param("vendedorId") Long vendedorId,
            @Param("dataInicio") Date dataInicio,
            @Param("dataFim") Date dataFim
    );
}
