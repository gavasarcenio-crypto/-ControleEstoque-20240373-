package com.myproject.controle_estoque.controller;

import com.myproject.controle_estoque.model.Venda;
import com.myproject.controle_estoque.service.VendaService;
import com.myproject.controle_estoque.repository.VendaRepository;
import com.myproject.controle_estoque.dto.venda.VendaRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private VendaRepository vendaRepository;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody VendaRequest request) {
        try {
            Venda v = vendaService.registrarVenda(request);
            return ResponseEntity.ok(v);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listarTodas() {
        List<Venda> todas = vendaRepository.findAll();
        return ResponseEntity.ok(todas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getById(@PathVariable Long id) {
        return vendaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
