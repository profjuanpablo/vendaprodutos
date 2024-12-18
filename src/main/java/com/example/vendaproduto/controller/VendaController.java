package com.example.vendaproduto.controller;

import com.example.vendaproduto.model.Produto;
import com.example.vendaproduto.model.Venda;
import com.example.vendaproduto.model.Vendedor;
import com.example.vendaproduto.service.ProdutoService;
import com.example.vendaproduto.service.VendaService;
import com.example.vendaproduto.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

@Controller
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/vendas")
    public String listarVendas(
            @RequestParam(value = "produtoId", required = false) Long produtoId,
            @RequestParam(value = "vendedorId", required = false) Long vendedorId,
            @RequestParam(value = "dataInicio", required = false) String dataInicioStr,
            @RequestParam(value = "dataFim", required = false) String dataFimStr,
            Model model) {

        // Converte as strings de data para Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicio = null;
        Date dataFim = null;

        try {
            if (dataInicioStr != null && !dataInicioStr.isEmpty()) {
                dataInicio = sdf.parse(dataInicioStr);
            }
            if (dataFimStr != null && !dataFimStr.isEmpty()) {
                dataFim = sdf.parse(dataFimStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Realiza a busca filtrada
        List<Venda> vendasFiltradas = vendaService.filtrarVendas(produtoId, vendedorId, dataInicio, dataFim);

        // Adiciona os dados ao modelo
        model.addAttribute("vendas", vendasFiltradas);

        // Adiciona as listas de produtos e vendedores para os filtros
        List<Produto> produtos = produtoService.listarTodos();
        List<Vendedor> vendedores = vendedorService.listarTodos();
        model.addAttribute("produtos", produtos);
        model.addAttribute("vendedores", vendedores);

        return "vendas/relatorio";
    }


    @PostMapping
    public String salvar(Venda venda) {
        vendaService.salvar(venda);
        return "redirect:/vendas";
    }
}
