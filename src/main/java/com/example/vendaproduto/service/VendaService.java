package com.example.vendaproduto.service;

import com.example.vendaproduto.model.Venda;
import com.example.vendaproduto.repository.VendaRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public Venda salvar(Venda venda) {
        return vendaRepository.save(venda);
    }

    // Método para realizar o filtro
    public List<Venda> filtrarVendas(Long produtoId, Long vendedorId, Date dataInicio, Date dataFim) {
        return vendaRepository.filtrarVendas(produtoId, vendedorId, dataInicio, dataFim);
    }
}
