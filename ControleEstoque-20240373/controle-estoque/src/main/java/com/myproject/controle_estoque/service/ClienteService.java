package com.myproject.controle_estoque.service;

import com.myproject.controle_estoque.model.Cliente;
import com.myproject.controle_estoque.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente existente = buscarPorId(id);

        existente.setNome(cliente.getNome());
        existente.setEmail(cliente.getEmail());

        return clienteRepository.save(existente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}