package com.myproject.controle_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myproject.controle_estoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}