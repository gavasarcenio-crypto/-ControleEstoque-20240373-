package com.myproject.controle_estoque.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.controle_estoque.dto.venda.ItemVendaRequest;
import com.myproject.controle_estoque.dto.venda.VendaRequest;
import com.myproject.controle_estoque.exception.EstoqueInsuficienteException;
import com.myproject.controle_estoque.exception.RecursoNaoEncontradoException;
import com.myproject.controle_estoque.model.Cliente;
import com.myproject.controle_estoque.model.Estoque;
import com.myproject.controle_estoque.model.ItemVenda;
import com.myproject.controle_estoque.model.Produto;
import com.myproject.controle_estoque.model.Venda;
import com.myproject.controle_estoque.repository.ClienteRepository;
import com.myproject.controle_estoque.repository.EstoqueRepository;
import com.myproject.controle_estoque.repository.ProdutoRepository;
import com.myproject.controle_estoque.repository.VendaRepository;

import jakarta.transaction.Transactional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Transactional
    public Venda registrarVenda(VendaRequest request) {

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado."));

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataVenda(LocalDateTime.now());

        List<ItemVenda> itensVenda = new ArrayList<>();

        for (ItemVendaRequest itemReq : request.getItens()) {

            Produto produto = produtoRepository.findById(itemReq.getProdutoId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado."));

            Estoque estoque = produto.getEstoque();
            if (estoque == null) {
                throw new RuntimeException("Produto não possui estoque cadastrado: " + produto.getNome());
            }

            if (estoque.getQuantidade() < itemReq.getQuantidade()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para: " + produto.getNome());
            }

            // baixa de estoque
            estoque.setQuantidade(estoque.getQuantidade() - itemReq.getQuantidade());
            estoqueRepository.save(estoque);

            // item da venda
            ItemVenda item = new ItemVenda();
            item.setVenda(venda);
            item.setProduto(produto);
            item.setQuantidade(itemReq.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());

            itensVenda.add(item);
        }

        venda.setItens(itensVenda);

        return vendaRepository.save(venda);
    }
}