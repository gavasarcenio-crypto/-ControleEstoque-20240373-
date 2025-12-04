package com.myproject.controle_estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.controle_estoque.model.Estoque;
import com.myproject.controle_estoque.repository.EstoqueRepository;
import com.myproject.controle_estoque.repository.ProdutoRepository;
import com.myproject.controle_estoque.model.Produto;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public Estoque criar(@RequestBody Estoque estoque) {
        if (estoque.getProduto() == null || estoque.getProduto().getId() == null) {
            throw new RuntimeException("Produto não encontrado");
        }
        Produto produto = produtoRepository.findById(estoque.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        estoque.setProduto(produto);
        return estoqueRepository.save(estoque);
    }

    @GetMapping
    public List<Estoque> listar() {
        return estoqueRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estoque buscar(@PathVariable Long id) {
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
    }

    @PutMapping("/{id}")
    public Estoque atualizar(@PathVariable Long id, @RequestBody Estoque estoque) {
        Estoque existente = buscar(id);
        existente.setQuantidade(estoque.getQuantidade());
        
        if (estoque.getProduto() != null && estoque.getProduto().getId() != null) {
            Produto produto = produtoRepository.findById(estoque.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            existente.setProduto(produto);
        }
        
        return estoqueRepository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        estoqueRepository.deleteById(id);
    }
}
