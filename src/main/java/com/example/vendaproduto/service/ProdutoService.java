package com.example.vendaproduto.service;

import com.example.vendaproduto.model.Produto;
import com.example.vendaproduto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void darBaixaEstoque(Long id, int quantidade) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        if (produto.getQuantidadeEstoque() >= quantidade) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
            produtoRepository.save(produto);
            if (produto.getQuantidadeEstoque() <= produto.getQuantidadeMinima()) {
                System.out.println("Estoque do produto " + produto.getNome() + " está baixo!");
            }
        } else {
            throw new RuntimeException("Estoque insuficiente para o produto " + produto.getNome());
        }
    }
}
