package com.myproject.controle_estoque.controller;

import com.myproject.controle_estoque.model.Venda;
import com.myproject.controle_estoque.service.VendaService;
import com.myproject.controle_estoque.dto.venda.VendaRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public Venda registrar(@RequestBody VendaRequest request) {
        return vendaService.registrarVenda(request);
    }
}
