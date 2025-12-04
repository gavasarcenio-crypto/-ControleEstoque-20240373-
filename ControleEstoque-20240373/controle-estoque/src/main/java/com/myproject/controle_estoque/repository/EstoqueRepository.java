package com.myproject.controle_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myproject.controle_estoque.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {}