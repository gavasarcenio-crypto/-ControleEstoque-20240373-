package com.myproject.controle_estoque.service;

import com.myproject.controle_estoque.model.Produto;
import com.myproject.controle_estoque.model.Estoque;
import com.myproject.controle_estoque.repository.ProdutoRepository;
import com.myproject.controle_estoque.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    public Produto criar(Produto produto) {

        Produto salvo = produtoRepository.save(produto);

        // Cria estoque automaticamente
        Estoque estoque = new Estoque();
        estoque.setProduto(salvo);
        estoque.setQuantidade(0);

        estoqueRepository.save(estoque);

        return salvo;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    public Produto atualizar(Long id, Produto produto) {
        Produto existente = buscarPorId(id);

        existente.setNome(produto.getNome());
        existente.setPreco(produto.getPreco());

        return produtoRepository.save(existente);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}