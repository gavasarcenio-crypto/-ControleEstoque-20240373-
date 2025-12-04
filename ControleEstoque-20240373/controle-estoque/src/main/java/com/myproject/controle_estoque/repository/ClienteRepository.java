package com.myproject.controle_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myproject.controle_estoque.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}