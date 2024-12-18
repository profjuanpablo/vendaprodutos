package com.example.vendaproduto.service;

import com.example.vendaproduto.model.Vendedor;
import com.example.vendaproduto.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    // Método para listar todos os vendedores
    public List<Vendedor> listarTodos() {
        return vendedorRepository.findAll();
    }

    // Método para salvar ou atualizar um vendedor
    public Vendedor salvarVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    // Método para buscar um vendedor por ID
    public Optional<Vendedor> buscarPorId(Long id) {
        return vendedorRepository.findById(id);
    }

    // Método para deletar um vendedor por ID
    public void deletarVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }
}
