package com.myproject.controle_estoque.controller;

import com.myproject.controle_estoque.model.Estoque;
import com.myproject.controle_estoque.model.Produto;
import com.myproject.controle_estoque.repository.EstoqueRepository;
import com.myproject.controle_estoque.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // DTO simples embutido (você pode criar classe separada se preferir)
    public static class EstoqueDTO {
        public Integer quantidade;
        public Long produtoId;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody EstoqueDTO dto) {
        if (dto == null || dto.produtoId == null) {
            return ResponseEntity.badRequest().body("produtoId é obrigatório");
        }

        Produto produto = produtoRepository.findById(dto.produtoId).orElse(null);
        if (produto == null) {
            return ResponseEntity.badRequest().body("Produto não encontrado");
        }

        // Se já existir estoque para esse produto, atualizar a quantidade (opcional)
        Estoque existente = estoqueRepository.findByProdutoId(produto.getId());
        if (existente != null) {
            existente.setQuantidade(dto.quantidade);
            return ResponseEntity.ok(estoqueRepository.save(existente));
        }

        Estoque estoque = new Estoque();
        estoque.setQuantidade(dto.quantidade);
        estoque.setProduto(produto);

        Estoque salvo = estoqueRepository.save(estoque);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return estoqueRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // opcional: buscar por produto id
    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<?> getPorProduto(@PathVariable Long produtoId) {
        Estoque e = estoqueRepository.findByProdutoId(produtoId);
        if (e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }
}
