package com.example.vendaproduto.repository;

import com.example.vendaproduto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Métodos adicionais para filtragem
}
