package com.myproject.controle_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myproject.controle_estoque.model.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {}