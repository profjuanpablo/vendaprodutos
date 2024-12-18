package com.example.vendaproduto.controller;

import com.example.vendaproduto.model.Vendedor;
import com.example.vendaproduto.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public String listarVendedores(Model model) {
        List<Vendedor> vendedores = vendedorService.listarTodos();
        model.addAttribute("vendedores", vendedores);
        return "vendedores/lista"; // Exibe a lista de vendedores em uma página HTML
    }

    @GetMapping("/novo")
    public String novoVendedor(Model model) {
        model.addAttribute("vendedor", new Vendedor());
        return "vendedores/formulario"; // Exibe um formulário para criar/editar vendedores
    }

    @PostMapping("/salvar")
    public String salvarVendedor(@ModelAttribute Vendedor vendedor) {
        vendedorService.salvarVendedor(vendedor);
        return "redirect:/vendedores";
    }

    @GetMapping("/editar/{id}")
    public String editarVendedor(@PathVariable("id") Long id, Model model) {
        Vendedor vendedor = vendedorService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Vendedor inválido: " + id));
        model.addAttribute("vendedor", vendedor);
        return "vendedores/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletarVendedor(@PathVariable("id") Long id) {
        vendedorService.deletarVendedor(id);
        return "redirect:/vendedores";
    }
}
