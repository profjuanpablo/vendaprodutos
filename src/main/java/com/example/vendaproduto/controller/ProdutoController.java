package com.example.vendaproduto.controller;

import com.example.vendaproduto.model.Produto;
import com.example.vendaproduto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produtos/listar";
    }

    @PostMapping
    public String salvar(Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/produtos";
    }
}
