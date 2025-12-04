package com.myproject.controle_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myproject.controle_estoque.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {}